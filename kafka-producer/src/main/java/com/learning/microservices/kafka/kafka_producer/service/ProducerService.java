package com.learning.microservices.kafka.kafka_producer.service;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
private KafkaTemplate<String, String> kafkaTemplate;

@Autowired
    public ProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produceData(int n) throws InterruptedException {

        while(n>0){
            kafkaTemplate.send("test-topic1", "Data Produced");
            --n;
            Thread.sleep(2000);
        }

    }
}
