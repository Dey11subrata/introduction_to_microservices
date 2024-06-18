package com.dailylearning.microservice.employee_service.responses;

import lombok.Data;

@Data
public class EmployeeResponse {
    private int id;
    private String name;
    private String bloodGroup;
}
