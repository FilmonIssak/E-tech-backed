package com.Etech.Service.Impl;

import com.Etech.Dto.CartDto;
import com.Etech.Dto.CustomerDto;
import com.Etech.Dto.ProductDto;
import com.Etech.Exception.ResourceException;
import com.Etech.Model.*;
import com.Etech.Repository.CartRepo;
import com.Etech.Repository.CustomerRepo;
import com.Etech.Repository.ProductRepo;
import com.Etech.Service.CustomerService;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Override
    public CustomerDto register(CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);
        customerRepo.save(customer);
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
    public CartDto addProductToViewerCart(Long viewerId, Long productId, int quantity) {
        if (viewerId == null || productId == null) {
            throw new IllegalArgumentException("customerId or ProductId cannot be null!");
        }
        Customer customer = customerRepo.findById(viewerId)
                .orElseThrow(() -> new ResourceNotFoundException("customer not found"));
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        Cart customerCart = customer.getCart();
        if (customerCart == null) {
            customerCart = new Cart();
            customer.setCart(customerCart);
            customerCart.setCustomer(customer);
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


}
