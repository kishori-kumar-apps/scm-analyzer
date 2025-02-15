package com.scmfetcher.githubfetcher.service;

import com.scmfetcher.githubfetcher.configuration.ApplicationProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    @Autowired
    private ApplicationProperty applicationProperty;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        kafkaTemplate.send(applicationProperty.getTopic(), message);
    }
}
