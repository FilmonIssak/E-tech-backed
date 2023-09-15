package com.Etech.Controller;

import com.Etech.Dto.ProductDto;
import com.Etech.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    private  AdminService adminService;

    @PostMapping("addProduct")

    public ResponseEntity<?> addProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.addProduct(productDto));
    }

    @GetMapping("getProductById/{id}")
    public ResponseEntity<?> findProductById(@PathVariable("id") long id) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.findProductById(id));
    }

    @GetMapping("getAllProducts")
    public ResponseEntity<List<?>> getAllProducts() {
       return ResponseEntity.status(HttpStatus.OK).body(adminService.findAllProduct());
    }

    @PutMapping("updateProductDescription/{id}")
    public ResponseEntity<?> updateProductDescription(@PathVariable long id, @RequestHeader String description) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.updateProductDescription(id,description));
    }

    @PutMapping("updateProductPrice/{id}")
    public ResponseEntity<?> updatePrice(@PathVariable long id, @RequestHeader double price) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.updateProductPrice(id, price));
    }

    @DeleteMapping("deleteProduct/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable long id) {
        adminService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body("Product to be deleted not present");

    }


}
