package com.Etech.Controller;

import com.Etech.Model.Product;
import com.Etech.Service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("addProduct")
    public void addProduct(@RequestBody Product product) {
        adminService.addProduct(product);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("getProduct/{id}")
    public Product findProductById(@PathVariable("id") long id) {
        return adminService.findProductById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("getAllProducts")
    public List<Product> getAllProducts() {
        return adminService.findAllProduct();
    }

    @PutMapping("updateDescription/{id}")
    public ResponseEntity<Product> updateProductDescription(@PathVariable long id, @RequestHeader String description) {
        try {
            adminService.updateProductDescription(id, description);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("updatePrice/{id}")
    public ResponseEntity<Product> updatePrice(@PathVariable long id, @RequestHeader double price) {
        try {
            Product product = adminService.updateProductPrice(id, price);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("delete/{id}")
    public void deleteProductById(@PathVariable long id) {
        adminService.deleteProduct(id);
    }



}
