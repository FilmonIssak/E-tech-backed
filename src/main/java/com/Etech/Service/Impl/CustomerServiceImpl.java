package com.Etech.Service.Impl;

import com.Etech.Dto.CartDto;
import com.Etech.Dto.CustomerDto;
import com.Etech.Dto.CustomerRegistrationDTO;
import com.Etech.Dto.ProductDto;
import com.Etech.Event.sender.CustomerRegisteredEvent;
import com.Etech.Exception.ResourceException;
import com.Etech.Model.*;
import com.Etech.Repository.CartRepo;
import com.Etech.Repository.CustomerRepo;
import com.Etech.Repository.ProductRepo;
import com.Etech.Service.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerRegisteredEvent customerRegisteredEvent;

    @Override
    public CustomerDto register(CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);
        customerRepo.save(customer);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            CustomerRegistrationDTO messageDTO = modelMapper.map(customerDto, CustomerRegistrationDTO.class);
            // map messageDTO to String
            String memberMessage = objectMapper.writeValueAsString(messageDTO);
            customerRegisteredEvent.sendCustomerRegisteredEvent(memberMessage);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return modelMapper.map(customer, CustomerDto.class);
    }


    @Override
    public List<CustomerDto> getAll(){
        List<Customer> customerList = customerRepo.findAll();
        return customerList.stream().map(customer -> modelMapper.map(customer,CustomerDto.class)).collect(Collectors.toList());
    }
    @Override
    public List<ProductDto> findAll() {
        List<Product> productList = productRepo.findAll();
        return productList.stream().map(product -> modelMapper.map(product,ProductDto.class)).collect(Collectors.toList());
    }
    @Override
    public CartDto addProductToCustomerCart(Long customerId, Long productId, int quantity) {
        if (customerId == null || productId == null) {
            throw new IllegalArgumentException("customerId or ProductId cannot be null!");
        }
        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("customer not found"));
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        Cart customerCart = customer.getCart();
        if (customerCart == null) {
            customerCart = new Cart();
            customer.setCart(customerCart);
            customerCart.setCustomer(customer);
        }
        if(quantity> product.getQuantity()){
            throw new ResourceException("Not Enough quantity we only have "  + product.getQuantity() + " - "+ product.getName() + " In our Stock" , HttpStatus.CONFLICT);
        }
        if (quantity < 0) {
            throw new ResourceException("Product quantity cannot be negative");
        }

        customerCart.addProduct(product, quantity);

        customerRepo.save(customer);
        customerCart = customer.getCart();
        return modelMapper.map(customerCart, CartDto.class);
    }

    @Override
    public CartDto deleteProductFromCustomerCart(Long customerId, Long productId) {
        Customer customer = customerRepo.findById(customerId).orElseThrow(() -> new ResourceException("Customer not found"));
        Product product = productRepo.findById(productId).orElseThrow(() -> new ResourceException("Product not found"));
        Cart customerCart = customer.getCart();
        customerCart.removeProduct(product);
        customerCart.updateTotalPrice();
        cartRepo.save(customerCart);
        return modelMapper.map(customerCart, CartDto.class);
    }

  @Override
    public List<CartDto> findAllProductCart() {
        List<Cart> cartList = cartRepo.findAll();
        List<CartDto> cartDtoList = new ArrayList<>();

        for (Cart cart : cartList) {
            CartDto cartDto = modelMapper.map(cart, CartDto.class);
            cartDtoList.add(cartDto);
        }

        return cartDtoList;
    }

}
