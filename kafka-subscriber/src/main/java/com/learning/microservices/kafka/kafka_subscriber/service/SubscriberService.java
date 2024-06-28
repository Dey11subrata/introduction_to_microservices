package com.learning.microservices.kafka.kafka_subscriber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class SubscriberService {
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public SubscriberService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "test-topic1", groupId = "test")
    public void kafkaListener(String message){
        System.out.println("Listener: "+message);
    }
}
