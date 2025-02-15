package com.scmfetcher.githubfetcher.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "github-meta")
public record GithubMeta(@Id Integer startFrom) implements Serializable {
    @Serial
    private static final long serialVersionUID = -3714394938016482571L;
}
