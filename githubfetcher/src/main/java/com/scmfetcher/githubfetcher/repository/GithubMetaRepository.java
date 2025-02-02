package com.scmfetcher.githubfetcher.repository;

import com.scmfetcher.githubfetcher.model.GithubMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GithubMetaRepository extends JpaRepository<GithubMeta, Integer> {
}
