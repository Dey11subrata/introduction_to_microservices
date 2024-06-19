package com.dailylearning.microservice.employee_service.services;

import com.dailylearning.microservice.employee_service.dto.EmployeeResponseDto;
import com.dailylearning.microservice.employee_service.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    // add an employee
    Employee addEmployee(Employee employee);
    // search an employee by id
    EmployeeResponseDto searchEmployeeById(int id);

    // list all employees
    List<Employee> listAllEmployee();
}
