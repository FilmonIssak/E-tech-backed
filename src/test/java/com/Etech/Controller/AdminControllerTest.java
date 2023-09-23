package com.Etech.Controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.Etech.Dto.ProductDto;
import com.Etech.Model.enums.ProductCategory;
import com.Etech.Model.enums.ProductStatus;
import com.Etech.Service.AdminService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AdminController.class})
@ExtendWith(SpringExtension.class)
class AdminControllerTest {
    @Autowired
    private AdminController adminController;

    @MockBean
    private AdminService adminService;

    /**
     * Method under test: {@link AdminController#addProduct(ProductDto)}
     */
    @Test
    void testAddProduct() throws Exception {
        when(adminService.addProduct((ProductDto) any())).thenReturn(new ProductDto());

        ProductDto productDto = new ProductDto();
        productDto.setDescription("The characteristics of someone or something");
        productDto.setId(1L);
        productDto.setName("Name");
        productDto.setPrice(10.0d);
        productDto.setProductCategory(ProductCategory.FASHION);
        productDto.setProductStatus(ProductStatus.AVAILABLE);
        productDto.setQuantity(1);
        String content = (new ObjectMapper()).writeValueAsString(productDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/admin/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"name\":null,\"description\":null,\"price\":0.0,\"quantity\":0,\"productStatus\":null,\"productCategory"
                                        + "\":null}"));
    }

    /**
     * Method under test: {@link AdminController#findProductById(long)}
     */
    @Test
    void testFindProductById() throws Exception {
        when(adminService.findProductById(anyLong())).thenReturn(new ProductDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/admin/products/{id}", 1L);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"name\":null,\"description\":null,\"price\":0.0,\"quantity\":0,\"productStatus\":null,\"productCategory"
                                        + "\":null}"));
    }

    /**
     * Method under test: {@link AdminController#findProductById(long)}
     */
    @Test
    void testFindProductById2() throws Exception {
        when(adminService.findAllProduct()).thenReturn(new ArrayList<>());
        when(adminService.findProductById(anyLong())).thenReturn(new ProductDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/admin/products/{id}", "",
                "Uri Variables");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AdminController#updateProduct(long, ProductDto)}
     */
    @Test
    void testUpdateProduct() throws Exception {
        when(adminService.updateProduct(anyLong(), (ProductDto) any())).thenReturn(new ProductDto());

        ProductDto productDto = new ProductDto();
        productDto.setDescription("The characteristics of someone or something");
        productDto.setId(1L);
        productDto.setName("Name");
        productDto.setPrice(10.0d);
        productDto.setProductCategory(ProductCategory.FASHION);
        productDto.setProductStatus(ProductStatus.AVAILABLE);
        productDto.setQuantity(1);
        String content = (new ObjectMapper()).writeValueAsString(productDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/admin/products/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"name\":null,\"description\":null,\"price\":0.0,\"quantity\":0,\"productStatus\":null,\"productCategory"
                                        + "\":null}"));
    }

    /**
     * Method under test: {@link AdminController#updateProductDescription(long, ProductDto)}
     */
    @Test
    void testUpdateProductDescription() throws Exception {
        when(adminService.updateProductDescription(anyLong(), (ProductDto) any())).thenReturn(new ProductDto());

        ProductDto productDto = new ProductDto();
        productDto.setDescription("The characteristics of someone or something");
        productDto.setId(1L);
        productDto.setName("Name");
        productDto.setPrice(10.0d);
        productDto.setProductCategory(ProductCategory.FASHION);
        productDto.setProductStatus(ProductStatus.AVAILABLE);
        productDto.setQuantity(1);
        String content = (new ObjectMapper()).writeValueAsString(productDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .patch("/api/v1/admin/products/description/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"name\":null,\"description\":null,\"price\":0.0,\"quantity\":0,\"productStatus\":null,\"productCategory"
                                        + "\":null}"));
    }

    /**
     * Method under test: {@link AdminController#deleteProduct(long)}
     */
    @Test
    void testDeleteProduct() throws Exception {
        doNothing().when(adminService).deleteProduct(anyLong());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/admin/products/{id}", 1L);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Product Successfully deleted"));
    }

    /**
     * Method under test: {@link AdminController#deleteProduct(long)}
     */
    @Test
    void testDeleteProduct2() throws Exception {
        doNothing().when(adminService).deleteProduct(anyLong());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/v1/admin/products/{id}", 1L);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Product Successfully deleted"));
    }

    /**
     * Method under test: {@link AdminController#getAllProducts()}
     */
    @Test
    void testGetAllProducts() throws Exception {
        when(adminService.findAllProduct()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/admin/products");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AdminController#getAllProducts()}
     */
    @Test
    void testGetAllProducts2() throws Exception {
        when(adminService.findAllProduct()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/admin/products");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AdminController#updateCatagory(long, ProductDto)}
     */
    @Test
    void testUpdateCatagory() throws Exception {
        when(adminService.updateProductCategory(anyLong(), (ProductDto) any())).thenReturn(new ProductDto());

        ProductDto productDto = new ProductDto();
        productDto.setDescription("The characteristics of someone or something");
        productDto.setId(1L);
        productDto.setName("Name");
        productDto.setPrice(10.0d);
        productDto.setProductCategory(ProductCategory.FASHION);
        productDto.setProductStatus(ProductStatus.AVAILABLE);
        productDto.setQuantity(1);
        String content = (new ObjectMapper()).writeValueAsString(productDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .patch("/api/v1/admin/products/category/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"name\":null,\"description\":null,\"price\":0.0,\"quantity\":0,\"productStatus\":null,\"productCategory"
                                        + "\":null}"));
    }

    /**
     * Method under test: {@link AdminController#updatePrice(long, ProductDto)}
     */
    @Test
    void testUpdatePrice() throws Exception {
        when(adminService.updateProductPrice(anyLong(), (ProductDto) any())).thenReturn(new ProductDto());

        ProductDto productDto = new ProductDto();
        productDto.setDescription("The characteristics of someone or something");
        productDto.setId(1L);
        productDto.setName("Name");
        productDto.setPrice(10.0d);
        productDto.setProductCategory(ProductCategory.FASHION);
        productDto.setProductStatus(ProductStatus.AVAILABLE);
        productDto.setQuantity(1);
        String content = (new ObjectMapper()).writeValueAsString(productDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .patch("/api/v1/admin/products/price/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"name\":null,\"description\":null,\"price\":0.0,\"quantity\":0,\"productStatus\":null,\"productCategory"
                                        + "\":null}"));
    }

    /**
     * Method under test: {@link AdminController#updateStatus(long, ProductDto)}
     */
    @Test
    void testUpdateStatus() throws Exception {
        when(adminService.updateProductStatus(anyLong(), (ProductDto) any())).thenReturn(new ProductDto());

        ProductDto productDto = new ProductDto();
        productDto.setDescription("The characteristics of someone or something");
        productDto.setId(1L);
        productDto.setName("Name");
        productDto.setPrice(10.0d);
        productDto.setProductCategory(ProductCategory.FASHION);
        productDto.setProductStatus(ProductStatus.AVAILABLE);
        productDto.setQuantity(1);
        String content = (new ObjectMapper()).writeValueAsString(productDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .patch("/api/v1/admin/products/status/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"name\":null,\"description\":null,\"price\":0.0,\"quantity\":0,\"productStatus\":null,\"productCategory"
                                        + "\":null}"));
    }
}

