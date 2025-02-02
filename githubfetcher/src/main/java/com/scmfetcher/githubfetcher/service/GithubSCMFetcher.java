package com.scmfetcher.githubfetcher.service;

import com.scmfetcher.githubfetcher.repository.PayloadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GithubSCMFetcher implements ISCMFetcher{

    @Autowired
    private WebClient webClient;

    @Autowired
    private PayloadRepository payloadRepository;

    @Override
    public void fetchGithubRepositories() {

    }
}
