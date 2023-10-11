package com.Etech.Model;

import com.Etech.Model.enums.CustomerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
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
    private LocalDate dateOfRegistration;

    @Enumerated(EnumType.STRING)
    private CustomerStatus customerStatus;

    @ManyToOne
    private Role role;

    @Embedded
    private CreditCard creditCard;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Address> addresses;

//    @OneToMany(cascade = CascadeType.ALL)
//    private List<Order> orders;


}
