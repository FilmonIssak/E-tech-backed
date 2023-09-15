package com.Etech.Service.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.Etech.Model.Product;
import com.Etech.Repository.ProductRepo;

import java.util.ArrayList;
import java.util.List;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
        when(productRepo.save((Product) any())).thenReturn(new Product());
        Product product = new Product();
        adminServiceImpl.addProduct(product);
        verify(productRepo).save((Product) any());
        assertEquals(0.0d, product.getPrice());
        assertTrue(adminServiceImpl.findAllProduct().isEmpty());
    }

    /**
     * Method under test: {@link AdminServiceImpl#addProduct(Product)}
     */
    @Test
    void testAddProduct2() {
        when(productRepo.save((Product) any())).thenThrow(new NoSuchElementException());
        assertThrows(NoSuchElementException.class, () -> adminServiceImpl.addProduct(new Product()));
        verify(productRepo).save((Product) any());
    }

    /**
     * Method under test: {@link AdminServiceImpl#findProductById(long)}
     */
    @Test
    void testFindProductById() {
        Product product = new Product();
        when(productRepo.findById((Long) any())).thenReturn(Optional.of(product));
        assertSame(product, adminServiceImpl.findProductById(1L));
        verify(productRepo).findById((Long) any());
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
        //       at java.util.Optional.get(Optional.java:143)
        //       at com.Etech.Service.Impl.AdminServiceImpl.findProductById(AdminServiceImpl.java:31)
        //   See https://diff.blue/R013 to resolve this issue.

        when(productRepo.findById((Long) any())).thenReturn(Optional.empty());
        adminServiceImpl.findProductById(1L);
    }

    /**
     * Method under test: {@link AdminServiceImpl#findProductById(long)}
     */
    @Test
    void testFindProductById3() {
        when(productRepo.findById((Long) any())).thenThrow(new NoSuchElementException());
        assertThrows(NoSuchElementException.class, () -> adminServiceImpl.findProductById(1L));
        verify(productRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateProductDescription(long, String)}
     */
    @Test
    void testUpdateProductDescription() {
        Product product = new Product();
        when(productRepo.save((Product) any())).thenReturn(product);
        when(productRepo.findById((Long) any())).thenReturn(Optional.of(new Product()));
        assertSame(product, adminServiceImpl.updateProductDescription(1L, "The characteristics of someone or something"));
        verify(productRepo).save((Product) any());
        verify(productRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateProductDescription(long, String)}
     */
    @Test
    void testUpdateProductDescription2() {
        when(productRepo.save((Product) any())).thenThrow(new NoSuchElementException());
        when(productRepo.findById((Long) any())).thenReturn(Optional.of(new Product()));
        assertThrows(NoSuchElementException.class,
                () -> adminServiceImpl.updateProductDescription(1L, "The characteristics of someone or something"));
        verify(productRepo).save((Product) any());
        verify(productRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateProductDescription(long, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateProductDescription3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException
        //       at com.Etech.Model.Product.setDescription(Product.java:9)
        //       at com.Etech.Service.Impl.AdminServiceImpl.updateProductDescription(AdminServiceImpl.java:38)
        //   See https://diff.blue/R013 to resolve this issue.

        Product product = mock(Product.class);
        doThrow(new NoSuchElementException()).when(product).setDescription((String) any());
        Optional<Product> ofResult = Optional.of(product);
        when(productRepo.save((Product) any())).thenReturn(new Product());
        when(productRepo.findById((Long) any())).thenReturn(ofResult);
        adminServiceImpl.updateProductDescription(1L, "The characteristics of someone or something");
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateProductDescription(long, String)}
     */
    @Test
    void testUpdateProductDescription4() {
        when(productRepo.save((Product) any())).thenReturn(new Product());
        when(productRepo.findById((Long) any())).thenReturn(Optional.empty());
        new NoSuchElementException();
        assertThrows(NoSuchElementException.class,
                () -> adminServiceImpl.updateProductDescription(1L, "The characteristics of someone or something"));
        verify(productRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateProductPrice(long, double)}
     */
    @Test
    void testUpdateProductPrice() {
        Product product = new Product();
        when(productRepo.save((Product) any())).thenReturn(product);
        when(productRepo.findById((Long) any())).thenReturn(Optional.of(new Product()));
        assertSame(product, adminServiceImpl.updateProductPrice(1L, 10.0d));
        verify(productRepo).save((Product) any());
        verify(productRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateProductPrice(long, double)}
     */
    @Test
    void testUpdateProductPrice2() {
        when(productRepo.save((Product) any())).thenThrow(new NoSuchElementException());
        when(productRepo.findById((Long) any())).thenReturn(Optional.of(new Product()));
        assertThrows(NoSuchElementException.class, () -> adminServiceImpl.updateProductPrice(1L, 10.0d));
        verify(productRepo).save((Product) any());
        verify(productRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateProductPrice(long, double)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateProductPrice3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException
        //       at com.Etech.Model.Product.setPrice(Product.java:9)
        //       at com.Etech.Service.Impl.AdminServiceImpl.updateProductPrice(AdminServiceImpl.java:47)
        //   See https://diff.blue/R013 to resolve this issue.

        Product product = mock(Product.class);
        doThrow(new NoSuchElementException()).when(product).setPrice(anyDouble());
        Optional<Product> ofResult = Optional.of(product);
        when(productRepo.save((Product) any())).thenReturn(new Product());
        when(productRepo.findById((Long) any())).thenReturn(ofResult);
        adminServiceImpl.updateProductPrice(1L, 10.0d);
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateProductPrice(long, double)}
     */
    @Test
    void testUpdateProductPrice4() {
        when(productRepo.save((Product) any())).thenReturn(new Product());
        when(productRepo.findById((Long) any())).thenReturn(Optional.empty());
        new NoSuchElementException();
        assertThrows(NoSuchElementException.class, () -> adminServiceImpl.updateProductPrice(1L, 10.0d));
        verify(productRepo).findById((Long) any());
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
        when(productRepo.findAll()).thenThrow(new NoSuchElementException());
        assertThrows(NoSuchElementException.class, () -> adminServiceImpl.findAllProduct());
        verify(productRepo).findAll();
    }

    /**
     * Method under test: {@link AdminServiceImpl#deleteProduct(long)}
     */
    @Test
    void testDeleteProduct() {
        doNothing().when(productRepo).deleteById((Long) any());
        adminServiceImpl.deleteProduct(1L);
        verify(productRepo).deleteById((Long) any());
        assertTrue(adminServiceImpl.findAllProduct().isEmpty());
    }

    /**
     * Method under test: {@link AdminServiceImpl#deleteProduct(long)}
     */
    @Test
    void testDeleteProduct2() {
        doThrow(new NoSuchElementException()).when(productRepo).deleteById((Long) any());
        assertThrows(NoSuchElementException.class, () -> adminServiceImpl.deleteProduct(1L));
        verify(productRepo).deleteById((Long) any());
    }
}

