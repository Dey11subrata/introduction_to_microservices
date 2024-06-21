package com.dailylearning.microservice.employee_service.services;

import com.dailylearning.microservice.employee_service.dto.EmployeeResponseDto;
import com.dailylearning.microservice.employee_service.feignclient.AddressAPIClient;
import com.dailylearning.microservice.employee_service.model.Employee;
import com.dailylearning.microservice.employee_service.otherservices.AddressResponse;
import com.dailylearning.microservice.employee_service.otherservices.AddressResponseDto;
import com.dailylearning.microservice.employee_service.repository.EmployeeRepository;
import com.dailylearning.microservice.employee_service.responses.EmployeeResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
//    private final RestTemplate restTemplate;
    private final AddressAPIClient addressAPIClient;


    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper, AddressAPIClient addressAPIClient) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
//        this.restTemplate = restTemplate;
        this.addressAPIClient = addressAPIClient;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public EmployeeResponseDto searchEmployeeById(int id) {
        Optional<Employee> resultEmployeeFromDatabase = employeeRepository.findById(id);

        EmployeeResponseDto employeeResponseDto = modelMapper.map(resultEmployeeFromDatabase.orElse(null), EmployeeResponseDto.class);
        /* till now we have only managed  employee entity.
         now address also needed in response
         so service communication need to be introduced here only*/
        // 01. Using RestTemplate
//       AddressResponse addressResponse = restTemplate.getForObject("http://localhost:8081/address/1", AddressResponse.class);
       AddressResponse addressResponse = addressAPIClient.callAddressById(1); // call using feign client
        employeeResponseDto.setAddressResponseDto(addressResponse.getAddressResponseDto());

        return employeeResponseDto;
    }

    @Override
    public List<Employee> listAllEmployee() {
        return employeeRepository.findAll();
    }
}
