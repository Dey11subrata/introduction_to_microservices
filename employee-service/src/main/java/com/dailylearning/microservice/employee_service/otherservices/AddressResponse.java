package com.dailylearning.microservice.employee_service.otherservices;

import lombok.Data;

@Data
public class AddressResponse {
    private int status;
    private String message;
    private AddressResponseDto addressResponseDto;
}
