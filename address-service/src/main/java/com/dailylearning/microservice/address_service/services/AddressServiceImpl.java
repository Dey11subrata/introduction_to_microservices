package com.dailylearning.microservice.address_service.services;

import com.dailylearning.microservice.address_service.model.Address;
import com.dailylearning.microservice.address_service.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AddressServiceImpl implements AddressService{
    private AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address addAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Optional<Address> searchAddressById(int id) {
        return addressRepository.findById(id);
    }

    @Override
    public List<Address> listAllAddresses() {
        return addressRepository.findAll();
    }
}
