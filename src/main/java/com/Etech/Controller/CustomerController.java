package com.Etech.Controller;

import com.Etech.Dto.ProductDto;
import com.Etech.Exception.ResourceException;
import com.Etech.Model.Product;
import com.Etech.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer/")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("products")
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAll());
    }

    @PutMapping("products/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable long id, @RequestBody ProductDto productDto) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.updateProductQuantity(id,productDto));
    }

}
