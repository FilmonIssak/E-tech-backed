package com.Etech.Controller;


import com.Etech.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findProductById(@PathVariable("id") long id) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findOrderById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelTheOrderByOrderId (@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.cancelOrderByOrderId(id));
    }

}
