package com.Etech.Service.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.Etech.Model.Product;
import com.Etech.Model.enums.ProductCategory;
import com.Etech.Repository.ProductRepo;

import java.util.ArrayList;
import java.util.List;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
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
    private ProductRepo productRepo;

    /**
     * Method under test: {@link AdminServiceImpl#addProduct(Product)}
     */
    @Test
    void testAddProduct() {
        Product product = new Product();
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setImage("Image");
        product.setName("Name");
        product.setPrice(10.0d);
        product.setProductCategory(ProductCategory.FASHION);
        product.setQuantity("Quantity");
        product.setStatus("Status");
        when(productRepo.save(Mockito.<Product>any())).thenReturn(product);

        Product product2 = new Product();
        product2.setDescription("The characteristics of someone or something");
        product2.setId(1L);
        product2.setImage("Image");
        product2.setName("Name");
        product2.setPrice(10.0d);
        product2.setProductCategory(ProductCategory.FASHION);
        product2.setQuantity("Quantity");
        product2.setStatus("Status");
        adminServiceImpl.addProduct(product2);
        verify(productRepo).save(Mockito.<Product>any());
        assertEquals("The characteristics of someone or something", product2.getDescription());
        assertEquals("Status", product2.getStatus());
        assertEquals("Quantity", product2.getQuantity());
        assertEquals(ProductCategory.FASHION, product2.getProductCategory());
        assertEquals(10.0d, product2.getPrice());
        assertEquals("Name", product2.getName());
        assertEquals("Image", product2.getImage());
        assertEquals(1L, product2.getId().longValue());
        assertTrue(adminServiceImpl.findAllProduct().isEmpty());
    }

    /**
     * Method under test: {@link AdminServiceImpl#addProduct(Product)}
     */
    @Test
    void testAddProduct2() {
        when(productRepo.save(Mockito.<Product>any())).thenThrow(new NoSuchElementException("foo"));

        Product product = new Product();
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setImage("Image");
        product.setName("Name");
        product.setPrice(10.0d);
        product.setProductCategory(ProductCategory.FASHION);
        product.setQuantity("Quantity");
        product.setStatus("Status");
        assertThrows(NoSuchElementException.class, () -> adminServiceImpl.addProduct(product));
        verify(productRepo).save(Mockito.<Product>any());
    }

    /**
     * Method under test: {@link AdminServiceImpl#findProductById(long)}
     */
    @Test
    void testFindProductById() {
        Product product = new Product();
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setImage("Image");
        product.setName("Name");
        product.setPrice(10.0d);
        product.setProductCategory(ProductCategory.FASHION);
        product.setQuantity("Quantity");
        product.setStatus("Status");
        Optional<Product> ofResult = Optional.of(product);
        when(productRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertSame(product, adminServiceImpl.findProductById(1L));
        verify(productRepo).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AdminServiceImpl#findProductById(long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindProductById2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.base/java.util.Optional.get(Optional.java:143)
        //       at com.Etech.Service.Impl.AdminServiceImpl.findProductById(AdminServiceImpl.java:31)
        //   See https://diff.blue/R013 to resolve this issue.

        Optional<Product> emptyResult = Optional.empty();
        when(productRepo.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        adminServiceImpl.findProductById(1L);
    }

    /**
     * Method under test: {@link AdminServiceImpl#findProductById(long)}
     */
    @Test
    void testFindProductById3() {
        when(productRepo.findById(Mockito.<Long>any())).thenThrow(new NoSuchElementException("foo"));
        assertThrows(NoSuchElementException.class, () -> adminServiceImpl.findProductById(1L));
        verify(productRepo).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateProductDescription(long, String)}
     */
    @Test
    void testUpdateProductDescription() {
        Product product = new Product();
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setImage("Image");
        product.setName("Name");
        product.setPrice(10.0d);
        product.setProductCategory(ProductCategory.FASHION);
        product.setQuantity("Quantity");
        product.setStatus("Status");
        Optional<Product> ofResult = Optional.of(product);

        Product product2 = new Product();
        product2.setDescription("The characteristics of someone or something");
        product2.setId(1L);
        product2.setImage("Image");
        product2.setName("Name");
        product2.setPrice(10.0d);
        product2.setProductCategory(ProductCategory.FASHION);
        product2.setQuantity("Quantity");
        product2.setStatus("Status");
        when(productRepo.save(Mockito.<Product>any())).thenReturn(product2);
        when(productRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertSame(product2,
                adminServiceImpl.updateProductDescription(1L, "The characteristics of someone or something"));
        verify(productRepo).save(Mockito.<Product>any());
        verify(productRepo).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateProductDescription(long, String)}
     */
    @Test
    void testUpdateProductDescription2() {
        Product product = new Product();
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setImage("Image");
        product.setName("Name");
        product.setPrice(10.0d);
        product.setProductCategory(ProductCategory.FASHION);
        product.setQuantity("Quantity");
        product.setStatus("Status");
        Optional<Product> ofResult = Optional.of(product);
        when(productRepo.save(Mockito.<Product>any())).thenThrow(new NoSuchElementException("foo"));
        when(productRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(NoSuchElementException.class,
                () -> adminServiceImpl.updateProductDescription(1L, "The characteristics of someone or something"));
        verify(productRepo).save(Mockito.<Product>any());
        verify(productRepo).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateProductDescription(long, String)}
     */
    @Test
    void testUpdateProductDescription3() {
        Optional<Product> emptyResult = Optional.empty();
        when(productRepo.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        assertThrows(NoSuchElementException.class,
                () -> adminServiceImpl.updateProductDescription(1L, "The characteristics of someone or something"));
        verify(productRepo).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateProductPrice(long, double)}
     */
    @Test
    void testUpdateProductPrice() {
        Product product = new Product();
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setImage("Image");
        product.setName("Name");
        product.setPrice(10.0d);
        product.setProductCategory(ProductCategory.FASHION);
        product.setQuantity("Quantity");
        product.setStatus("Status");
        Optional<Product> ofResult = Optional.of(product);

        Product product2 = new Product();
        product2.setDescription("The characteristics of someone or something");
        product2.setId(1L);
        product2.setImage("Image");
        product2.setName("Name");
        product2.setPrice(10.0d);
        product2.setProductCategory(ProductCategory.FASHION);
        product2.setQuantity("Quantity");
        product2.setStatus("Status");
        when(productRepo.save(Mockito.<Product>any())).thenReturn(product2);
        when(productRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertSame(product2, adminServiceImpl.updateProductPrice(1L, 10.0d));
        verify(productRepo).save(Mockito.<Product>any());
        verify(productRepo).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateProductPrice(long, double)}
     */
    @Test
    void testUpdateProductPrice2() {
        Product product = new Product();
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setImage("Image");
        product.setName("Name");
        product.setPrice(10.0d);
        product.setProductCategory(ProductCategory.FASHION);
        product.setQuantity("Quantity");
        product.setStatus("Status");
        Optional<Product> ofResult = Optional.of(product);
        when(productRepo.save(Mockito.<Product>any())).thenThrow(new NoSuchElementException("foo"));
        when(productRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(NoSuchElementException.class, () -> adminServiceImpl.updateProductPrice(1L, 10.0d));
        verify(productRepo).save(Mockito.<Product>any());
        verify(productRepo).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateProductPrice(long, double)}
     */
    @Test
    void testUpdateProductPrice3() {
        Optional<Product> emptyResult = Optional.empty();
        when(productRepo.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        assertThrows(NoSuchElementException.class, () -> adminServiceImpl.updateProductPrice(1L, 10.0d));
        verify(productRepo).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AdminServiceImpl#findAllProduct()}
     */
    @Test
    void testFindAllProduct() {
        ArrayList<Product> productList = new ArrayList<>();
        when(productRepo.findAll()).thenReturn(productList);
        List<Product> actualFindAllProductResult = adminServiceImpl.findAllProduct();
        assertSame(productList, actualFindAllProductResult);
        assertTrue(actualFindAllProductResult.isEmpty());
        verify(productRepo).findAll();
    }

    /**
     * Method under test: {@link AdminServiceImpl#findAllProduct()}
     */
    @Test
    void testFindAllProduct2() {
        when(productRepo.findAll()).thenThrow(new NoSuchElementException("foo"));
        assertThrows(NoSuchElementException.class, () -> adminServiceImpl.findAllProduct());
        verify(productRepo).findAll();
    }

    /**
     * Method under test: {@link AdminServiceImpl#deleteProduct(long)}
     */
    @Test
    void testDeleteProduct() {
        doNothing().when(productRepo).deleteById(Mockito.<Long>any());
        adminServiceImpl.deleteProduct(1L);
        verify(productRepo).deleteById(Mockito.<Long>any());
        assertTrue(adminServiceImpl.findAllProduct().isEmpty());
    }

    /**
     * Method under test: {@link AdminServiceImpl#deleteProduct(long)}
     */
    @Test
    void testDeleteProduct2() {
        doThrow(new NoSuchElementException("foo")).when(productRepo).deleteById(Mockito.<Long>any());
        assertThrows(NoSuchElementException.class, () -> adminServiceImpl.deleteProduct(1L));
        verify(productRepo).deleteById(Mockito.<Long>any());
    }
}

