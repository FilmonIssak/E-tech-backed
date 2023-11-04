package com.Etech.Service;

import com.Etech.Dto.CustomerDto;
import com.Etech.Dto.OrderDto;
import com.Etech.Dto.OrderDtoWithOutDetails;
import com.Etech.Model.Cart;
import com.Etech.Model.enums.OrderStatus;

import java.math.BigDecimal;

public interface OrderService {

    public OrderDtoWithOutDetails findOrderByOrderNumber(String orderNumber);

    public OrderDto cancelOrderByOrderNumber(String orderNumber);

    public OrderDto placeOrder(Long customerId);

    public BigDecimal calculateOrderTotal(Cart cart);
    public OrderStatus checkOrderStatus(String orderNumber);

}
