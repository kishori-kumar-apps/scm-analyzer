package com.scmfetcher.githubfetcher.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RepositoryListResponse {
    private List<String> repositoryStringList;
}
