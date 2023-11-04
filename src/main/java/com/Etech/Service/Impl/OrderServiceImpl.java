package com.Etech.Service.Impl;

import com.Etech.Dto.*;
import com.Etech.Event.sender.OrderCanceledEvent;
import com.Etech.Event.sender.OrderPlacedEvent;
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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private CustomerRepo customerRepo;

    @Autowired
    private OrderPlacedEvent orderPlacedEvent;

    @Autowired
    private OrderCanceledEvent orderCanceledEvent;

    @Autowired
    private PaypalService paypalService;

    @Override
    public OrderDtoWithOutDetails findOrderByOrderNumber(String orderNumber) {
        Order toGet = orderRepo.findOrderByOrderNumber(orderNumber);
         if (toGet == null) {
            throw new ResourceException("Order with order number: " + orderNumber + " is not present");
        }
        return modelMapper.map(toGet, OrderDtoWithOutDetails.class);
    }

//    @Override
//    public OrderDto addOrder(OrderDto orderDto) {
//        Long orderId = orderDto.getId();
//
//        if (orderId != null) {
//            Optional<Order> toBeAdded = orderRepository.findById(orderDto.getId());
//            if (toBeAdded.isPresent()) {
//                throw new ResourceException("Order with order id = " + orderDto.getId() + " is already present", HttpStatus.CONFLICT);
//            }
//        }
//       Order order = modelMapper.map(orderDto, Order.class);
//
//        Address address = order.getAddress();
//        if (address.getId() == null) {
//            address = addressRepository.save(address);
//
//            order.setAddress(address);
//        }
//
//        /**
//         * first we need to create a cart and customer */
//
//       orderRepository.save(order);
//       return modelMapper.map(order, OrderDto.class);
//    }


        @Override
        public OrderDto cancelOrderByOrderNumber(String orderNumber) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
        Order order= orderRepo.findOrderByOrderNumber(orderNumber);
        if(order == null){ throw new ResourceException("No order exists with given orderNumber "+ orderNumber);}
         if(order.getOrderStatus()== OrderStatus.PENDING) {
            order.setOrderStatus(OrderStatus.CANCELLED);
            try {
                OrderCancelationDto messageDTO = modelMapper.map(order, OrderCancelationDto.class);
                // map messageDTO to String
                String memberMessage = objectMapper.writeValueAsString(messageDTO);
                orderCanceledEvent.sendOrderCanceledEvent(memberMessage);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            List<Product> productsInCartList= order.getProductCartItems();


            for(Product p : productsInCartList ) {
                int deductedQuantity = p.getQuantity();
                Integer addedQuantity = p.getQuantity()+ deductedQuantity;
                p.setQuantity(addedQuantity);
                if(addedQuantity > 0 && p.getProductStatus() == ProductStatus.OUTOFSTOCK) {
                    p.setProductStatus(ProductStatus.AVAILABLE);
                }
            }

            productRepo.saveAll(productsInCartList);
            orderRepo.save(order);
            return modelMapper.map(order, OrderDto.class);
        }
        else {
            throw new ResourceException("Sorry Order is already : " + order.getOrderStatus());
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


//        BigDecimal orderTotal = calculateOrderTotal(customerCart);
//
//        if (customer.getAccountBalance().compareTo(orderTotal) < 0) {
//            throw new IllegalStateException("Insufficient funds to place the order.");
//        }


        Order order = new Order();
        order.setOrderNumber(generateUniqueOrderNumber(customerId));
        order.setOrderDate(LocalDate.from(LocalDateTime.now()));
        order.setOrderTime(LocalTime.from(LocalDateTime.now()));
        order.setOrderTotal(customerCart.getTotalPrice());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setCustomer(customer);
        orderRepo.save(order);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            OrderPlacedDto messageDTO = modelMapper.map(order, OrderPlacedDto.class);
            // map messageDTO to String
            String memberMessage = objectMapper.writeValueAsString(messageDTO);
            orderPlacedEvent.sendOrderPlacedEvent(memberMessage);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        for (Map.Entry<Product, Integer> entry : customerCart.getProducts().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            product.deductQuantity(quantity);
            if(product.getQuantity()<0){
                throw new ResourceException("Product quantity can not be negative");
            }
        }

        productRepo.saveAll(customerCart.getProducts().keySet());

        customerCart.getProducts().clear();
        customerCart.setTotalPrice(0);


        customerRepo.save(customer);
        productRepo.saveAll(customerCart.getProducts().keySet());
        System.out.println("OrderTime (before returning): " + order.getOrderTime());


        return modelMapper.map(order, OrderDto.class);
    }

    @Override
    public BigDecimal calculateOrderTotal(Cart cart) {
        double totalPrice = cart.getTotalPrice();
        return BigDecimal.valueOf(totalPrice);
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
