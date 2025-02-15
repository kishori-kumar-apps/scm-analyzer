package com.scmfetcher.githubfetcher.constants;

public interface GithubConstants {
    String GITHUB_BASE_URL = "https://api.github.com";
    String PUBLIC_REPOSITORY_GITHUB_URI = "/repositories";
    String AUTHORIZATION_KEY = "Authorization";

    String API_VERSION_KEY = "X-GitHub-Api-Version";
    String API_VERSION_VALUE = "2022-11-28";

    String ACCEPT_TYPE_KEY = "Accept";
    String ACCEPT_TYPE_VALUE = "application/vnd.github+json";

    String SINCE_KEY = "since";

    String NEXT_PAGE_HEADER_KEY = "Link";
}
