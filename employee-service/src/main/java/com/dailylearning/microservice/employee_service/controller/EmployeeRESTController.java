package com.dailylearning.microservice.employee_service.controller;

import com.dailylearning.microservice.employee_service.dto.EmployeeResponseDto;
import com.dailylearning.microservice.employee_service.model.Employee;
import com.dailylearning.microservice.employee_service.responses.EmployeeResponse;
import com.dailylearning.microservice.employee_service.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee")
public class EmployeeRESTController {
    private final EmployeeService employeeService;
//    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeRESTController(EmployeeService employeeService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
//        this.modelMapper = modelMapper;
    }

    // add an employee
    @PostMapping("/add-employee")
    public ResponseEntity<Employee> insertEmployee(@RequestBody Employee employee) {
        Employee newEmployee = employeeService.addEmployee(employee);
        return ResponseEntity.status(HttpStatus.ACCEPTED.value())
                .header("employee-added", "true")
                .body(newEmployee);
    }

    // find an employee by id
    @GetMapping("{id}")
    public ResponseEntity<EmployeeResponse> searchEmployeeById(@PathVariable(name = "id") int id) {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        EmployeeResponseDto employeeResponseDto = employeeService.searchEmployeeById(id);
//        EmployeeResponseDto employeeResponseDto = modelMapper.map(employee.orElse(null), EmployeeResponseDto.class);

        if (employeeResponseDto != null) {
            employeeResponse.setStatus(HttpStatus.FOUND.value());
            employeeResponse.setMessage("employee with id:" + id + " found");
            employeeResponse.setEmployeeResponseDto(employeeResponseDto);
            return ResponseEntity.status(HttpStatus.OK.value())
                    .header("employee-found", "true")
                    .body(employeeResponse);
        }
        employeeResponse.setStatus(HttpStatus.NOT_FOUND.value());
        employeeResponse.setMessage("employee with id:" + id + " not found");
        employeeResponse.setEmployeeResponseDto(employeeResponseDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .header("employee-found", "false")
                .body(employeeResponse);
    }

    // list all employees
    @GetMapping("/list-all")
    public List<Employee> listAllEmployee() {
        return employeeService.listAllEmployee();
    }
}
