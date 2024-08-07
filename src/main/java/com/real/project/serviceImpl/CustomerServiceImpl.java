package com.real.project.serviceImpl;

import com.real.project.dto.AddressDto;
import com.real.project.dto.CustomerDto;
import com.real.project.model.Address;
import com.real.project.model.Customer;
import com.real.project.repository.CustomerRepository;
import com.real.project.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer addCustomerWithAddress(CustomerDto customerDto) {
        Customer newCustomer = Customer.builder()
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .email(customerDto.getEmail())
                .password(customerDto.getPassword())
                .createdOn(new Date())
                .phone(customerDto.getPhone())
                .isDeleted(false)
                .isEmailVerified(true).build();

        List<Address> addresses = new ArrayList<>();
        for (AddressDto addressDto : customerDto.getAddresses()) {
            Address newAddress = Address.builder()
                    .houseName(addressDto.getHouseName())
                    .street(addressDto.getStreet())
                    .city(addressDto.getCity())
                    .createdOn(new Date())
                    .country(addressDto.getCountry())
                    .isDeleted(false)
                    .customer(newCustomer).build();
            addresses.add(newAddress);
        }

        newCustomer.setAddresses(addresses);
        return customerRepository.save(newCustomer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .filter(customer -> !customer.isDeleted())
                .collect(Collectors.toList());
    }

    @Override
    public Customer updateCustomer(CustomerDto customerDto, Long id) {
        return customerRepository.findById(id)
                .map(existingCustomer -> {
                    Customer updatedCustomer = Customer.builder()
                            .customerId(existingCustomer.getCustomerId())
                            .firstName(Optional.ofNullable(customerDto.getFirstName()).orElse(existingCustomer.getFirstName()))
                            .lastName(Optional.ofNullable(customerDto.getLastName()).orElse(existingCustomer.getLastName()))
                            .email(Optional.ofNullable(customerDto.getEmail()).orElse(existingCustomer.getEmail()))
                            .password(Optional.ofNullable(customerDto.getPassword()).orElse(existingCustomer.getPassword()))
                            .updatedOn(new Date())
                            .phone(Optional.ofNullable(customerDto.getPhone()).orElse(existingCustomer.getPhone()))
                            .isEmailVerified(true)
                            .build();

                    return customerRepository.save(updatedCustomer);
                })
                .orElse(null);
    }

    @Override
    public boolean deleteCustomer(Long id) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setDeleted(true);
                    customerRepository.save(customer);
                    return true;
                })
                .orElse(false);

    }


}
