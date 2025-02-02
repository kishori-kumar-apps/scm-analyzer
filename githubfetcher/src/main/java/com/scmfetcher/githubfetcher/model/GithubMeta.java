package com.scmfetcher.githubfetcher.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@RequiredArgsConstructor
public class GithubMeta implements Serializable {
    private final Integer startFro;
}
