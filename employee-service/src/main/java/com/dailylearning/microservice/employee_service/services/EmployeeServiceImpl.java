package com.dailylearning.microservice.employee_service.services;

import com.dailylearning.microservice.employee_service.dto.EmployeeResponseDto;
import com.dailylearning.microservice.employee_service.feignclient.AddressAPIClient;
import com.dailylearning.microservice.employee_service.model.Employee;
import com.dailylearning.microservice.employee_service.otherservices.AddressResponse;
import com.dailylearning.microservice.employee_service.otherservices.AddressResponseDto;
import com.dailylearning.microservice.employee_service.repository.EmployeeRepository;
import com.dailylearning.microservice.employee_service.responses.EmployeeResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    private Map<Integer, EmployeeResponseDto> cacheEmployee= new HashMap<>();
    private int retryCount=0;

//    private final RestTemplate restTemplate;
    private final AddressAPIClient addressAPIClient;

/*    @Autowired
    public void setAddressAPIClient(AddressAPIClient addressAPIClient) {
        this.addressAPIClient = addressAPIClient;
    }*/

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
    @CircuitBreaker(name ="address-service-circuit-breaker", fallbackMethod = "addEmployeeFallback")
    @Retry(name ="address-service-circuit-breaker", fallbackMethod = "addEmployeeFallback")
    public EmployeeResponseDto searchEmployeeById(int id) {
        System.out.println("Attempt:"+ ++retryCount);
        Optional<Employee> resultEmployeeFromDatabase = employeeRepository.findById(id);

        EmployeeResponseDto employeeResponseDto = modelMapper.map(resultEmployeeFromDatabase.orElse(null), EmployeeResponseDto.class);
        // cache result if found;
        if(!employeeResponseDto.equals(null)){
            cacheEmployee.put(id, employeeResponseDto);
        }
        /* till now we have only managed  employee entity.
         now address also needed in response
         so service communication need to be introduced here only*/
        // 01. Using RestTemplate
//       AddressResponse addressResponse = restTemplate.getForObject("http://localhost:8081/address/1", AddressResponse.class);

        // 02. Using Feign Client
       AddressResponse addressResponse = addressAPIClient.callAddressById(1); // call using feign client
        employeeResponseDto.setAddressResponseDto(addressResponse.getAddressResponseDto());

        return employeeResponseDto;
    }

    @Override
    public List<Employee> listAllEmployee() {
        return employeeRepository.findAll();
    }

//    Fallbacks
    public EmployeeResponseDto addEmployeeFallback(int id, RuntimeException exception){
        System.out.println("fallback invoked");
        System.out.println(cacheEmployee);
            if(!cacheEmployee.isEmpty()){
                return cacheEmployee.get(id);
            }
            else
                return new EmployeeResponseDto();
    }
}
