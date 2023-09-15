package com.Etech.Service.Impl;

import com.Etech.Model.Product;
import com.Etech.Repository.CustomerRepo;
import com.Etech.Repository.ProductRepo;
import com.Etech.Service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepo customerRepo;
    private ProductRepo productRepo;

    public CustomerServiceImpl(CustomerRepo customerRepo, ProductRepo productRepo) {
        this.customerRepo = customerRepo;
        this.productRepo = productRepo;
    }


    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }
}
