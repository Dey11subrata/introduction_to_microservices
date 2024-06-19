package com.dailylearning.microservice.employee_service.otherservices;

import lombok.Data;

@Data
public class AddressResponseDto {
    private int id;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private long zip;
}
