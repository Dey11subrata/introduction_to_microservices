package com.learning.microservices.kafka.kafka_producer.controller;

import com.learning.microservices.kafka.kafka_producer.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer")
public class ProducerRestController {
    private ProducerService producerService;

    @Autowired
    public ProducerRestController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping("")
    public String dataPublisher(@RequestParam(name = "num") int n) throws InterruptedException {
        producerService.produceData(n);
        return "data produced";
    }
}
