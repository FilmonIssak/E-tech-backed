package com.Etech.Service;


import com.Etech.Dto.ProductDto;
import com.Etech.Model.Product;

import java.util.List;

public interface CustomerService {

    public List<ProductDto> findAll();
}
