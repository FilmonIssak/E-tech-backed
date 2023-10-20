package com.Etech.Dto;


import com.Etech.Model.Address;
import com.Etech.Model.Cart;
import com.Etech.Model.Product;
import com.Etech.Model.enums.OrderStatus;
import lombok.AllArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private String orderNumber;
    private Double orderTotal;
    private LocalDate orderDate;
    private LocalTime orderTime;
    private OrderStatus orderStatus;
    private Address address;
    private List<Product> productCartItems;
    private CustomerDto customer;


}
