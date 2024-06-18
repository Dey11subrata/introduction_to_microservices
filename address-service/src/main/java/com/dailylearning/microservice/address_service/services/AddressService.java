package com.dailylearning.microservice.address_service.services;

import com.dailylearning.microservice.address_service.model.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    // add address
    public Address addAddress(Address address);
    // get address by id
    public Optional<Address> searchAddressById(int id);
    // get all address
    public List<Address> listAllAddresses();
}
