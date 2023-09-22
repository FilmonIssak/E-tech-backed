package com.Etech.Dto;


import com.Etech.Model.Address;
import com.Etech.Model.Cart;
import com.Etech.Model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private String orderNumber;
    private String orderDate;
    private String orderTime;
    private String orderTotal;

    private OrderStatus orderStatus;

    private Address address;

    /**
     * @Author Filmon,sase,abi
     * we have to update this one
     *
     * We have to create a payment class
     */

    private Cart cart;

    private CustomerDto customer;


}
