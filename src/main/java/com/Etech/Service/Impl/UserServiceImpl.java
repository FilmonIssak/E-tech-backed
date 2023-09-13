package com.Etech.Service.Impl;

import com.Etech.Model.Product;
import com.Etech.Repository.ProductRepo;
import com.Etech.Repository.UserRepo;
import com.Etech.Service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;
    private ProductRepo productRepo;

    public UserServiceImpl(UserRepo userRepo, ProductRepo productRepo) {
        this.userRepo = userRepo;
        this.productRepo = productRepo;
    }


    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }
}
