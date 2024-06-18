package com.dailylearning.microservice.employee_service.services;

import com.dailylearning.microservice.employee_service.model.Employee;
import com.dailylearning.microservice.employee_service.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> searchEmployeeById(int id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> listAllEmployee() {
        return employeeRepository.findAll();
    }
}
