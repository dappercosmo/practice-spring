package com.real.project.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private boolean isEmailVerified;
    private Date createdOn;

    private List<AddressDto> addresses;
}
