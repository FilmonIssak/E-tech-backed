package com.Etech.Service.Impl;


import com.Etech.Dto.ProductDto;
import com.Etech.Exception.ResourceException;
import com.Etech.Model.Product;
import com.Etech.Model.enums.ProductCategory;
import com.Etech.Repository.ProductRepo;
import com.Etech.Service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImp implements ProductService {

    @Autowired
    ProductRepo productRepo;
    @Autowired
    ModelMapper modelMapper;


    @Override
    public ProductDto findProductByName(String name) {
        var product = productRepo.getProductByName (name).orElseThrow ( () -> new ResourceException("Product with the Name : "+ name + " is not present"));
        return modelMapper.map (  product,ProductDto.class);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepo.findAll ().stream ().map ( product -> modelMapper.map ( product, ProductDto.class)).collect( Collectors.toList());
    }

    @Override
    public List<ProductDto> findAllByCatagory(String category) throws RuntimeException {
        ProductCategory productCategory;
        try {
             productCategory = ProductCategory.valueOf(category.toUpperCase());

        }
        catch (IllegalArgumentException e){
            throw  new ResourceException  ("No category with the name :" + category);
        }

        List<Product> product =productRepo.findProductsByProductCategory(productCategory);

        if(product.isEmpty ()) {
            throw new ResourceException ("The Category: " + category + " has no Products");
        }
        else
        return product.stream ().map ( p -> modelMapper.map ( p, ProductDto.class ) ).collect ( Collectors.toList () );
    }

    @Override
      public List<ProductDto> findAllByKeyWord(String keyWord) {
          return productRepo.findAllByKeyWord ( keyWord ).stream ().map ( product -> modelMapper.map ( product, ProductDto.class)).collect( Collectors.toList());
    }


//    @Override
//    public List<ProductDto> filterProducts(String name, String category, String keyWord) {
//
//        List<ProductDto> productDtoList = new ArrayList<> ();
//        if(keyWord!=null){
//            productDtoList = productRepo.findAllByKeyWord ( keyWord ).stream ().map ( product -> modelMapper.map ( product, ProductDto.class)).collect( Collectors.toList());
//        }
//        if(category!=null){
//            ProductCategory productCategory;
//
//            try {
//                productCategory = ProductCategory.valueOf(category.toUpperCase());
//
//            }
//            catch (IllegalArgumentException e){
//                if(name!=null){
//                    Product product = productRepo.getProductByName (name).orElseThrow ( () -> new ResourceException("Product with the Name : "+ name + " is not present"));
//                    productDtoList.add ( modelMapper.map ( product, ProductDto.class));
//                }
//
//             List<Product> product =productRepo.findProductsByProductCategory(productCategory);
//
//            if(product.isEmpty ()) {
//                throw new ResourceException ("The Category: " + category + " has no Products");
//            }
//            else
//                return product.stream ().map ( p -> modelMapper.map ( p, ProductDto.class ) ).collect ( Collectors.toList () );
//
//
//        }
//
//        if(name!=null){
//            Product product = productRepo.getProductByName (name).orElseThrow ( () -> new ResourceException("Product with the Name : "+ name + " is not present"));
//            productDtoList.add ( modelMapper.map ( product, ProductDto.class));
//        }
//        return productDtoList;
//     }
}
