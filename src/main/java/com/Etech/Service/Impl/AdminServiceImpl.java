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

    @Autowired
    private ProductRepo productRepo;


    @Override
    public void addProduct(Product product) {
        productRepo.save(product);
    }

    @Override
    public Product findProductById(long id) {
        return productRepo.findById(id).get();
    }

    @Override
    public Product updateProductDescription(long id, String description) {
        var product = productRepo.findById(id);
        Product tobeUpdate = product.orElseThrow(() -> new NoSuchElementException("Product with id " + id + " not found."));
        tobeUpdate.setDescription(description);
        return productRepo.save(tobeUpdate);

    }

    @Override
    public Product updateProductPrice(long id, double price) {
        var product = productRepo.findById(id);
        Product tobeUpdate = product.orElseThrow(() -> new NoSuchElementException("Product with id " + id + " not found."));
        tobeUpdate.setPrice(price);
        return productRepo.save(tobeUpdate);

    }

    @Override
    public List<Product> findAllProduct() {
        return productRepo.findAll();
    }



    @Override
    public void deleteProduct(long id) {
        productRepo.deleteById(id);
    }

}
