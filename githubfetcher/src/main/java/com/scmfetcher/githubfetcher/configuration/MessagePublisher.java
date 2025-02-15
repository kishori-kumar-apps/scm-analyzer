package com.scmfetcher.githubfetcher.configuration;

import org.springframework.stereotype.Component;

@Component
public class MessagePublisher {

    public void publishMessage(String message) {
        System.out.println("Published : " + message);
    }
}
