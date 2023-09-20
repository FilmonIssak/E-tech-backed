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
        Long productId = productDto.getId();

        if (productId != null) {
            Optional<Product> toBeAdded = productRepo.findProductById(productId);

            if (toBeAdded.isPresent()) {
                throw new ResourceException("Product with ID " + productDto.getId() + " already exists", HttpStatus.CONFLICT);
            }
        }
        Product product = modelMapper.map(productDto, Product.class);
        productRepo.save(product);
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public ProductDto findProductById(long id) {
        Product product = productRepo.findProductById(id).orElseThrow(() -> new ResourceException("Product with id: "+ id + " is not present"));
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public ProductDto updateProductDescription(long id, ProductDto productDto) {
        Product toBeUpdated = productRepo.findProductById(id).orElseThrow(() -> new ResourceException("Product with id: "+ id + " is not present", HttpStatus.NOT_FOUND));
        toBeUpdated.setDescription(productDto.getDescription());
        productRepo.save(toBeUpdated);
        return modelMapper.map(toBeUpdated, ProductDto.class);

    }

    @Override
    public ProductDto updateProductPrice(long id, ProductDto productDto) {
        Product toBeUpdated = productRepo.findProductById(id).orElseThrow(() -> new ResourceException("Product with id: "+ id + " is not present", HttpStatus.NOT_FOUND));
        toBeUpdated.setPrice(productDto.getPrice());
        productRepo.save(toBeUpdated);
        return modelMapper.map(toBeUpdated, ProductDto.class);

    }
    @Override
    public ProductDto updateProductCategory(long id, ProductDto productDto) {
        Product toBeUpdated = productRepo.findProductById(id).orElseThrow(() -> new ResourceException("Product with id: "+ id + " is not present", HttpStatus.NOT_FOUND));
        toBeUpdated.setProductCategory(productDto.getProductCategory());
        productRepo.save(toBeUpdated);
        return modelMapper.map(toBeUpdated, ProductDto.class);

    }

    @Override
    public ProductDto updateProductStatus(long id, ProductDto productDto) {
        Product toBeUpdated = productRepo.findProductById(id).orElseThrow(() -> new ResourceException("Product with id: "+ id + " is not present", HttpStatus.NOT_FOUND));
        toBeUpdated.setProductStatus(productDto.getProductStatus());
        productRepo.save(toBeUpdated);
        return modelMapper.map(toBeUpdated, ProductDto.class);
    }

    @Override
    public List<ProductDto> findAllProduct() {

        List<Product> productList = productRepo.findAll();
        return productList.stream().map(product -> modelMapper.map(product,ProductDto.class)).collect(Collectors.toList());
    }



    @Override
    public void deleteProduct(long id) {

        Product toBeDeleted = productRepo.findProductById(id).orElseThrow(() -> new ResourceException("Product to be deleted not found"));
        productRepo.delete(toBeDeleted);
    }

}
