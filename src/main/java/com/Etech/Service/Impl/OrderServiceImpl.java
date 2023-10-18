package com.Etech.Service.Impl;

import com.Etech.Dto.OrderDto;
import com.Etech.Exception.ResourceException;
import com.Etech.Model.Cart;
import com.Etech.Model.Customer;
import com.Etech.Model.Order;
import com.Etech.Model.Product;
import com.Etech.Model.enums.CustomerStatus;
import com.Etech.Model.enums.OrderStatus;
import com.Etech.Model.enums.ProductStatus;
import com.Etech.Repository.AddressRepo;
import com.Etech.Repository.CustomerRepo;
import com.Etech.Repository.OrderRepo;
import com.Etech.Repository.ProductRepo;
import com.Etech.Service.OrderService;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    CustomerRepo customerRepo;


    @Override
    public OrderDto findOrderById(long id) {
        Order toGet = orderRepo.findById(id).orElseThrow(() -> new ResourceException("Order with order id: " + id + " is not present"));
        return modelMapper.map(toGet, OrderDto.class);
    }

              @Override
              public OrderDto cancelOrderByOrderId(long id) {

                  Order order= orderRepo.findById(id).orElseThrow(()->new ResourceException("No order exists with given OrderId "+ id));
                  if(order.getOrderStatus()== OrderStatus.PENDING) {
                      order.setOrderStatus(OrderStatus.CANCELLED);
                      orderRepo.save(order);
                      return modelMapper.map(order, OrderDto.class);
                  }
                  else if(order.getOrderStatus()==OrderStatus.COMPLETED) {
                      order.setOrderStatus(OrderStatus.CANCELLED);
                      List<Product> productsInCartList= order.getProductCartItems();

                      for(Product p : productsInCartList ) {
                          Integer addedQuantity = p.getQuantity()+ 1;
                          p.setQuantity(addedQuantity);
                          if(p.getProductStatus() == ProductStatus.OUTOFSTOCK) {
                              p.setProductStatus(ProductStatus.AVAILABLE);
                          }
                      }

                      orderRepo.save(order);
                      return modelMapper.map(order, OrderDto.class);
                  }
                  else {
                      throw new ResourceException("Order was already cancelled");
                  }

              }

    @Override
    public OrderDto placeOrder(Long customerId) {
        if (customerId == null) {
            throw new IllegalArgumentException("customerId cannot be null!");
        }
        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        Cart customerCart = customer.getCart();
        if (customerCart == null || customerCart.getProducts().isEmpty()) {
            throw new IllegalStateException("The cart is empty. Cannot place an order with an empty cart.");
        }

        Order order = new Order();
        order.setOrderNumber(generateUniqueOrderNumber(customerId));
        order.setOrderStatus(OrderStatus.PENDING);
        order.setCustomer(customer);

        orderRepo.save(order);

        for (Map.Entry<Product, Integer> entry : customerCart.getProducts().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            product.deductQuantity(quantity); 
        }

        customerCart.getProducts().clear();
        customerCart.setTotalPrice(0);

        customerRepo.save(customer);
        productRepo.saveAll(customerCart.getProducts().keySet());

        return modelMapper.map(order, OrderDto.class);
    }


    private String generateUniqueOrderNumber(Long customerId) {
        DateTimeFormatter timestampFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timestamp = LocalDateTime.now().format(timestampFormatter);
        return timestamp + customerId;
    }


    @Override
    public OrderStatus checkOrderStatus(String orderNumber) {
        Order order = orderRepo.findOrderByOrderNumber(orderNumber);
        if (order == null) {
            throw new ResourceException("Order with order number: " + orderNumber + " is not present");
        }
        return order.getOrderStatus();
    }


}
