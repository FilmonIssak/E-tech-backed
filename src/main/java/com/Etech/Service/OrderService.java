package com.Etech.Service;

import com.Etech.Dto.CustomerDto;
import com.Etech.Dto.OrderDto;
import com.Etech.Dto.ProductDto;
import com.Etech.Model.enums.OrderStatus;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {

    public OrderDto findOrderById(long id);

    public OrderDto cancelOrderByOrderId(long id);

    public OrderDto placeOrder(Long customerId);

    public OrderStatus checkOrderStatus(String orderNumber);
}
