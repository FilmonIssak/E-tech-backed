package com.Etech.Service;

import com.Etech.Model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AdminService {

    /**
     * @Author: Filmon.
     * TODO: 9/12/23 Add Admin Service Methods Here
     *
     */



    public void deleteProduct(long id);
    public void updateProductDescription(long id, String description);
    public List<Product> findAllProduct();


}
