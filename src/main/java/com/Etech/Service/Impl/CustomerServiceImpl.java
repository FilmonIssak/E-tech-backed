package com.Etech.Service.Impl;

import com.Etech.Dto.ProductDto;
import com.Etech.Exception.EnumArgumentException;
import com.Etech.Model.Product;
import com.Etech.Model.enums.ProductCategory;
import com.Etech.Repository.CustomerRepo;
import com.Etech.Repository.ProductRepo;
import com.Etech.Service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<ProductDto> findAll() {
        List<Product> productList = productRepo.findAll();
        return productList.stream().map(product -> modelMapper.map(product,ProductDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findAllByCatagory(String category) throws EnumArgumentException  {
        ProductCategory productCategory;
        try {
             productCategory = ProductCategory.valueOf (category );
        }
        catch (IllegalArgumentException e){
            throw new RuntimeException ("Category not Exist" + category);
        }
        return productRepo.findByCategory(productCategory).stream().map (product-> modelMapper.map (product, ProductDto.class)).collect( Collectors.toList());
    }


}
