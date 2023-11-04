package com.Etech.Dto;

import com.Etech.Model.enums.CustomerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDtoWithSpecificDetails {

    private Long id;
    private String firstName;
   // private String lastName;
    private String email;
  //  private String password;
    private String phone;
  //  private LocalDate dateOfRegistration;

    private CustomerStatus customerStatus;
//    private Role role;

//    private List<Address> addresses;




}
