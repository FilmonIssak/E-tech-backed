package com.Etech.Controller;

import com.Etech.Dto.CartDto;
import com.Etech.Dto.CustomerDto;
import com.Etech.Dto.OrderDto;
import com.Etech.Dto.ProductDto;
import com.Etech.Exception.ResourceException;
import com.Etech.Service.CartService;
import com.Etech.Model.enums.OrderStatus;
import com.Etech.Service.CustomerService;
import com.Etech.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/customer/")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;

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

    @PostMapping(value = "register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> registerCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto registeredCustomer = customerService.register(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredCustomer);
    }
    @GetMapping("cart/findAll")
    public ResponseEntity<?> viewAllProductsInViewerCart() {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.viewAllProductsInCart());
    }


    @PostMapping("{customerId}/cart")
    public ResponseEntity<CartDto> addToCart(@PathVariable Long customerId, @RequestBody ProductDto productDto) {
        CartDto updatedCart = customerService.addProductToViewerCart(customerId, productDto.getId(), productDto.getQuantity());
        return ResponseEntity.status(HttpStatus.OK).body(updatedCart);
    }

    @PostMapping("{customerId}/order")
    public ResponseEntity<String> placeOrder(@PathVariable Long customerId) {
        try {
            OrderDto createdOrder = orderService.placeOrder(customerId);
            String orderNumber = createdOrder.getOrderNumber();
            return ResponseEntity.ok("Order placed successfully. Order number: " + orderNumber);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PatchMapping("{orderNumber}/order-status")
    public ResponseEntity<String> checkOrderStatus(@PathVariable String orderNumber) {
        try {
            OrderStatus orderStatus = orderService.checkOrderStatus(orderNumber);

            if (orderStatus != null) {
                return ResponseEntity.ok("Order status for order number " + orderNumber + ": " + orderStatus.toString());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (ResourceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{customerId}/cart/product/{productId}")
    public ResponseEntity<?> deleteProductFromCart(@PathVariable Long customerId, @PathVariable Long productId) {
        CartDto updatedCart = customerService.deleteProductFromCustomerCart(customerId, productId);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    }

    @GetMapping("findAllProductCart")
    public ResponseEntity<?> findAllProductCart() {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAllProductCart());
    }

}