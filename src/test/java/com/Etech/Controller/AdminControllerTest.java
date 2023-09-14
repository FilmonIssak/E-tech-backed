package com.Etech.Controller;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.Etech.Model.Product;
import com.Etech.Model.enums.ProductCategory;
import com.Etech.Service.AdminService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
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
     * Method under test: {@link AdminController#addProduct(Product)}
     */
    @Test
    void testAddProduct() throws Exception {
        doNothing().when(adminService).addProduct(Mockito.<Product>any());

        Product product = new Product();
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setImage("Image");
        product.setName("Name");
        product.setPrice(10.0d);
        product.setProductCategory(ProductCategory.FASHION);
        product.setQuantity("Quantity");
        product.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(product);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/admin/addProduct")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"name\":\"Name\",\"description\":\"The characteristics of someone or something\",\"price\":10.0,\"image"
                                        + "\":\"Image\",\"quantity\":\"Quantity\",\"status\":\"Status\",\"productCategory\":\"FASHION\"}"));
    }

    /**
     * Method under test: {@link AdminController#findProductById(long)}
     */
    @Test
    void testFindProductById() throws Exception {
        Product product = new Product();
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setImage("Image");
        product.setName("Name");
        product.setPrice(10.0d);
        product.setProductCategory(ProductCategory.FASHION);
        product.setQuantity("Quantity");
        product.setStatus("Status");
        when(adminService.findProductById(anyLong())).thenReturn(product);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/admin/getProductById/{id}",
                1L);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"name\":\"Name\",\"description\":\"The characteristics of someone or something\",\"price\":10.0,\"image"
                                        + "\":\"Image\",\"quantity\":\"Quantity\",\"status\":\"Status\",\"productCategory\":\"FASHION\"}"));
    }

    /**
     * Method under test: {@link AdminController#deleteProduct(long)}
     */
    @Test
    void testDeleteProduct() throws Exception {
        doNothing().when(adminService).deleteProduct(anyLong());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/admin/deleteProduct/{id}",
                1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link AdminController#deleteProduct(long)}
     */
    @Test
    void testDeleteProduct2() throws Exception {
        doNothing().when(adminService).deleteProduct(anyLong());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/admin/deleteProduct/{id}",
                1L);
        requestBuilder.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link AdminController#getAllProducts()}
     */
    @Test
    void testGetAllProducts() throws Exception {
        when(adminService.findAllProduct()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/admin/getAllProducts");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AdminController#updatePrice(long, double)}
     */
    @Test
    void testUpdatePrice() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/admin/updateProductPrice/{id}",
                1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link AdminController#updateProductDescription(long, String)}
     */
    @Test
    void testUpdateProductDescription() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/v1/admin/updateProductDescription/{id}", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}

