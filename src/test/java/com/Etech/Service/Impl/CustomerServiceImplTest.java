package com.Etech.Service.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.Etech.Dto.ProductDto;
import com.Etech.Model.Product;
import com.Etech.Model.enums.ProductCategory;
import com.Etech.Model.enums.ProductStatus;
import com.Etech.Repository.ProductRepo;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CustomerServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CustomerServiceImplTest {
    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private ProductRepo productRepo;

    /**
     * Method under test: {@link CustomerServiceImpl#findAll()}
     */
    @Test
    void testFindAll() {
        when(productRepo.findAll()).thenReturn(new ArrayList<>());
        assertTrue(customerServiceImpl.findAll().isEmpty());
        verify(productRepo).findAll();
    }

    /**
     * Method under test: {@link CustomerServiceImpl#findAll()}
     */
    @Test
    void testFindAll2() {
        when(modelMapper.map((Object) any(), (Class<ProductDto>) any())).thenReturn(new ProductDto());

        Product product = new Product();
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setName("Name");
        product.setPrice(10.0d);
        product.setProductCategory(ProductCategory.FASHION);
        product.setProductStatus(ProductStatus.AVAILABLE);
        product.setQuantity(1);

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productRepo.findAll()).thenReturn(productList);
        assertEquals(1, customerServiceImpl.findAll().size());
        verify(modelMapper).map((Object) any(), (Class<ProductDto>) any());
        verify(productRepo).findAll();
    }

    /**
     * Method under test: {@link CustomerServiceImpl#findAll()}
     */
    @Test
    void testFindAll3() {
        when(modelMapper.map((Object) any(), (Class<ProductDto>) any())).thenReturn(new ProductDto());

        Product product = new Product();
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setName("Name");
        product.setPrice(10.0d);
        product.setProductCategory(ProductCategory.FASHION);
        product.setProductStatus(ProductStatus.AVAILABLE);
        product.setQuantity(1);

        Product product1 = new Product();
        product1.setDescription("Description");
        product1.setId(2L);
        product1.setName("com.Etech.Model.Product");
        product1.setPrice(0.5d);
        product1.setProductCategory(ProductCategory.BOOKS);
        product1.setProductStatus(ProductStatus.OUTOFSTOCK);
        product1.setQuantity(0);

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product);
        when(productRepo.findAll()).thenReturn(productList);
        assertEquals(2, customerServiceImpl.findAll().size());
        verify(modelMapper, atLeast(1)).map((Object) any(), (Class<ProductDto>) any());
        verify(productRepo).findAll();
    }
}

