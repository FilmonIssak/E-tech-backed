package com.Etech.Service;

import com.Etech.Dto.ProductDto;

import java.util.List;

public interface AdminService {

    public ProductDto addProduct(ProductDto productDto);

    public ProductDto findProductById(long id);
    public List<ProductDto> findAllProduct();
    public void deleteProduct(long id);
    public ProductDto updateProductDescription(long id, ProductDto productDto);
    public ProductDto updateProductPrice(long id, ProductDto productDto);
    public ProductDto updateProductCategory(long id, ProductDto productDto);
    public ProductDto updateProductStatus(long id, ProductDto productDto);



}
