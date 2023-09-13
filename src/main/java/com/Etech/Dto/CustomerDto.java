package com.Etech.Dto;

import com.Etech.Model.Address;
import com.Etech.Model.CreditCard;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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


    private CreditCard creditCard;

    private List<Address> addresses;

    private List<OrderDto> orders;


}
