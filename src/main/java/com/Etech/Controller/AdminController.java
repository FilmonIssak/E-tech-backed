package com.Etech.Controller;

import com.Etech.Dto.CustomerDto;
import com.Etech.Dto.OrderDto;
import com.Etech.Dto.ProductDto;
import com.Etech.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/")
public class AdminController {

    @Autowired
    private  AdminService adminService;


    @PostMapping("products")
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
    public ResponseEntity<?> updateCategory(@PathVariable long id, @RequestBody ProductDto productDto) {
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

    /** Customer $*/
    @PatchMapping("customer/password/{id}")
    public ResponseEntity<?> updateCustomerPassword(@PathVariable long id, @RequestBody CustomerDto customerDto) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.updateCustomerPassword(id,customerDto));
    }

    /**
     * to be updated later
     *
    @PatchMapping("customer/password/{id}")
    public ResponseEntity<?> changePassword(@PathVariable long id, @RequestBody PasswordDTO passwordDTO) {
        ApiResponse response = ApiResponse.builder().message("Password changed successfully").success(true).status(HttpStatus.OK).build();
        return ResponseEntity.status(HttpStatus.OK).body(adminService.changePassword(id,passwordDTO));
    }
     */


    @PatchMapping("customer/phone/{id}")
    public ResponseEntity<?> updateCustomerPhone(@PathVariable long id, @RequestBody CustomerDto customerDto) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.updateCustomerPhone(id,customerDto));
    }
    @PatchMapping("customer/email/{id}")
    public ResponseEntity<?> updateCustomerEmail(@PathVariable long id, @RequestBody CustomerDto customerDto) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.updateCustomerEmail(id,customerDto));
    }
    @PatchMapping("customer/{id}")
    public ResponseEntity<?> activateOrDeactivateCustomerStatus(@PathVariable long id, @RequestBody CustomerDto customerDto) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.activateOrDeactivateCustomerStatus(id,customerDto));
    }
    @PutMapping("customer/{id}")
    public ResponseEntity<?> updateCustomerDetails(@PathVariable long id, @RequestBody CustomerDto customerDto){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.updateCustomerDetails(id, customerDto));
    }
    @DeleteMapping("customer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable long id){
        adminService.deleteCustomer(id);
        return ResponseEntity.status(HttpStatus.OK).body("Customer successfully deleted");
    }

    /** Order $*/

    @GetMapping("orders")
    public ResponseEntity<List<?>> getAllOrders() {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getAllOrders());
    }
    @GetMapping("orders/date/{date}")
    public ResponseEntity<List<?>> getOrdersByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getAllOrdersByDate(date));
    }


    //////////////////////////////////////////////////////


    @PatchMapping("orders/{orderNumber}/completed")
    public ResponseEntity<?> updateOrderStatusToProcessing(@PathVariable String orderNumber, OrderDto orderDto) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.updateOrderStatusToCompleted(orderNumber, orderDto));
    }

    @PatchMapping("orders/{orderNumber}/delivered")
    public ResponseEntity<?> updateOrderStatusToDelivery(@PathVariable String orderNumber, OrderDto orderDto) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.updateOrderStatusToDelivery(orderNumber, orderDto));
    }

    @PatchMapping("orders/{orderNumber}/shipping")
    public ResponseEntity<?> updateOrderStatusToShipping(@PathVariable String orderNumber,OrderDto orderDto) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.updateOrderStatusToShipping(orderNumber, orderDto));
    }

    @DeleteMapping("orders/{orderNumber}")
    public ResponseEntity<?> deleteOrder(@PathVariable String orderNumber) {
        adminService.deleteOrder(orderNumber);
        return ResponseEntity.status(HttpStatus.OK).body("Order successfully deleted");
    }



}
