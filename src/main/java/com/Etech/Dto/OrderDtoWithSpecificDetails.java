package com.Etech.Dto;


import com.Etech.Model.Address;
import com.Etech.Model.Product;
import com.Etech.Model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDtoWithSpecificDetails {

   // private Long id;
    private String orderNumber;
    private Double orderTotal;
    private LocalDate orderDate;
    private LocalTime orderTime;
    private OrderStatus orderStatus;
  //  private Address address;
    private List<Product> productCartItems;
    private CustomerDtoWithSpecificDetails customer;


}
