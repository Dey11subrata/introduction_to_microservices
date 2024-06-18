package com.dailylearning.microservice.address_service.response;

import com.dailylearning.microservice.address_service.dto.AddressResponseDto;
import lombok.Data;

@Data
public class AddressResponse {
    private int status;
    private String message;
    private AddressResponseDto addressResponseDto;
}
