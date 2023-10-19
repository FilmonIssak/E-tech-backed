package com.Etech.Dto;

import com.Etech.Model.Address;
import com.Etech.Model.CreditCard;
import com.Etech.Model.Role;
import com.Etech.Model.enums.CustomerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private LocalDate dateOfRegistration;

    private CustomerStatus customerStatus;
//    private Role role;

    private CreditCard creditCard;

//    private List<Address> addresses;



}
