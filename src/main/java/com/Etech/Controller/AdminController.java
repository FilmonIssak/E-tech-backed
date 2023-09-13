package com.Etech.Controller;

import com.Etech.Model.Product;
import com.Etech.Service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        try {
            adminService.addProduct(product);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



    @GetMapping("getProductById/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable("id") long id) {
        try {
            Product product = adminService.findProductById(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            List<Product> products = adminService.findAllProduct();
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("updateProductDescription/{id}")
    public ResponseEntity<Product> updateProductDescription(@PathVariable long id, @RequestHeader String description) {
        try {
            adminService.updateProductDescription(id, description);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("updateProductPrice/{id}")
    public ResponseEntity<Product> updatePrice(@PathVariable long id, @RequestHeader double price) {
        try {
            Product product = adminService.updateProductPrice(id, price);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping("deleteProduct/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable long id) {
        try {
            adminService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
