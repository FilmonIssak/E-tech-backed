package com.Etech.Controller;

import com.Etech.Dto.CustomerDto;
import com.Etech.Model.Customer;
import com.Etech.Service.CartService;
import com.Etech.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer/")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CartService cartService;

    @GetMapping("findAll")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getAll());
    }

    @GetMapping("products")
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAll());
    }

    @PostMapping("register")
    public ResponseEntity<CustomerDto> registerCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto registeredCustomer = customerService.register(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredCustomer);
    }
    @GetMapping("cart/findAll")
    public ResponseEntity<?> viewAllProductsInViewerCart() {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.viewAllProductsInCart());
    }

}