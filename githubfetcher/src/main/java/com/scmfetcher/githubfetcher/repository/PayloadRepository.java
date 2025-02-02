package com.scmfetcher.githubfetcher.repository;

import com.scmfetcher.githubfetcher.model.Payload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayloadRepository extends JpaRepository<Payload, Long> {
}
