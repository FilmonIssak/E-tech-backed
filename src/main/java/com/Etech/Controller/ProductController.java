package com.Etech.Controller;

import com.Etech.Dto.ProductDto;
import com.Etech.Exception.ResourceException;
import com.Etech.Model.enums.ProductCategory;
import com.Etech.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products/")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("search/{name}")
    public ResponseEntity<List<ProductDto>> searchProducts(@PathVariable String name) {
        try {
            List<ProductDto> products = productService.searchProducts(name);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (ResourceException e) {
            return new ResponseEntity<>(HttpStatus.valueOf(e.getStatus().value()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("searchByCategory/{category}")
    public ResponseEntity<List<ProductDto>> searchProductsByCategory(@PathVariable String category) {
        try {
            ProductCategory cat = ProductCategory.valueOf(category.toUpperCase());
            List<ProductDto> products = productService.findByCategory(cat);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (ResourceException e) {
            return new ResponseEntity<>(HttpStatus.valueOf(e.getStatus().value()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
