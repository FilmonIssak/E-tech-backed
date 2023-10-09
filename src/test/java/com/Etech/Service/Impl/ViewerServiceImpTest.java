package com.Etech.Service.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.Etech.Dto.ProductDto;
import com.Etech.Exception.ResourceException;
import com.Etech.Model.Product;
import com.Etech.Model.enums.ProductCategory;
import com.Etech.Model.enums.ProductStatus;
import com.Etech.Repository.ProductRepo;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ViewerServiceImp.class})
@ExtendWith(SpringExtension.class)
class ViewerServiceImpTest {
    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private ProductRepo productRepo;

    @Autowired
    private ViewerServiceImp viewerServiceImp;

    /**
     * Method under test: {@link ViewerServiceImp#findProductByName(String)}
     */
    @Test
    void testFindProductByName() {
        ProductDto productDto = new ProductDto();
        when(modelMapper.map((Object) any(), (Class<ProductDto>) any())).thenReturn(productDto);

        Product product = new Product();
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setName("Name");
        product.setPrice(10.0d);
        product.setProductCategory(ProductCategory.FASHION);
        product.setProductStatus(ProductStatus.AVAILABLE);
        product.setQuantity(1);
        Optional<Product> ofResult = Optional.of(product);
        when(productRepo.getProductByName((String) any())).thenReturn(ofResult);
        assertSame(productDto, viewerServiceImp.findProductByName("Name"));
        verify(modelMapper).map((Object) any(), (Class<ProductDto>) any());
        verify(productRepo).getProductByName((String) any());
    }

    /**
     * Method under test: {@link ViewerServiceImp#findProductByName(String)}
     */
    @Test
    void testFindProductByName2() {
        when(modelMapper.map((Object) any(), (Class<ProductDto>) any())).thenThrow(new IllegalArgumentException());

        Product product = new Product();
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setName("Name");
        product.setPrice(10.0d);
        product.setProductCategory(ProductCategory.FASHION);
        product.setProductStatus(ProductStatus.AVAILABLE);
        product.setQuantity(1);
        Optional<Product> ofResult = Optional.of(product);
        when(productRepo.getProductByName((String) any())).thenReturn(ofResult);
        assertThrows(IllegalArgumentException.class, () -> viewerServiceImp.findProductByName("Name"));
        verify(modelMapper).map((Object) any(), (Class<ProductDto>) any());
        verify(productRepo).getProductByName((String) any());
    }

    /**
     * Method under test: {@link ViewerServiceImp#findProductByName(String)}
     */
    @Test
    void testFindProductByName3() {
        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<ProductDto>) any())).thenReturn(new ProductDto());
        when(productRepo.getProductByName((String) any())).thenReturn(Optional.empty());
        assertThrows(ResourceException.class, () -> viewerServiceImp.findProductByName("Name"));
        verify(modelMapper).map((Object) any(), (Class<Object>) any());
        verify(productRepo).getProductByName((String) any());
    }

    /**
     * Method under test: {@link ViewerServiceImp#getAllProducts()}
     */
    @Test
    void testGetAllProducts() {
        when(productRepo.findAll()).thenReturn(new ArrayList<>());
        assertTrue(viewerServiceImp.getAllProducts().isEmpty());
        verify(productRepo).findAll();
    }

    /**
     * Method under test: {@link ViewerServiceImp#getAllProducts()}
     */
    @Test
    void testGetAllProducts2() {
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
        assertEquals(1, viewerServiceImp.getAllProducts().size());
        verify(modelMapper).map((Object) any(), (Class<ProductDto>) any());
        verify(productRepo).findAll();
    }

    /**
     * Method under test: {@link ViewerServiceImp#getAllProducts()}
     */
    @Test
    void testGetAllProducts3() {
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
        assertEquals(2, viewerServiceImp.getAllProducts().size());
        verify(modelMapper, atLeast(1)).map((Object) any(), (Class<ProductDto>) any());
        verify(productRepo).findAll();
    }

    /**
     * Method under test: {@link ViewerServiceImp#getAllProducts()}
     */
    @Test
    void testGetAllProducts4() {
        when(modelMapper.map((Object) any(), (Class<ProductDto>) any())).thenThrow(new IllegalArgumentException());

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
        assertThrows(IllegalArgumentException.class, () -> viewerServiceImp.getAllProducts());
        verify(modelMapper).map((Object) any(), (Class<ProductDto>) any());
        verify(productRepo).findAll();
    }

    /**
     * Method under test: {@link ViewerServiceImp#findAllByCatagory(String)}
     */
    @Test
    void testFindAllByCatagory() throws RuntimeException {
        assertThrows(ResourceException.class, () -> viewerServiceImp.findAllByCatagory("Category"));
        assertThrows(ResourceException.class, () -> viewerServiceImp.findAllByCatagory("42"));
    }

    /**
     * Method under test: {@link ViewerServiceImp#findAllByKeyWord(String)}
     */
    @Test
    void testFindAllByKeyWord() {
        when(productRepo.findAllByKeyWord((String) any())).thenReturn(new ArrayList<>());
        assertTrue(viewerServiceImp.findAllByKeyWord("Key Word").isEmpty());
        verify(productRepo).findAllByKeyWord((String) any());
    }

    /**
     * Method under test: {@link ViewerServiceImp#findAllByKeyWord(String)}
     */
    @Test
    void testFindAllByKeyWord2() {
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
        when(productRepo.findAllByKeyWord((String) any())).thenReturn(productList);
        assertEquals(1, viewerServiceImp.findAllByKeyWord("Key Word").size());
        verify(modelMapper).map((Object) any(), (Class<ProductDto>) any());
        verify(productRepo).findAllByKeyWord((String) any());
    }

    /**
     * Method under test: {@link ViewerServiceImp#findAllByKeyWord(String)}
     */
    @Test
    void testFindAllByKeyWord3() {
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
        when(productRepo.findAllByKeyWord((String) any())).thenReturn(productList);
        assertEquals(2, viewerServiceImp.findAllByKeyWord("Key Word").size());
        verify(modelMapper, atLeast(1)).map((Object) any(), (Class<ProductDto>) any());
        verify(productRepo).findAllByKeyWord((String) any());
    }

    /**
     * Method under test: {@link ViewerServiceImp#findAllByKeyWord(String)}
     */
    @Test
    void testFindAllByKeyWord4() {
        when(modelMapper.map((Object) any(), (Class<ProductDto>) any())).thenThrow(new IllegalArgumentException());

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
        when(productRepo.findAllByKeyWord((String) any())).thenReturn(productList);
        assertThrows(IllegalArgumentException.class, () -> viewerServiceImp.findAllByKeyWord("Key Word"));
        verify(modelMapper).map((Object) any(), (Class<ProductDto>) any());
        verify(productRepo).findAllByKeyWord((String) any());
    }
}

