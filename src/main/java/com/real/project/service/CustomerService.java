package com.real.project.service;

import com.real.project.dto.CustomerDto;
import com.real.project.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer addCustomerWithAddress(CustomerDto customerDto);

    List<Customer> getAllCustomers();

    Customer updateCustomer(CustomerDto customerDto, Long id);

    boolean deleteCustomer(Long id);
}
