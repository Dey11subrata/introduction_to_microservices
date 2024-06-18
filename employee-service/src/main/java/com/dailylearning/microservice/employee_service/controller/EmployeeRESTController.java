package com.dailylearning.microservice.employee_service.controller;

import com.dailylearning.microservice.employee_service.model.Employee;
import com.dailylearning.microservice.employee_service.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("employee")
public class EmployeeRESTController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeRESTController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // add an employee
    @PostMapping("/add-employee")
    public ResponseEntity<Employee> insertEmployee(@RequestBody Employee employee){
        Employee newEmployee = employeeService.addEmployee(employee);
        return ResponseEntity.status(HttpStatus.ACCEPTED.value())
                            .header("employee-added", "true")
                            .body(newEmployee);
    }
    // find an employee by id
    @GetMapping("{id}")
    public ResponseEntity<Employee> searchEmployeeById(@PathVariable(name = "id") int id){
        Optional<Employee> employee = employeeService.searchEmployeeById(id);
         Employee searchedEmployee = employee.orElse(null);
         return ResponseEntity.status(HttpStatus.OK.value())
                 .body(searchedEmployee);
    }

    // list all employees
    @GetMapping("/list-all")
    public List<Employee> listAllEmployee(){
        return employeeService.listAllEmployee();
    }
}
