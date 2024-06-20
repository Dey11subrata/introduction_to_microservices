package com.dailylearning.microservice.employee_service.feignclient;

import com.dailylearning.microservice.employee_service.otherservices.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "a", url = "http://localhost:8081")
public interface AddressAPIClient {
    // method, request, response
    @GetMapping("/address/{id}")
    AddressResponse callAddressById(@PathVariable(name = "id") int id);
}
