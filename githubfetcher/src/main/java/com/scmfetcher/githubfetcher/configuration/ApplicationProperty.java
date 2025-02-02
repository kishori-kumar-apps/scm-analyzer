package com.scmfetcher.githubfetcher.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "githubfetcher.github")
@Getter
@Setter
public class ApplicationProperty {
    private String token;
}
