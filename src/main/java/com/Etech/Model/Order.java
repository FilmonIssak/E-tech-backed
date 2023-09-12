package com.Etech.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    private Long id;
    private String orderNumber;
    private String orderDate;
    private String orderTime;
    private String orderStatus;
    private String orderTotal;
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
