package com.Etech.Service.Impl;

import com.Etech.Dto.ProductDto;
import com.Etech.Exception.ResourceException;
import com.Etech.Model.Product;
import com.Etech.Model.enums.ProductCategory;
import com.Etech.Repository.ProductRepo;
import com.Etech.Service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    private ModelMapper modelMapper;

//
//    @Override
//    public List<ProductDto> searchProducts(ProductDto productDto) {
//        List<Product> products = productRepo.findByNameContainingIgnoreCase(productDto.getName());
//        return products.stream().map(p -> modelMapper.map(p, ProductDto.class)).collect(Collectors.toList());
//    }
//
//    @Override
//    public List<ProductDto> findByCategory(ProductDto productDto) {
//        List<Product> products = productRepo.findByCategory(productDto.getProductCategory());
//        return products.stream().map(p -> modelMapper.map(p,ProductDto.class)).collect(Collectors.toList());
//    }


    @Override
    public List<ProductDto> searchProducts(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new ResourceException("Product name must not be null or empty", HttpStatus.BAD_REQUEST);
        }
        List<Product> products = productRepo.findByNameContainingIgnoreCase(name);
        if (products.isEmpty()) {
            throw new ResourceException("No products found with name: " + name);
        }
        return products.stream().map(p -> modelMapper.map(p, ProductDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findByCategory(ProductCategory category) {
        if (category == null) {
            throw new ResourceException("Category must not be null", HttpStatus.BAD_REQUEST);
        }
        List<Product> products = productRepo.findByProductCategory(category);
        if (products.isEmpty()) {
            throw new ResourceException("No products found in category: " + category);
        }
        return products.stream().map(p -> modelMapper.map(p, ProductDto.class)).collect(Collectors.toList());
    }

}
