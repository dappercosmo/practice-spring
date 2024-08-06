package com.real.project.service;

import com.real.project.dto.CustomerDto;
import com.real.project.model.Customer;

public interface CustomerService {
    Customer addCustomerWithAddress(CustomerDto customerDto);
}
