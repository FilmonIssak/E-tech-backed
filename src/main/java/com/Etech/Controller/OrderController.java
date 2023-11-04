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

    @GetMapping("/{orderNumber}")
    public ResponseEntity<?> findProductById(@PathVariable String orderNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findOrderByOrderNumber(orderNumber));
    }
    @DeleteMapping("/{orderNumber}/cancel")
    public ResponseEntity<?> cancelTheOrderByOrderNumber (@PathVariable String orderNumber){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.cancelOrderByOrderNumber(orderNumber));
    }

}
