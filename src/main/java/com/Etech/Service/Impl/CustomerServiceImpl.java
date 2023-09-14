package com.Etech.Service.Impl;

import com.Etech.Model.Product;
import com.Etech.Repository.ProductRepo;
import com.Etech.Repository.CustomerRepo;
import com.Etech.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private ProductRepo productRepo;




    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }
}
