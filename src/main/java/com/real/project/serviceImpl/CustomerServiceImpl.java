package com.real.project.serviceImpl;

import com.real.project.dto.AddressDto;
import com.real.project.dto.CustomerDto;
import com.real.project.model.Address;
import com.real.project.model.Customer;
import com.real.project.repository.AddressRepository;
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
    public CustomerServiceImpl(CustomerRepository customerRepository, AddressRepository addressRepository) {
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

    public Optional<Customer> findByFirstName(String firstName) {
        return Optional.ofNullable(customerRepository.findByFirstName(firstName));
    }
}
