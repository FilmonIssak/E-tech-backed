package com.Etech.Service.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyDouble;
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
import com.Etech.Repository.ProductRepo;

import java.util.ArrayList;
import java.util.NoSuchElementException;

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
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Long.longValue()" because the return value of "com.Etech.Dto.ProductDto.getId()" is null
        //       at com.Etech.Service.Impl.AdminServiceImpl.addProduct(AdminServiceImpl.java:33)
        //   See https://diff.blue/R013 to resolve this issue.

        adminServiceImpl.addProduct(new ProductDto());
    }

    /**
     * Method under test: {@link AdminServiceImpl#addProduct(ProductDto)}
     */
    @Test
    void testAddProduct2() {
        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(productRepo.findProductBy(anyLong())).thenReturn(Optional.of(new Product()));

        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        assertThrows(ResourceException.class, () -> adminServiceImpl.addProduct(productDto));
        verify(productRepo).findProductBy(anyLong());
    }

    /**
     * Method under test: {@link AdminServiceImpl#addProduct(ProductDto)}
     */
    @Test
    void testAddProduct3() {
        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(productRepo.findProductBy(anyLong())).thenThrow(new ResourceException());

        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        assertThrows(ResourceException.class, () -> adminServiceImpl.addProduct(productDto));
        verify(productRepo).findProductBy(anyLong());
    }

    /**
     * Method under test: {@link AdminServiceImpl#addProduct(ProductDto)}
     */
    @Test
    void testAddProduct4() {
        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn(null);
        when(productRepo.save((Product) any())).thenReturn(new Product());
        when(productRepo.findProductBy(anyLong())).thenReturn(Optional.empty());

        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        assertNull(adminServiceImpl.addProduct(productDto));
        verify(modelMapper, atLeast(1)).map((Object) any(), (Class<Object>) any());
        verify(productRepo).save((Product) any());
        verify(productRepo).findProductBy(anyLong());
    }

    /**
     * Method under test: {@link AdminServiceImpl#findProductById(long)}
     */
    @Test
    void testFindProductById() {
        ProductDto productDto = new ProductDto();
        when(modelMapper.map((Object) any(), (Class<ProductDto>) any())).thenReturn(productDto);
        when(productRepo.findProductBy(anyLong())).thenReturn(Optional.of(new Product()));
        assertSame(productDto, adminServiceImpl.findProductById(1L));
        verify(modelMapper).map((Object) any(), (Class<ProductDto>) any());
        verify(productRepo).findProductBy(anyLong());
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
        //   com.Etech.Exception.ResourceException
        //       at org.modelmapper.ModelMapper.map(ModelMapper.java:404)
        //       at com.Etech.Service.Impl.AdminServiceImpl.findProductById(AdminServiceImpl.java:45)
        //   See https://diff.blue/R013 to resolve this issue.

        when(modelMapper.map((Object) any(), (Class<ProductDto>) any())).thenThrow(new ResourceException());
        when(productRepo.findProductBy(anyLong())).thenReturn(Optional.of(new Product()));
        adminServiceImpl.findProductById(1L);
    }

    /**
     * Method under test: {@link AdminServiceImpl#findProductById(long)}
     */
    @Test
    void testFindProductById3() {
        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<ProductDto>) any())).thenReturn(new ProductDto());
        when(productRepo.findProductBy(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResourceException.class, () -> adminServiceImpl.findProductById(1L));
        verify(modelMapper).map((Object) any(), (Class<Object>) any());
        verify(productRepo).findProductBy(anyLong());
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateProductDescription(long, String)}
     */
    @Test
    void testUpdateProductDescription() {
        ProductDto productDto = new ProductDto();
        when(modelMapper.map((Object) any(), (Class<ProductDto>) any())).thenReturn(productDto);
        when(productRepo.save((Product) any())).thenReturn(new Product());
        when(productRepo.findById((Long) any())).thenReturn(Optional.of(new Product()));
        assertSame(productDto,
                adminServiceImpl.updateProductDescription(1L, "The characteristics of someone or something"));
        verify(modelMapper).map((Object) any(), (Class<ProductDto>) any());
        verify(productRepo).save((Product) any());
        verify(productRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateProductDescription(long, String)}
     */
    @Test
    void testUpdateProductDescription2() {
        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<ProductDto>) any())).thenReturn(new ProductDto());
        when(productRepo.save((Product) any())).thenThrow(new ResourceException());
        when(productRepo.findById((Long) any())).thenReturn(Optional.of(new Product()));
        assertThrows(ResourceException.class,
                () -> adminServiceImpl.updateProductDescription(1L, "The characteristics of someone or something"));
        verify(modelMapper).map((Object) any(), (Class<Object>) any());
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
        //   com.Etech.Exception.ResourceException
        //       at com.Etech.Model.Product.setDescription(Product.java:9)
        //       at com.Etech.Service.Impl.AdminServiceImpl.updateProductDescription(AdminServiceImpl.java:52)
        //   See https://diff.blue/R013 to resolve this issue.

        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<ProductDto>) any())).thenReturn(new ProductDto());
        Product product = mock(Product.class);
        doThrow(new ResourceException()).when(product).setDescription((String) any());
        Optional<Product> ofResult = Optional.of(product);
        when(productRepo.save((Product) any())).thenThrow(new ResourceException());
        when(productRepo.findById((Long) any())).thenReturn(ofResult);
        adminServiceImpl.updateProductDescription(1L, "The characteristics of someone or something");
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateProductDescription(long, String)}
     */
    @Test
    void testUpdateProductDescription4() {
        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<ProductDto>) any())).thenReturn(new ProductDto());
        when(productRepo.save((Product) any())).thenThrow(new ResourceException());
        when(productRepo.findById((Long) any())).thenReturn(Optional.empty());
        new ResourceException();
        assertThrows(ResourceException.class,
                () -> adminServiceImpl.updateProductDescription(1L, "The characteristics of someone or something"));
        verify(modelMapper).map((Object) any(), (Class<Object>) any());
        verify(productRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateProductPrice(long, double)}
     */
    @Test
    void testUpdateProductPrice() {
        ProductDto productDto = new ProductDto();
        when(modelMapper.map((Object) any(), (Class<ProductDto>) any())).thenReturn(productDto);
        when(productRepo.save((Product) any())).thenReturn(new Product());
        when(productRepo.findById((Long) any())).thenReturn(Optional.of(new Product()));
        assertSame(productDto, adminServiceImpl.updateProductPrice(1L, 10.0d));
        verify(modelMapper).map((Object) any(), (Class<ProductDto>) any());
        verify(productRepo).save((Product) any());
        verify(productRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateProductPrice(long, double)}
     */
    @Test
    void testUpdateProductPrice2() {
        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<ProductDto>) any())).thenReturn(new ProductDto());
        when(productRepo.save((Product) any())).thenThrow(new ResourceException());
        when(productRepo.findById((Long) any())).thenReturn(Optional.of(new Product()));
        assertThrows(ResourceException.class, () -> adminServiceImpl.updateProductPrice(1L, 10.0d));
        verify(modelMapper).map((Object) any(), (Class<Object>) any());
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
        //   com.Etech.Exception.ResourceException
        //       at com.Etech.Model.Product.setPrice(Product.java:9)
        //       at com.Etech.Service.Impl.AdminServiceImpl.updateProductPrice(AdminServiceImpl.java:62)
        //   See https://diff.blue/R013 to resolve this issue.

        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<ProductDto>) any())).thenReturn(new ProductDto());
        Product product = mock(Product.class);
        doThrow(new ResourceException()).when(product).setPrice(anyDouble());
        Optional<Product> ofResult = Optional.of(product);
        when(productRepo.save((Product) any())).thenThrow(new ResourceException());
        when(productRepo.findById((Long) any())).thenReturn(ofResult);
        adminServiceImpl.updateProductPrice(1L, 10.0d);
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateProductPrice(long, double)}
     */
    @Test
    void testUpdateProductPrice4() {
        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<ProductDto>) any())).thenReturn(new ProductDto());
        when(productRepo.save((Product) any())).thenThrow(new ResourceException());
        when(productRepo.findById((Long) any())).thenReturn(Optional.empty());
        new ResourceException();
        assertThrows(NoSuchElementException.class, () -> adminServiceImpl.updateProductPrice(1L, 10.0d));
        verify(modelMapper).map((Object) any(), (Class<Object>) any());
        verify(productRepo).findById((Long) any());
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

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(new Product());
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

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(new Product());
        productList.add(new Product());
        when(productRepo.findAll()).thenReturn(productList);
        assertEquals(2, adminServiceImpl.findAllProduct().size());
        verify(modelMapper, atLeast(1)).map((Object) any(), (Class<ProductDto>) any());
        verify(productRepo).findAll();
    }

    /**
     * Method under test: {@link AdminServiceImpl#findAllProduct()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindAllProduct4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.Etech.Exception.ResourceException
        //       at org.modelmapper.ModelMapper.map(ModelMapper.java:404)
        //       at com.Etech.Service.Impl.AdminServiceImpl.lambda$findAllProduct$3(AdminServiceImpl.java:72)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.Etech.Service.Impl.AdminServiceImpl.findAllProduct(AdminServiceImpl.java:72)
        //   See https://diff.blue/R013 to resolve this issue.

        when(modelMapper.map((Object) any(), (Class<ProductDto>) any())).thenThrow(new ResourceException());

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(new Product());
        when(productRepo.findAll()).thenReturn(productList);
        adminServiceImpl.findAllProduct();
    }

    /**
     * Method under test: {@link AdminServiceImpl#deleteProduct(long)}
     */
    @Test
    void testDeleteProduct() {
        doNothing().when(productRepo).delete((Product) any());
        when(productRepo.findProductBy(anyLong())).thenReturn(Optional.of(new Product()));
        adminServiceImpl.deleteProduct(1L);
        verify(productRepo).findProductBy(anyLong());
        verify(productRepo).delete((Product) any());
    }

    /**
     * Method under test: {@link AdminServiceImpl#deleteProduct(long)}
     */
    @Test
    void testDeleteProduct2() {
        doThrow(new ResourceException()).when(productRepo).delete((Product) any());
        when(productRepo.findProductBy(anyLong())).thenReturn(Optional.of(new Product()));
        assertThrows(ResourceException.class, () -> adminServiceImpl.deleteProduct(1L));
        verify(productRepo).findProductBy(anyLong());
        verify(productRepo).delete((Product) any());
    }

    /**
     * Method under test: {@link AdminServiceImpl#deleteProduct(long)}
     */
    @Test
    void testDeleteProduct3() {
        doNothing().when(productRepo).delete((Product) any());
        when(productRepo.findProductBy(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResourceException.class, () -> adminServiceImpl.deleteProduct(1L));
        verify(productRepo).findProductBy(anyLong());
    }
}

