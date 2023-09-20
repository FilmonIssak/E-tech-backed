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

    @PostMapping("/products")
    public ResponseEntity<?> addProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.addProduct(productDto));
    }

    @GetMapping("products/{id}")
    public ResponseEntity<?> findProductById(@PathVariable("id") long id) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.findProductById(id));
    }

    @GetMapping("products")
    public ResponseEntity<List<?>> getAllProducts() {
       return ResponseEntity.status(HttpStatus.OK).body(adminService.findAllProduct());
    }
    @PutMapping("products/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable long id, @RequestBody ProductDto productDto) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.updateProduct(id,productDto));
    }

    @PatchMapping("products/description/{id}")
    public ResponseEntity<?> updateProductDescription(@PathVariable long id, @RequestBody ProductDto productDto) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.updateProductDescription(id,productDto));
    }

    @PatchMapping("products/price/{id}")
    public ResponseEntity<?> updatePrice(@PathVariable long id, @RequestBody ProductDto productDto) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.updateProductPrice(id, productDto));
    }
    @PatchMapping("products/category/{id}")
    public ResponseEntity<?> updateCatagory(@PathVariable long id, @RequestBody ProductDto productDto) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.updateProductCategory(id, productDto));
    }
    @PatchMapping("products/status/{id}")
    public ResponseEntity<?> updateStatus(@PathVariable long id, @RequestBody ProductDto productDto) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.updateProductStatus(id, productDto));
    }

    @DeleteMapping("products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable long id) {
        adminService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body("Product Successfully deleted");
    }


}
