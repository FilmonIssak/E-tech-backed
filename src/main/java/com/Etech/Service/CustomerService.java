package com.Etech.Service;


import com.Etech.Dto.ProductDto;
import com.Etech.Exception.EnumArgumentException;
import com.Etech.Model.Product;
import com.Etech.Model.enums.ProductCategory;

import java.util.List;

public interface CustomerService {

    public List<ProductDto> findAll();
    public List<ProductDto> findAllByCatagory(String category) throws EnumArgumentException;
}
