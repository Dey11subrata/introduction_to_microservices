package com.dailylearning.microservice.employee_service.dto;

import com.dailylearning.microservice.employee_service.otherservices.AddressResponseDto;
import lombok.Data;

@Data
public class EmployeeResponseDto {
    private int id;
    private String name;
    private String bloodGroup;
    private AddressResponseDto addressResponseDto;
}
