package com.Etech.Service.Impl;

import com.Etech.Dto.ProductDto;
import com.Etech.Exception.ResourceException;
import com.Etech.Model.Product;
import com.Etech.Repository.ProductRepo;
import com.Etech.Service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public ProductDto addProduct(ProductDto productDto) {

        Optional<Product> toBeAdded = productRepo.findById(productDto.getId());
        if(toBeAdded.isPresent()){
            throw new ResourceException("product already present: ", HttpStatus.CONFLICT);
        }
        Product product = modelMapper.map(productDto, Product.class);
        Product nowProduct = productRepo.save(product);
        return modelMapper.map(nowProduct, ProductDto.class);
    }

    @Override
    public ProductDto findProductById(long id) {
        Product product = productRepo.findById(id).orElseThrow(() -> new ResourceException("product with id: "+ id + "is already present"));
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public ProductDto updateProductDescription(long id, String description) {
        var product = productRepo.findById(id);
        Product tobeUpdate = product.orElseThrow(() -> new ResourceException("Product with id " + id + " not found."));
        tobeUpdate.setDescription(description);
        productRepo.save(tobeUpdate);
        return modelMapper.map(tobeUpdate,ProductDto.class);

    }

    @Override
    public ProductDto updateProductPrice(long id, double price) {
        var product = productRepo.findById(id);
        Product tobeUpdate = product.orElseThrow(() -> new NoSuchElementException("Product with id " + id + " not found."));
        tobeUpdate.setPrice(price);
         productRepo.save(tobeUpdate);
        return modelMapper.map(tobeUpdate, ProductDto.class);

    }

    @Override
    public List<ProductDto> findAllProduct() {

        List<Product> productList = productRepo.findAll();
        return productList.stream().map(product -> modelMapper.map(product,ProductDto.class)).collect(Collectors.toList());
    }



    @Override
    public void deleteProduct(long id) {

        Product toBeDeleted = productRepo.findById(id).orElseThrow(() -> new ResourceException("product to be deleted not found"));
        productRepo.delete(toBeDeleted);
    }

}
