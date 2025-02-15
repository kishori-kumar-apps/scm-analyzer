package com.scmfetcher.githubfetcher.service;

import com.google.gson.Gson;
import com.scmfetcher.githubfetcher.pojo.RepositoryListResponse;
import com.scmfetcher.githubfetcher.repository.PayloadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class GithubRepoProcessor {

    @Autowired
    private PayloadRepository payloadRepository;

    private CopyOnWriteArrayList<String> repoStringList;

    public GithubRepoProcessor() {
        repoStringList = new CopyOnWriteArrayList<>();
    }

    @Async
    public void processAndAddRepos(String reposJsonString) {
        Gson gson = new Gson();
        RepositoryListResponse repositoryListResponse = gson.fromJson(reposJsonString, RepositoryListResponse.class);
        repoStringList.addAll(repositoryListResponse.getRepositoryStringList());
    }

    public void publishRepositories() {
        int processors = Runtime.getRuntime().availableProcessors();
        try (ExecutorService executorService = Executors.newFixedThreadPool(processors)) {
            repoStringList.forEach(repoString -> {

            });
        }
    }
}
