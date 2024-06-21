package com.dailylearning.microservice.contact_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact")
public class ContactRESTController {

    @GetMapping("/hello")
    public String welcome(){
        return "Hello from contact...";
    }
}
