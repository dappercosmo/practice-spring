package com.real.project.controllers;

import com.real.project.dto.CustomerDto;
import com.real.project.model.Customer;
import com.real.project.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer/")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    ResponseEntity<Customer> createCustomer(@RequestBody CustomerDto customerDto){
        try{
            Customer customer = customerService.addCustomerWithAddress(customerDto);
            return ResponseEntity.ok(customer);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

}
