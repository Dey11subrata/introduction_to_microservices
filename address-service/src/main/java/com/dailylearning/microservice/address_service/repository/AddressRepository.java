package com.dailylearning.microservice.address_service.repository;

import com.dailylearning.microservice.address_service.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
