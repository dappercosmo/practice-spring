package com.real.project.controllers;

import com.real.project.dto.CustomerDto;
import com.real.project.model.Customer;
import com.real.project.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/customer/")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    ResponseEntity<Customer> createCustomer(@RequestBody CustomerDto customerDto) {
        try {
            Customer customer = customerService.addCustomerWithAddress(customerDto);
            return ResponseEntity.ok(customer);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{customerId}")
    ResponseEntity<Customer> updateCustomer(@RequestBody CustomerDto customerDto, @PathVariable Long customerId) {
        try {
            Customer updatedCustomer = customerService.updateCustomer(customerDto, customerId);
            return ResponseEntity.ok(updatedCustomer);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    ResponseEntity<?> getAllCustomers() {
        try {
            List<Customer> customers = customerService.getAllCustomers();
            return ResponseEntity.ok(customers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occured while fetching all customers!");

        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        try {
            boolean deleted = customerService.deleteCustomer(id);

            if (deleted) {
                return ResponseEntity.ok().body("customer deleted successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occured while deleted customer");
        }
    }
}
