package com.scmfetcher.githubfetcher.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "githubfetcher.github")
@Getter
@Setter
public class ApplicationProperty {
    private String token;
    private Integer numberOfRequest;

    @Value("${mq.producer.topic}")
    private String topic;
}
