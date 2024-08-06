package com.real.project.repository;

import com.real.project.model.Address;
import com.real.project.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    //List<Customer> findByCustomerId(Long id);



}
