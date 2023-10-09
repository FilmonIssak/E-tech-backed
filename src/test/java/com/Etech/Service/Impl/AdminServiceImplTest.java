package com.Etech.Service.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
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

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AdminServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AdminServiceImplTest {
    @Autowired
    private AdminServiceImpl adminServiceImpl;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private ProductRepo productRepo;

    /**
     * Method under test: {@link AdminServiceImpl#addProduct(ProductDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddProduct() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.Etech.Dto.ProductDto.getId()" because "productDto" is null
        //       at com.Etech.Service.Impl.AdminServiceImpl.addProduct(AdminServiceImpl.java:31)
        //   See https://diff.blue/R013 to resolve this issue.

        Product product = mock(Product.class);
        doNothing().when(product).setDescription((String) any());
        doNothing().when(product).setId((Long) any());
        doNothing().when(product).setName((String) any());
        doNothing().when(product).setPrice(anyDouble());
        doNothing().when(product).setProductCategory((ProductCategory) any());
        doNothing().when(product).setProductStatus((ProductStatus) any());
        doNothing().when(product).setQuantity(anyInt());
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setName("Name");
        product.setPrice(10.0d);
        product.setProductCategory(ProductCategory.FASHION);
        product.setProductStatus(ProductStatus.AVAILABLE);
        product.setQuantity(1);
        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn(product);
        Product product1 = mock(Product.class);
        doNothing().when(product1).setDescription((String) any());
        doNothing().when(product1).setId((Long) any());
        doNothing().when(product1).setName((String) any());
        doNothing().when(product1).setPrice(anyDouble());
        doNothing().when(product1).setProductCategory((ProductCategory) any());
        doNothing().when(product1).setProductStatus((ProductStatus) any());
        doNothing().when(product1).setQuantity(anyInt());
        product1.setDescription("The characteristics of someone or something");
        product1.setId(1L);
        product1.setName("Name");
        product1.setPrice(10.0d);
        product1.setProductCategory(ProductCategory.FASHION);
        product1.setProductStatus(ProductStatus.AVAILABLE);
        product1.setQuantity(1);
        when(productRepo.save((Product) any())).thenReturn(product1);
        adminServiceImpl.addProduct(null);
    }

    /**
     * Method under test: {@link AdminServiceImpl#addProduct(ProductDto)}
     */
    @Test
    void testAddProduct2() {
        Product product = mock(Product.class);
        doNothing().when(product).setDescription((String) any());
        doNothing().when(product).setId((Long) any());
        doNothing().when(product).setName((String) any());
        doNothing().when(product).setPrice(anyDouble());
        doNothing().when(product).setProductCategory((ProductCategory) any());
        doNothing().when(product).setProductStatus((ProductStatus) any());
        doNothing().when(product).setQuantity(anyInt());
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setName("Name");
        product.setPrice(10.0d);
        product.setProductCategory(ProductCategory.FASHION);
        product.setProductStatus(ProductStatus.AVAILABLE);
        product.setQuantity(1);
        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn(product);
        Product product1 = mock(Product.class);
        doNothing().when(product1).setDescription((String) any());
        doNothing().when(product1).setId((Long) any());
        doNothing().when(product1).setName((String) any());
        doNothing().when(product1).setPrice(anyDouble());
        doNothing().when(product1).setProductCategory((ProductCategory) any());
        doNothing().when(product1).setProductStatus((ProductStatus) any());
        doNothing().when(product1).setQuantity(anyInt());
        product1.setDescription("The characteristics of someone or something");
        product1.setId(1L);
        product1.setName("Name");
        product1.setPrice(10.0d);
        product1.setProductCategory(ProductCategory.FASHION);
        product1.setProductStatus(ProductStatus.AVAILABLE);
        product1.setQuantity(1);

        Product product2 = new Product();
        product2.setDescription("The characteristics of someone or something");
        product2.setId(1L);
        product2.setName("Name");
        product2.setPrice(10.0d);
        product2.setProductCategory(ProductCategory.FASHION);
        product2.setProductStatus(ProductStatus.AVAILABLE);
        product2.setQuantity(1);
        Optional<Product> ofResult = Optional.of(product2);
        when(productRepo.findProductById(anyLong())).thenReturn(ofResult);
        when(productRepo.save((Product) any())).thenReturn(product1);
        assertThrows(ResourceException.class, () -> adminServiceImpl.addProduct(new ProductDto(1L, "Name",
                "The characteristics of someone or something", 10.0d, 1, ProductStatus.AVAILABLE, ProductCategory.FASHION)));
        verify(product).setDescription((String) any());
        verify(product).setId((Long) any());
        verify(product).setName((String) any());
        verify(product).setPrice(anyDouble());
        verify(product).setProductCategory((ProductCategory) any());
        verify(product).setProductStatus((ProductStatus) any());
        verify(product).setQuantity(anyInt());
        verify(productRepo).findProductById(anyLong());
        verify(product1).setDescription((String) any());
        verify(product1).setId((Long) any());
        verify(product1).setName((String) any());
        verify(product1).setPrice(anyDouble());
        verify(product1).setProductCategory((ProductCategory) any());
        verify(product1).setProductStatus((ProductStatus) any());
        verify(product1).setQuantity(anyInt());
    }

    /**
     * Method under test: {@link AdminServiceImpl#addProduct(ProductDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddProduct3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Optional.isPresent()" because "toBeAdded" is null
        //       at com.Etech.Service.Impl.AdminServiceImpl.addProduct(AdminServiceImpl.java:36)
        //   See https://diff.blue/R013 to resolve this issue.

        Product product = mock(Product.class);
        doNothing().when(product).setDescription((String) any());
        doNothing().when(product).setId((Long) any());
        doNothing().when(product).setName((String) any());
        doNothing().when(product).setPrice(anyDouble());
        doNothing().when(product).setProductCategory((ProductCategory) any());
        doNothing().when(product).setProductStatus((ProductStatus) any());
        doNothing().when(product).setQuantity(anyInt());
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setName("Name");
        product.setPrice(10.0d);
        product.setProductCategory(ProductCategory.FASHION);
        product.setProductStatus(ProductStatus.AVAILABLE);
        product.setQuantity(1);
        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn(product);
        Product product1 = mock(Product.class);
        doNothing().when(product1).setDescription((String) any());
        doNothing().when(product1).setId((Long) any());
        doNothing().when(product1).setName((String) any());
        doNothing().when(product1).setPrice(anyDouble());
        doNothing().when(product1).setProductCategory((ProductCategory) any());
        doNothing().when(product1).setProductStatus((ProductStatus) any());
        doNothing().when(product1).setQuantity(anyInt());
        product1.setDescription("The characteristics of someone or something");
        product1.setId(1L);
        product1.setName("Name");
        product1.setPrice(10.0d);
        product1.setProductCategory(ProductCategory.FASHION);
        product1.setProductStatus(ProductStatus.AVAILABLE);
        product1.setQuantity(1);
        when(productRepo.findProductById(anyLong())).thenReturn(null);
        when(productRepo.save((Product) any())).thenReturn(product1);
        adminServiceImpl.addProduct(new ProductDto(1L, "Name", "The characteristics of someone or something", 10.0d, 1,
                ProductStatus.AVAILABLE, ProductCategory.FASHION));
    }

    /**
     * Method under test: {@link AdminServiceImpl#findProductById(long)}
     */
    @Test
    void testFindProductById() {
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
        when(productRepo.findProductById(anyLong())).thenReturn(ofResult);
        assertSame(productDto, adminServiceImpl.findProductById(1L));
        verify(modelMapper).map((Object) any(), (Class<ProductDto>) any());
        verify(productRepo).findProductById(anyLong());
    }

    /**
     * Method under test: {@link AdminServiceImpl#findProductById(long)}
     */
    @Test
    void testFindProductById2() {
        when(modelMapper.map((Object) any(), (Class<ProductDto>) any())).thenThrow(new ResourceException());

        Product product = new Product();
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setName("Name");
        product.setPrice(10.0d);
        product.setProductCategory(ProductCategory.FASHION);
        product.setProductStatus(ProductStatus.AVAILABLE);
        product.setQuantity(1);
        Optional<Product> ofResult = Optional.of(product);
        when(productRepo.findProductById(anyLong())).thenReturn(ofResult);
        assertThrows(ResourceException.class, () -> adminServiceImpl.findProductById(1L));
        verify(modelMapper).map((Object) any(), (Class<ProductDto>) any());
        verify(productRepo).findProductById(anyLong());
    }

    /**
     * Method under test: {@link AdminServiceImpl#findProductById(long)}
     */
    @Test
    void testFindProductById3() {
        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<ProductDto>) any())).thenReturn(new ProductDto());
        when(productRepo.findProductById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResourceException.class, () -> adminServiceImpl.findProductById(1L));
        verify(modelMapper).map((Object) any(), (Class<Object>) any());
        verify(productRepo).findProductById(anyLong());
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateProduct(long, ProductDto)}
     */
    @Test
    void testUpdateProduct() {
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

        Product product1 = new Product();
        product1.setDescription("The characteristics of someone or something");
        product1.setId(1L);
        product1.setName("Name");
        product1.setPrice(10.0d);
        product1.setProductCategory(ProductCategory.FASHION);
        product1.setProductStatus(ProductStatus.AVAILABLE);
        product1.setQuantity(1);
        when(productRepo.save((Product) any())).thenReturn(product1);
        when(productRepo.findProductById(anyLong())).thenReturn(ofResult);
        assertSame(productDto, adminServiceImpl.updateProduct(1L, new ProductDto()));
        verify(modelMapper).map((Object) any(), (Class<ProductDto>) any());
        verify(productRepo).save((Product) any());
        verify(productRepo).findProductById(anyLong());
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateProduct(long, ProductDto)}
     */
    @Test
    void testUpdateProduct2() {
        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<ProductDto>) any())).thenReturn(new ProductDto());

        Product product = new Product();
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setName("Name");
        product.setPrice(10.0d);
        product.setProductCategory(ProductCategory.FASHION);
        product.setProductStatus(ProductStatus.AVAILABLE);
        product.setQuantity(1);
        Optional<Product> ofResult = Optional.of(product);
        when(productRepo.save((Product) any())).thenThrow(new ResourceException());
        when(productRepo.findProductById(anyLong())).thenReturn(ofResult);
        assertThrows(ResourceException.class, () -> adminServiceImpl.updateProduct(1L, new ProductDto()));
        verify(modelMapper).map((Object) any(), (Class<Object>) any());
        verify(productRepo).save((Product) any());
        verify(productRepo).findProductById(anyLong());
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateProduct(long, ProductDto)}
     */
    @Test
    void testUpdateProduct3() {
        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<ProductDto>) any())).thenReturn(new ProductDto());
        when(productRepo.save((Product) any())).thenThrow(new ResourceException());
        when(productRepo.findProductById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResourceException.class, () -> adminServiceImpl.updateProduct(1L, new ProductDto()));
        verify(modelMapper).map((Object) any(), (Class<Object>) any());
        verify(productRepo).findProductById(anyLong());
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateProduct(long, ProductDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateProduct4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.Etech.Dto.ProductDto.getName()" because "productDto" is null
        //       at com.Etech.Service.Impl.AdminServiceImpl.updateProduct(AdminServiceImpl.java:54)
        //   See https://diff.blue/R013 to resolve this issue.

        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<ProductDto>) any())).thenReturn(new ProductDto());

        Product product = new Product();
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setName("Name");
        product.setPrice(10.0d);
        product.setProductCategory(ProductCategory.FASHION);
        product.setProductStatus(ProductStatus.AVAILABLE);
        product.setQuantity(1);
        Optional<Product> ofResult = Optional.of(product);
        when(productRepo.save((Product) any())).thenThrow(new ResourceException());
        when(productRepo.findProductById(anyLong())).thenReturn(ofResult);
        adminServiceImpl.updateProduct(1L, null);
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateProductDescription(long, ProductDto)}
     */
    @Test
    void testUpdateProductDescription() {
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

        Product product1 = new Product();
        product1.setDescription("The characteristics of someone or something");
        product1.setId(1L);
        product1.setName("Name");
        product1.setPrice(10.0d);
        product1.setProductCategory(ProductCategory.FASHION);
        product1.setProductStatus(ProductStatus.AVAILABLE);
        product1.setQuantity(1);
        when(productRepo.save((Product) any())).thenReturn(product1);
        when(productRepo.findProductById(anyLong())).thenReturn(ofResult);
        assertSame(productDto, adminServiceImpl.updateProductDescription(1L, new ProductDto()));
        verify(modelMapper).map((Object) any(), (Class<ProductDto>) any());
        verify(productRepo).save((Product) any());
        verify(productRepo).findProductById(anyLong());
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateProductDescription(long, ProductDto)}
     */
    @Test
    void testUpdateProductDescription2() {
        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<ProductDto>) any())).thenReturn(new ProductDto());

        Product product = new Product();
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setName("Name");
        product.setPrice(10.0d);
        product.setProductCategory(ProductCategory.FASHION);
        product.setProductStatus(ProductStatus.AVAILABLE);
        product.setQuantity(1);
        Optional<Product> ofResult = Optional.of(product);
        when(productRepo.save((Product) any())).thenThrow(new ResourceException());
        when(productRepo.findProductById(anyLong())).thenReturn(ofResult);
        assertThrows(ResourceException.class, () -> adminServiceImpl.updateProductDescription(1L, new ProductDto()));
        verify(modelMapper).map((Object) any(), (Class<Object>) any());
        verify(productRepo).save((Product) any());
        verify(productRepo).findProductById(anyLong());
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateProductDescription(long, ProductDto)}
     */
    @Test
    void testUpdateProductDescription3() {
        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<ProductDto>) any())).thenReturn(new ProductDto());
        when(productRepo.save((Product) any())).thenThrow(new ResourceException());
        when(productRepo.findProductById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResourceException.class, () -> adminServiceImpl.updateProductDescription(1L, new ProductDto()));
        verify(modelMapper).map((Object) any(), (Class<Object>) any());
        verify(productRepo).findProductById(anyLong());
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateProductDescription(long, ProductDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateProductDescription4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.Etech.Dto.ProductDto.getDescription()" because "productDto" is null
        //       at com.Etech.Service.Impl.AdminServiceImpl.updateProductDescription(AdminServiceImpl.java:66)
        //   See https://diff.blue/R013 to resolve this issue.

        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<ProductDto>) any())).thenReturn(new ProductDto());

        Product product = new Product();
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setName("Name");
        product.setPrice(10.0d);
        product.setProductCategory(ProductCategory.FASHION);
        product.setProductStatus(ProductStatus.AVAILABLE);
        product.setQuantity(1);
        Optional<Product> ofResult = Optional.of(product);
        when(productRepo.save((Product) any())).thenThrow(new ResourceException());
        when(productRepo.findProductById(anyLong())).thenReturn(ofResult);
        adminServiceImpl.updateProductDescription(1L, null);
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateProductPrice(long, ProductDto)}
     */
    @Test
    void testUpdateProductPrice() {
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

        Product product1 = new Product();
        product1.setDescription("The characteristics of someone or something");
        product1.setId(1L);
        product1.setName("Name");
        product1.setPrice(10.0d);
        product1.setProductCategory(ProductCategory.FASHION);
        product1.setProductStatus(ProductStatus.AVAILABLE);
        product1.setQuantity(1);
        when(productRepo.save((Product) any())).thenReturn(product1);
        when(productRepo.findProductById(anyLong())).thenReturn(ofResult);
        assertSame(productDto, adminServiceImpl.updateProductPrice(1L, new ProductDto()));
        verify(modelMapper).map((Object) any(), (Class<ProductDto>) any());
        verify(productRepo).save((Product) any());
        verify(productRepo).findProductById(anyLong());
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateProductPrice(long, ProductDto)}
     */
    @Test
    void testUpdateProductPrice2() {
        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<ProductDto>) any())).thenReturn(new ProductDto());

        Product product = new Product();
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setName("Name");
        product.setPrice(10.0d);
        product.setProductCategory(ProductCategory.FASHION);
        product.setProductStatus(ProductStatus.AVAILABLE);
        product.setQuantity(1);
        Optional<Product> ofResult = Optional.of(product);
        when(productRepo.save((Product) any())).thenThrow(new ResourceException());
        when(productRepo.findProductById(anyLong())).thenReturn(ofResult);
        assertThrows(ResourceException.class, () -> adminServiceImpl.updateProductPrice(1L, new ProductDto()));
        verify(modelMapper).map((Object) any(), (Class<Object>) any());
        verify(productRepo).save((Product) any());
        verify(productRepo).findProductById(anyLong());
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateProductPrice(long, ProductDto)}
     */
    @Test
    void testUpdateProductPrice3() {
        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<ProductDto>) any())).thenReturn(new ProductDto());
        when(productRepo.save((Product) any())).thenThrow(new ResourceException());
        when(productRepo.findProductById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResourceException.class, () -> adminServiceImpl.updateProductPrice(1L, new ProductDto()));
        verify(modelMapper).map((Object) any(), (Class<Object>) any());
        verify(productRepo).findProductById(anyLong());
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateProductPrice(long, ProductDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateProductPrice4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.Etech.Dto.ProductDto.getPrice()" because "productDto" is null
        //       at com.Etech.Service.Impl.AdminServiceImpl.updateProductPrice(AdminServiceImpl.java:75)
        //   See https://diff.blue/R013 to resolve this issue.

        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<ProductDto>) any())).thenReturn(new ProductDto());

        Product product = new Product();
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setName("Name");
        product.setPrice(10.0d);
        product.setProductCategory(ProductCategory.FASHION);
        product.setProductStatus(ProductStatus.AVAILABLE);
        product.setQuantity(1);
        Optional<Product> ofResult = Optional.of(product);
        when(productRepo.save((Product) any())).thenThrow(new ResourceException());
        when(productRepo.findProductById(anyLong())).thenReturn(ofResult);
        adminServiceImpl.updateProductPrice(1L, null);
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateProductCategory(long, ProductDto)}
     */
    @Test
    void testUpdateProductCategory() {
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

        Product product1 = new Product();
        product1.setDescription("The characteristics of someone or something");
        product1.setId(1L);
        product1.setName("Name");
        product1.setPrice(10.0d);
        product1.setProductCategory(ProductCategory.FASHION);
        product1.setProductStatus(ProductStatus.AVAILABLE);
        product1.setQuantity(1);
        when(productRepo.save((Product) any())).thenReturn(product1);
        when(productRepo.findProductById(anyLong())).thenReturn(ofResult);
        assertSame(productDto, adminServiceImpl.updateProductCategory(1L, new ProductDto()));
        verify(modelMapper).map((Object) any(), (Class<ProductDto>) any());
        verify(productRepo).save((Product) any());
        verify(productRepo).findProductById(anyLong());
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateProductCategory(long, ProductDto)}
     */
    @Test
    void testUpdateProductCategory2() {
        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<ProductDto>) any())).thenReturn(new ProductDto());

        Product product = new Product();
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setName("Name");
        product.setPrice(10.0d);
        product.setProductCategory(ProductCategory.FASHION);
        product.setProductStatus(ProductStatus.AVAILABLE);
        product.setQuantity(1);
        Optional<Product> ofResult = Optional.of(product);
        when(productRepo.save((Product) any())).thenThrow(new ResourceException());
        when(productRepo.findProductById(anyLong())).thenReturn(ofResult);
        assertThrows(ResourceException.class, () -> adminServiceImpl.updateProductCategory(1L, new ProductDto()));
        verify(modelMapper).map((Object) any(), (Class<Object>) any());
        verify(productRepo).save((Product) any());
        verify(productRepo).findProductById(anyLong());
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateProductCategory(long, ProductDto)}
     */
    @Test
    void testUpdateProductCategory3() {
        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<ProductDto>) any())).thenReturn(new ProductDto());
        when(productRepo.save((Product) any())).thenThrow(new ResourceException());
        when(productRepo.findProductById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResourceException.class, () -> adminServiceImpl.updateProductCategory(1L, new ProductDto()));
        verify(modelMapper).map((Object) any(), (Class<Object>) any());
        verify(productRepo).findProductById(anyLong());
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateProductCategory(long, ProductDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateProductCategory4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.Etech.Dto.ProductDto.getProductCategory()" because "productDto" is null
        //       at com.Etech.Service.Impl.AdminServiceImpl.updateProductCategory(AdminServiceImpl.java:83)
        //   See https://diff.blue/R013 to resolve this issue.

        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<ProductDto>) any())).thenReturn(new ProductDto());

        Product product = new Product();
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setName("Name");
        product.setPrice(10.0d);
        product.setProductCategory(ProductCategory.FASHION);
        product.setProductStatus(ProductStatus.AVAILABLE);
        product.setQuantity(1);
        Optional<Product> ofResult = Optional.of(product);
        when(productRepo.save((Product) any())).thenThrow(new ResourceException());
        when(productRepo.findProductById(anyLong())).thenReturn(ofResult);
        adminServiceImpl.updateProductCategory(1L, null);
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateProductStatus(long, ProductDto)}
     */
    @Test
    void testUpdateProductStatus() {
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

        Product product1 = new Product();
        product1.setDescription("The characteristics of someone or something");
        product1.setId(1L);
        product1.setName("Name");
        product1.setPrice(10.0d);
        product1.setProductCategory(ProductCategory.FASHION);
        product1.setProductStatus(ProductStatus.AVAILABLE);
        product1.setQuantity(1);
        when(productRepo.save((Product) any())).thenReturn(product1);
        when(productRepo.findProductById(anyLong())).thenReturn(ofResult);
        assertSame(productDto, adminServiceImpl.updateProductStatus(1L, new ProductDto()));
        verify(modelMapper).map((Object) any(), (Class<ProductDto>) any());
        verify(productRepo).save((Product) any());
        verify(productRepo).findProductById(anyLong());
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateProductStatus(long, ProductDto)}
     */
    @Test
    void testUpdateProductStatus2() {
        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<ProductDto>) any())).thenReturn(new ProductDto());

        Product product = new Product();
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setName("Name");
        product.setPrice(10.0d);
        product.setProductCategory(ProductCategory.FASHION);
        product.setProductStatus(ProductStatus.AVAILABLE);
        product.setQuantity(1);
        Optional<Product> ofResult = Optional.of(product);
        when(productRepo.save((Product) any())).thenThrow(new ResourceException());
        when(productRepo.findProductById(anyLong())).thenReturn(ofResult);
        assertThrows(ResourceException.class, () -> adminServiceImpl.updateProductStatus(1L, new ProductDto()));
        verify(modelMapper).map((Object) any(), (Class<Object>) any());
        verify(productRepo).save((Product) any());
        verify(productRepo).findProductById(anyLong());
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateProductStatus(long, ProductDto)}
     */
    @Test
    void testUpdateProductStatus3() {
        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<ProductDto>) any())).thenReturn(new ProductDto());
        when(productRepo.save((Product) any())).thenThrow(new ResourceException());
        when(productRepo.findProductById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResourceException.class, () -> adminServiceImpl.updateProductStatus(1L, new ProductDto()));
        verify(modelMapper).map((Object) any(), (Class<Object>) any());
        verify(productRepo).findProductById(anyLong());
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateProductStatus(long, ProductDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateProductStatus4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.Etech.Dto.ProductDto.getProductStatus()" because "productDto" is null
        //       at com.Etech.Service.Impl.AdminServiceImpl.updateProductStatus(AdminServiceImpl.java:92)
        //   See https://diff.blue/R013 to resolve this issue.

        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<ProductDto>) any())).thenReturn(new ProductDto());

        Product product = new Product();
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setName("Name");
        product.setPrice(10.0d);
        product.setProductCategory(ProductCategory.FASHION);
        product.setProductStatus(ProductStatus.AVAILABLE);
        product.setQuantity(1);
        Optional<Product> ofResult = Optional.of(product);
        when(productRepo.save((Product) any())).thenThrow(new ResourceException());
        when(productRepo.findProductById(anyLong())).thenReturn(ofResult);
        adminServiceImpl.updateProductStatus(1L, null);
    }

    /**
     * Method under test: {@link AdminServiceImpl#findAllProduct()}
     */
    @Test
    void testFindAllProduct() {
        when(productRepo.findAll()).thenReturn(new ArrayList<>());
        assertTrue(adminServiceImpl.findAllProduct().isEmpty());
        verify(productRepo).findAll();
    }

    /**
     * Method under test: {@link AdminServiceImpl#findAllProduct()}
     */
    @Test
    void testFindAllProduct2() {
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
        assertEquals(1, adminServiceImpl.findAllProduct().size());
        verify(modelMapper).map((Object) any(), (Class<ProductDto>) any());
        verify(productRepo).findAll();
    }

    /**
     * Method under test: {@link AdminServiceImpl#findAllProduct()}
     */
    @Test
    void testFindAllProduct3() {
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
        assertEquals(2, adminServiceImpl.findAllProduct().size());
        verify(modelMapper, atLeast(1)).map((Object) any(), (Class<ProductDto>) any());
        verify(productRepo).findAll();
    }

    /**
     * Method under test: {@link AdminServiceImpl#findAllProduct()}
     */
    @Test
    void testFindAllProduct4() {
        when(modelMapper.map((Object) any(), (Class<ProductDto>) any())).thenThrow(new ResourceException());

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
        assertThrows(ResourceException.class, () -> adminServiceImpl.findAllProduct());
        verify(modelMapper).map((Object) any(), (Class<ProductDto>) any());
        verify(productRepo).findAll();
    }

    /**
     * Method under test: {@link AdminServiceImpl#deleteProduct(long)}
     */
    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setName("Name");
        product.setPrice(10.0d);
        product.setProductCategory(ProductCategory.FASHION);
        product.setProductStatus(ProductStatus.AVAILABLE);
        product.setQuantity(1);
        Optional<Product> ofResult = Optional.of(product);
        doNothing().when(productRepo).delete((Product) any());
        when(productRepo.findProductById(anyLong())).thenReturn(ofResult);
        adminServiceImpl.deleteProduct(1L);
        verify(productRepo).findProductById(anyLong());
        verify(productRepo).delete((Product) any());
    }

    /**
     * Method under test: {@link AdminServiceImpl#deleteProduct(long)}
     */
    @Test
    void testDeleteProduct2() {
        Product product = new Product();
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setName("Name");
        product.setPrice(10.0d);
        product.setProductCategory(ProductCategory.FASHION);
        product.setProductStatus(ProductStatus.AVAILABLE);
        product.setQuantity(1);
        Optional<Product> ofResult = Optional.of(product);
        doThrow(new ResourceException()).when(productRepo).delete((Product) any());
        when(productRepo.findProductById(anyLong())).thenReturn(ofResult);
        assertThrows(ResourceException.class, () -> adminServiceImpl.deleteProduct(1L));
        verify(productRepo).findProductById(anyLong());
        verify(productRepo).delete((Product) any());
    }

    /**
     * Method under test: {@link AdminServiceImpl#deleteProduct(long)}
     */
    @Test
    void testDeleteProduct3() {
        doNothing().when(productRepo).delete((Product) any());
        when(productRepo.findProductById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResourceException.class, () -> adminServiceImpl.deleteProduct(1L));
        verify(productRepo).findProductById(anyLong());
    }
}

