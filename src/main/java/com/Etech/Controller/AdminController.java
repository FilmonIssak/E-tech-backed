package com.Etech.Controller;

import com.Etech.Dto.ProductDto;
import com.Etech.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/products")
public class AdminController {

    @Autowired
    private  AdminService adminService;

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.addProduct(productDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findProductById(@PathVariable("id") long id) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.findProductById(id));
    }

    @GetMapping
    public ResponseEntity<List<?>> getAllProducts() {
       return ResponseEntity.status(HttpStatus.OK).body(adminService.findAllProduct());
    }

    @PatchMapping("description/{id}")
    public ResponseEntity<?> updateProductDescription(@PathVariable long id, @RequestBody ProductDto productDto) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.updateProductDescription(id,productDto));
    }

    @PatchMapping("price/{id}")
    public ResponseEntity<?> updatePrice(@PathVariable long id, @RequestBody ProductDto productDto) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.updateProductPrice(id, productDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable long id) {
        adminService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body("Product to be deleted not present");
    }


}
