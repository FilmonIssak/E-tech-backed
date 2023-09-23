package com.Etech.Model;

import com.Etech.Model.enums.CustomerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    private String phone;
    private LocalDateTime dateOfRegistration;

    @Enumerated(EnumType.STRING)
    private CustomerStatus customerStatus;

//    @OneToOne
//    private Role role;

    @Embedded
    private CreditCard creditCard;

    @OneToMany
    private List<Address> addresses;

    @OneToMany
    private List<Order> orders;


}
