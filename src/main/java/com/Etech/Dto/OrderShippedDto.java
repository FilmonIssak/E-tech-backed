package com.Etech.Dto;


import com.Etech.Model.Address;
import com.Etech.Model.Product;
import com.Etech.Model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderShippedDto {

//    private Long id;
    private String orderNumber;
    //   private Double orderTotal;
    private LocalDate orderDate;
//    private LocalTime orderTime;
    private OrderStatus orderStatus;
    private Address address;
    private List<Product> productCartItems;
    private CustomerDto customer;


}
