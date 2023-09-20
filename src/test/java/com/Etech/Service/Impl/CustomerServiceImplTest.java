package com.Etech.Service.Impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.Etech.Model.Product;
import com.Etech.Repository.ProductRepo;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
    private ProductRepo productRepo;

    /**
     * Method under test: {@link CustomerServiceImpl#findAll()}
     */
//    @Test
//    void testFindAll() {
//        ArrayList<Product> productList = new ArrayList<>();
//        when(productRepo.findAll()).thenReturn(productList);
//        List<Product> actualFindAllResult = customerServiceImpl.findAll();
//        assertSame(productList, actualFindAllResult);
//        assertTrue(actualFindAllResult.isEmpty());
//        verify(productRepo).findAll();
//    }
}

