package com.real.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDto {
    private String houseName;
    private String street;
    private String city;
    private String country;
    private Date createdOn;
    private boolean isDeleted;

    private CustomerDto customerDto;
}
