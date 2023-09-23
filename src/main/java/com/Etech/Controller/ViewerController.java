package com.Etech.Controller;


import com.Etech.Dto.ProductDto;
import com.Etech.Service.ViewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/viewer")
public class ViewerController {

    @Autowired
    ViewerService viewerService;


    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.status( HttpStatus.OK).body(viewerService.getAllProducts());
    }

    @GetMapping("/byname/{name}")
    public ResponseEntity<ProductDto> findProductByName(@PathVariable("name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(viewerService.findProductByName (name));
    }
    @GetMapping("/byCategory/{category}")
    public ResponseEntity<List<ProductDto>> findProductsByCategory(@PathVariable("category") String category) {
        return ResponseEntity.status(HttpStatus.OK).body(viewerService.findAllByCatagory (category));
    }

    @GetMapping("/byKeyword/{keyword}")
    public ResponseEntity<List<ProductDto>> findProductsByKeyword(@PathVariable("keyword") String keyword) {
        return ResponseEntity.status(HttpStatus.OK).body(viewerService.findAllByKeyWord (keyword));
    }

    @GetMapping("/filterProducts")
    public ResponseEntity<List<ProductDto>> filterProducts(@RequestParam(name = "name", required = false) String name,
                                                           @RequestParam(name = "category", required = false) String category,
                                                           @RequestParam(name = "keyWord", required = false) String keyWord){
        return ResponseEntity.status(HttpStatus.OK).body(viewerService.filterProducts (name,category, keyWord));
    }



}
