package com.Etech.Dto;


import com.Etech.Model.Address;
import com.Etech.Model.Cart;
import com.Etech.Model.Product;
import com.Etech.Model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private String orderNumber;
    private String orderDate;
    private String orderTime;
    private Double orderTotal;
    private OrderStatus orderStatus;
    private Address address;
    private List<Product> productCartItems;
    private CustomerDto customer;


}
