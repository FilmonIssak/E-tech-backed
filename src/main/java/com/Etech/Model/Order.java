package com.Etech.Model;


import com.Etech.Model.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String orderDate;
    private String orderTime;
    private String orderTotal;

    @Enumerated
    private OrderStatus orderStatus;

    @ManyToOne
    private Address address;

    /**
     * @Author Filmon,sase,abi
     * we have to update this one
     *
     * We have to create a payment class
     */


    @OneToOne
    private Cart cart;

    @ManyToOne
    private Customer customer;

//    private String orderSubtotal;
//    private String orderTax;
//    private String orderShipping;
//    private String orderDiscount;
//    private String orderPaymentMethod;
//    private String orderPaymentStatus;
//    private String orderShippingMethod;
//    private String orderShippingStatus;
//    private String orderShippingAddress;
//    private String orderBillingAddress;



}
