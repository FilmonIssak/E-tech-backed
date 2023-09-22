package com.Etech.Service;

import com.Etech.Dto.ProductDto;
import com.Etech.Model.enums.ProductCategory;

import java.util.List;

public interface ProductService {
    public List<ProductDto> searchProducts(String name);

    public List<ProductDto> findByCategory(ProductCategory category);
}
