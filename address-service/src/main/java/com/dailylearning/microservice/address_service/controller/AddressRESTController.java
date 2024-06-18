package com.dailylearning.microservice.address_service.controller;

import com.dailylearning.microservice.address_service.dto.AddressResponseDto;
import com.dailylearning.microservice.address_service.model.Address;
import com.dailylearning.microservice.address_service.response.AddressResponse;
import com.dailylearning.microservice.address_service.services.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/address")
public class AddressRESTController {
    private final AddressService addressService;
    private final ModelMapper modelMapper;

    @Autowired
    public AddressRESTController(AddressService addressService, ModelMapper modelMapper) {
        this.addressService = addressService;
        this.modelMapper = modelMapper;
    }

    // method to handle adding an address
    @PostMapping("/add_address")
    public ResponseEntity<AddressResponse> insertAddress(@RequestBody Address address){
        AddressResponse addressResponse = new AddressResponse();
        Address savedAddress = addressService.addAddress(address);
        AddressResponseDto addressResponseDto = modelMapper.map(savedAddress, AddressResponseDto.class);
        addressResponse.setStatus(HttpStatus.OK.value());
        addressResponse.setMessage("Address inserted successfully");
        addressResponse.setAddressResponseDto(addressResponseDto);
        return ResponseEntity.status(HttpStatus.CREATED.value())
                .header("address-inserted", "true")
                .body(addressResponse);
    }

    // method to handle searching an address by id
    @GetMapping("/{id}")
    public ResponseEntity<AddressResponse> searchById(@PathVariable(name = "id") int id){
        AddressResponse addressResponse = new AddressResponse();
        Optional<Address> address = addressService.searchAddressById(id);

    AddressResponseDto addressResponseDto = modelMapper.map(address.orElse(null), AddressResponseDto.class);
if(addressResponseDto!=null){
    addressResponse.setStatus(HttpStatus.FOUND.value());
    addressResponse.setMessage("address found");
    addressResponse.setAddressResponseDto(addressResponseDto);
    return ResponseEntity.status(HttpStatus.OK.value())
            .header("address-found", "true")
            .body(addressResponse);
}
        addressResponse.setStatus(HttpStatus.NOT_FOUND.value());
        addressResponse.setMessage("address not found");
        addressResponse.setAddressResponseDto(null);
        return ResponseEntity.status(HttpStatus.NO_CONTENT.value())
                .header("address-found", "false")
                .body(addressResponse);
    }
    // method to handle list all saved addresses
    @GetMapping("/list_all")
    public List<Address> listAllAddress(){
        return addressService.listAllAddresses();
    }
}
