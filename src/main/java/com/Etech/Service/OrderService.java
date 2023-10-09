package com.Etech.Service;

import com.Etech.Dto.CustomerDto;
import com.Etech.Dto.OrderDto;
import com.Etech.Dto.ProductDto;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {

    public OrderDto findOrderById(long id);

    public OrderDto cancelOrderByOrderId(long id);

    //public OrderDto addOrder (OrderDto orderDto);


   // public OrderDto updateOrder (long id, OrderDto orderDto);

    //public CustomerDto getCustomerByOrderNumber(Long orderNumber);


}
