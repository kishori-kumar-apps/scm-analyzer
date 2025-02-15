package com.scmfetcher.githubfetcher.service;

import com.scmfetcher.githubfetcher.configuration.ApplicationProperty;
import com.scmfetcher.githubfetcher.constants.GithubConstants;
import com.scmfetcher.githubfetcher.model.GithubMeta;
import com.scmfetcher.githubfetcher.repository.GithubMetaRepository;
import com.scmfetcher.githubfetcher.util.GithubUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

@Service
@Slf4j
public class GithubSCMFetcher implements ISCMFetcher {

    private final WebClient webClient;

    @Autowired
    private GithubMetaRepository githubMetaRepository;

    @Autowired
    private GithubRepoProcessor githubRepoProcessor;

    @Autowired
    private ApplicationProperty applicationProperty;

    private static final Lock lock = new ReentrantLock();

    public GithubSCMFetcher(WebClient.Builder builder) {
        this.webClient = builder.baseUrl(GithubConstants.GITHUB_BASE_URL + GithubConstants.PUBLIC_REPOSITORY_GITHUB_URI)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.set(GithubConstants.AUTHORIZATION_KEY, applicationProperty.getToken());
                })
                .exchangeStrategies(ExchangeStrategies
                        .builder()
                        .codecs(codecs -> codecs
                                .defaultCodecs()
                                .maxInMemorySize(500 * 1024))
                        .build())
                .build();
    }

    @Override
    public void fetchGithubRepositories() {
        log.info("Fetch repositories starting");
        lock.lock();
        log.info("Fetch repositories process lock acquired");
        try {
            AtomicInteger nextSince = new AtomicInteger(0);
            IntStream.range(0, applicationProperty.getNumberOfRequest())
                    .takeWhile(i -> nextSince.get() != -1)
                    .forEach(index -> {
                        Mono<String> mono = webClient
                                .get()
                                .uri(uriBuilder -> uriBuilder
                                        .queryParam(GithubConstants.SINCE_KEY, nextSince.get())
                                        .queryParam(GithubConstants.API_VERSION_KEY, GithubConstants.API_VERSION_VALUE)
                                        .queryParam(GithubConstants.ACCEPT_TYPE_KEY, GithubConstants.ACCEPT_TYPE_VALUE)
                                        .build())

                                .exchangeToMono(res -> {
                                    if (res.statusCode().is2xxSuccessful()) {
                                        String linkHeader = res.headers().header(GithubConstants.NEXT_PAGE_HEADER_KEY).getFirst();
                                        nextSince.set(GithubUtil.getSinceValue(linkHeader));
                                        return res.bodyToMono(String.class);
                                    } else {
                                        return res.createError();
                                    }
                                });

                        String output = mono.block();
                        githubRepoProcessor.processAndAddRepos(output);
                    });

            githubMetaRepository.save(new GithubMeta(nextSince.get()));
            githubRepoProcessor.publishRepositories();
        } catch (Exception e) {
            log.error("Error while fetching repositories: {}", e.getMessage(), e);
        } finally {
            lock.unlock();
            log.info("Fetch repositories process lock released");
        }
        log.info("Fetch repositories completed");
    }

    @EventListener(ApplicationReadyEvent.class)
    public void run() throws Exception {
        log.info("Application Started");
        log.info("Start Github fetch repositories");
        fetchGithubRepositories();
    }
}
