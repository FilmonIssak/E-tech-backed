package com.Etech.Model;


import com.Etech.Model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"order\"")
public class Order {

    @Id
    @GeneratedValue
    private Long id;
    private String orderNumber;
    private LocalDate orderDate;
    private Double orderTotal;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne
    private Address address;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "order_id")
    private List<Product> productCartItems = new ArrayList<>();

    @ManyToOne
    private Customer customer;


}
