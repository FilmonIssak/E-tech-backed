package com.Etech.Service.Impl;

import com.Etech.Dto.CustomerDto;
import com.Etech.Dto.ProductDto;
import com.Etech.Exception.ResourceException;
import com.Etech.Model.Customer;
import com.Etech.Model.Product;
import com.Etech.Repository.CustomerRepo;
import com.Etech.Repository.ProductRepo;
import com.Etech.Service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CustomerDto register(CustomerDto customerDto) {
        Long customerId = customerDto.getId();

        if (customerId != null) {
            Optional<Product> toBeAdded = productRepo.findProductById(customerId);

            if (toBeAdded.isPresent()) {
                throw new ResourceException("Customer with ID " + customerDto.getId() + " already exists", HttpStatus.CONFLICT);
            }
        }
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



}
