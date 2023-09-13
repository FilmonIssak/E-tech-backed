package com.Etech.Service;

import com.Etech.Model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AdminService {

    public void addProduct(Product product);

    public Product findProductById(long id);
    public List<Product> findAllProduct();
    public void deleteProduct(long id);
    public Product updateProductDescription(long id, String description);
    public Product updateProductPrice(long id, double price);



}
