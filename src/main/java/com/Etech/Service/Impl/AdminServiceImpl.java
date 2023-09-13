package com.Etech.Service.Impl;

import com.Etech.Model.Product;
import com.Etech.Repository.AdminRepo;
import com.Etech.Repository.ProductRepo;
import com.Etech.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {


    private AdminRepo adminRepo;
    private ProductRepo productRepo;



    @Autowired
    public AdminServiceImpl(AdminRepo adminRepo, ProductRepo productRepo) {
        this.adminRepo = adminRepo;
        this.productRepo = productRepo;
    }



    /**
     * @Author: Filmon.
     * TODO: 9/12/23 Add Admin Service Impl Methods Here
     *
     */




    @Override
    public void deleteProduct(long id) {
        adminRepo.deleteProduct(id);
    }

    @Override
    public void updateProductDescription(long id, String description) {

            Optional<Product> optionalProduct = productRepo.findById(id);

            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                product.setDescription(description);
                productRepo.save(product);
            } else {
                throw new NoSuchElementException("Product with id " + id + " not found.");
        }
    }

    @Override
    public List<Product> findAllProduct() {
        return productRepo.findAll();
    }



}
