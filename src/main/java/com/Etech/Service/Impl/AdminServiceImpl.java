package com.Etech.Service.Impl;

import com.Etech.Dto.*;
import com.Etech.Event.sender.OrderDeliveredEvent;
import com.Etech.Event.sender.OrderShippedEvent;
import com.Etech.Exception.ResourceException;
import com.Etech.Model.*;
import com.Etech.Model.enums.OrderStatus;
import com.Etech.Repository.*;
import com.Etech.Service.AdminService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private OrderRepo orderRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private OrderShippedEvent orderShippedEvent;

    @Autowired
    private OrderDeliveredEvent orderDeliveredEvent;





    @Override
    public ProductDto addProduct(ProductDto productDto) {
        Long productId = productDto.getId();

        if (productId != null) {
            Optional<Product> toBeAdded = productRepo.findProductById(productId);

            if (toBeAdded.isPresent()) {
                throw new ResourceException("Product with ID " + productDto.getId() + " already exists", HttpStatus.CONFLICT);
            }
        }
        Product product = modelMapper.map(productDto, Product.class);
        productRepo.save(product);
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public ProductDto findProductById(long id) {
        Product product = productRepo.findProductById(id).orElseThrow(() -> new ResourceException("Product with id: "+ id + " is not present"));
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public ProductDto updateProduct(long id, ProductDto productDto) {
        Product toBeUpdated = productRepo.findProductById(id).orElseThrow(() -> new ResourceException("Product with id: "+ id + " is not present", HttpStatus.NOT_FOUND));
        toBeUpdated.setName(productDto.getName());
        toBeUpdated.setQuantity(productDto.getQuantity());
        toBeUpdated.setPrice(productDto.getPrice());
        toBeUpdated.setDescription(productDto.getDescription());
        toBeUpdated.setProductStatus(productDto.getProductStatus());
        toBeUpdated.setProductCategory(productDto.getProductCategory());
        productRepo.save(toBeUpdated);
        return modelMapper.map(toBeUpdated, ProductDto.class);
    }
    @Override
    public ProductDto updateProductDescription(long id, ProductDto productDto) {
        Product toBeUpdated = productRepo.findProductById(id).orElseThrow(() -> new ResourceException("Product with id: "+ id + " is not present", HttpStatus.NOT_FOUND));
        toBeUpdated.setDescription(productDto.getDescription());
        productRepo.save(toBeUpdated);
        return modelMapper.map(toBeUpdated, ProductDto.class);

    }

    @Override
    public ProductDto updateProductPrice(long id, ProductDto productDto) {
        Product toBeUpdated = productRepo.findProductById(id).orElseThrow(() -> new ResourceException("Product with id: "+ id + " is not present", HttpStatus.NOT_FOUND));
        toBeUpdated.setPrice(productDto.getPrice());
        productRepo.save(toBeUpdated);
        return modelMapper.map(toBeUpdated, ProductDto.class);

    }
    @Override
    public ProductDto updateProductCategory(long id, ProductDto productDto) {
        Product toBeUpdated = productRepo.findProductById(id).orElseThrow(() -> new ResourceException("Product with id: "+ id + " is not present", HttpStatus.NOT_FOUND));
        toBeUpdated.setProductCategory(productDto.getProductCategory());
        productRepo.save(toBeUpdated);
        return modelMapper.map(toBeUpdated, ProductDto.class);

    }

    @Override
    public ProductDto updateProductStatus(long id, ProductDto productDto) {
        Product toBeUpdated = productRepo.findProductById(id).orElseThrow(() -> new ResourceException("Product with id: "+ id + " is not present", HttpStatus.NOT_FOUND));
        toBeUpdated.setProductStatus(productDto.getProductStatus());
        productRepo.save(toBeUpdated);
        return modelMapper.map(toBeUpdated, ProductDto.class);
    }

    @Override
    public List<ProductDto> findAllProduct() {

        List<Product> productList = productRepo.findAll();
        return productList.stream().map(product -> modelMapper.map(product,ProductDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteProduct(long id) {

        Product toBeDeleted = productRepo.findProductById(id).orElseThrow(() -> new ResourceException("Product to be deleted not found"));
        productRepo.delete(toBeDeleted);
    }

    /** Customer $*/

    @Override
    public CustomerDto updateCustomerPassword(Long id, CustomerDto customerDto) {
        Customer toBeUpdated = customerRepo.findCustomersById(id).orElseThrow(() -> new ResourceException("Customer with id: "+ id + " is not present", HttpStatus.NOT_FOUND));
        toBeUpdated.setPassword(customerDto.getPassword());
        customerRepo.save(toBeUpdated);
        return modelMapper.map(toBeUpdated, CustomerDto.class);
    }

/**
 * Will update the updatePassword with this change password later $
 *
 * @Override
    public void changePassword(long id, PasswordDTO passwordDTO) {
        Customer customer = customerRepo.findById(id).orElseThrow(()->new ResourceException("Customer Not found"));
        if (!passwordDTO.getNewPassword().equals(passwordDTO.getVerifyNewPassword())){
            throw new ResourceException("Password must match",HttpStatus.CONFLICT);
        }
        customer.setId(id);
        customer.setPassword(bCryptPasswordEncoder.encode(passwordDTO.getVerifyNewPassword()));
        customerRepo.save(customer);
    }
 */

    @Override
    public CustomerDto updateCustomerPhone(Long id, CustomerDto customerDto) {
        Customer toBeUpdated = customerRepo.findCustomersById(id).orElseThrow(() -> new ResourceException("Customer with id: "+ id + " is not present", HttpStatus.NOT_FOUND));
        toBeUpdated.setPhone(customerDto.getPhone());
        customerRepo.save(toBeUpdated);
        return modelMapper.map(toBeUpdated, CustomerDto.class);
    }

    @Override
    public CustomerDto updateCustomerEmail(Long id, CustomerDto customerDto) {
        Customer toBeUpdated = customerRepo.findCustomersById(id).orElseThrow(() -> new ResourceException("Customer with id: "+ id + " is not present", HttpStatus.NOT_FOUND));
        toBeUpdated.setEmail(customerDto.getEmail());
        customerRepo.save(toBeUpdated);
        return modelMapper.map(toBeUpdated, CustomerDto.class);
    }

    @Override
    public CustomerDto activateOrDeactivateCustomerStatus(Long id, CustomerDto customerDto) {
        Customer toBeUpdated = customerRepo.findCustomersById(id).orElseThrow(() -> new ResourceException("Customer with id: "+ id + " is not present", HttpStatus.NOT_FOUND));
        toBeUpdated.setCustomerStatus(customerDto.getCustomerStatus());
        customerRepo.save(toBeUpdated);
        return modelMapper.map(toBeUpdated, CustomerDto.class);
    }

    @Override
    public CustomerDto updateCustomerDetails(Long id, CustomerDto customerDto) {
        Customer toBeUpdated = customerRepo.findCustomersById(id).orElseThrow(() -> new ResourceException("Customer with id: "+ id + " is not present", HttpStatus.NOT_FOUND));
        toBeUpdated.setFirstName(customerDto.getFirstName());
        toBeUpdated.setLastName(customerDto.getLastName());
        toBeUpdated.setPassword(customerDto.getPassword());
//        toBeUpdated.setRole(customerDto.getRole());
        toBeUpdated.setPhone(customerDto.getPhone());
        toBeUpdated.setEmail(customerDto.getEmail());
        toBeUpdated.setCustomerStatus(customerDto.getCustomerStatus());
        //toBeUpdated.setCreditCard(customerDto.getCreditCard());
        customerRepo.save(toBeUpdated);
        return modelMapper.map(toBeUpdated, CustomerDto.class);
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer toBeDeleted = customerRepo.findCustomersById(id).orElseThrow(() -> new ResourceException("Customer to be deleted not found"));
        customerRepo.delete(toBeDeleted);

    }


    /** Order $ */

    @Override
    public List<OrderDtoWithSpecificDetails> getAllOrdersByDate(LocalDate orderDate) {
        List<Order> listOfOrdersOntheDay = orderRepository.findOrderByOrderDate(orderDate);
        return listOfOrdersOntheDay.stream().map(order -> modelMapper.map(order, OrderDtoWithSpecificDetails.class)).collect(Collectors.toList());
    }

    @Override
    public List<OrderDtoWithSpecificDetails> getAllOrders() {
        List<Order> orderList = orderRepository.findAll();
        return orderList.stream().map(order -> modelMapper.map(order,OrderDtoWithSpecificDetails.class)).collect(Collectors.toList());
    }



    @Override
    public OrderDto updateOrderStatusToCompleted(String orderNumber, OrderDto orderDto) {
        Order order = orderRepository.findOrderByOrderNumber(orderNumber);
        if (order == null) {
            throw new ResourceException("Order with order number: " + orderNumber + " is not present", HttpStatus.NOT_FOUND);
        }
        order.setOrderStatus(OrderStatus.COMPLETED);
        orderRepository.save(order);
        return modelMapper.map(order, OrderDto.class);
    }

    @Override
    public void deleteOrder(String orderNumber) {
        Order toBeDeleted = orderRepository.findOrderByOrderNumber(orderNumber);
        if(toBeDeleted == null){
            throw new ResourceException("Order to be deleted not found");
        }
        orderRepository.delete(toBeDeleted);
    }

    @Override
    public OrderDto updateOrderStatusToDelivery(String orderNumber, OrderDto orderDto) {
        Order order = orderRepository.findOrderByOrderNumber(orderNumber);
        if (order == null) {
            throw new ResourceException("Order with order number: " + orderNumber + " is not present", HttpStatus.NOT_FOUND);
        }
        order.setOrderStatus(OrderStatus.DELIVERED);
        orderRepository.save(order);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            OrderPlacedDto messageDTO = modelMapper.map(order, OrderPlacedDto.class);
            // map messageDTO to String
            String memberMessage = objectMapper.writeValueAsString(messageDTO);
            orderDeliveredEvent.sendOrderDeliveredEvent(memberMessage);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return modelMapper.map(order, OrderDto.class);
    }



    @Override
    public OrderDto updateOrderStatusToShipping(String orderNumber, OrderDto orderDto) {
        Order order = orderRepository.findOrderByOrderNumber(orderNumber);
                if (order == null) {
            throw new ResourceException("Order with order number " + orderNumber + " not found");
        }

        order.setOrderStatus(OrderStatus.SHIPPED);
        orderRepository.save(order);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            OrderPlacedDto messageDTO = modelMapper.map(order, OrderPlacedDto.class);
            // map messageDTO to String
            String memberMessage = objectMapper.writeValueAsString(messageDTO);
            orderShippedEvent.sendOrderShippedEvent(memberMessage);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return modelMapper.map(order, OrderDto.class);
    }






}
