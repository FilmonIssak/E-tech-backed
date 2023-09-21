package com.Etech.Service;

import com.Etech.Dto.CustomerDto;
import com.Etech.Dto.OrderDto;
import com.Etech.Dto.ProductDto;

import java.util.List;

public interface OrderService {

    public ProductDto findProductById(long id);

    public OrderDto addOrder (OrderDto orderDto);

    public void cancelOrder(long id);

    public OrderDto updateOrder (long id, OrderDto orderDto);

    public List<OrderDto> getAllOrdersByDate(String orderDate);

    public List<OrderDto> getAllOrders();

    public CustomerDto getCustomerByOrderNumber(String orderNumber);




}
