package com.Etech.Controller;


import com.Etech.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/publicuser")
public class ViewerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("getAllProducts")
    public ResponseEntity<List<?>> getAllProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAll());
    }

    @GetMapping("getProductsByCatagory")
    public ResponseEntity<List<?>> getAllProductsByCatagory(@RequestParam(name= "category", required = true) String  catagory){
        return ResponseEntity.status (HttpStatus.OK).body (customerService.findAllByCatagory (catagory) );
    }

//    @GetMapping("hi")
//    public String hello(){
//        return "Hello Conttroller";
//    }
}
