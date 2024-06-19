package com.dailylearning.microservice.employee_service.responses;

import com.dailylearning.microservice.employee_service.dto.EmployeeResponseDto;
import lombok.Data;

@Data
public class EmployeeResponse {
    private int status;
    private String message;
    private EmployeeResponseDto employeeResponseDto;
}
