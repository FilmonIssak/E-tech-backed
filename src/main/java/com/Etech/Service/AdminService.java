package com.Etech.Service;

import com.Etech.Dto.*;

import java.time.LocalDate;
import java.util.List;

public interface AdminService {

    public ProductDto addProduct(ProductDto productDto);

    public ProductDto findProductById(long id);
    public List<ProductDto> findAllProduct();
    public void deleteProduct(long id);
    public ProductDto updateProduct(long id, ProductDto productDto);
    public ProductDto updateProductDescription(long id, ProductDto productDto);
    public ProductDto updateProductPrice(long id, ProductDto productDto);
    public ProductDto updateProductCategory(long id, ProductDto productDto);
    public ProductDto updateProductStatus(long id, ProductDto productDto);

    /** Customer $*/
    public CustomerDto updateCustomerPassword(Long id, CustomerDto customerDto);

   // public void changePassword(long id, PasswordDTO passwordDTO);
    public CustomerDto updateCustomerPhone(Long id, CustomerDto customerDto);
    public CustomerDto updateCustomerEmail(Long id, CustomerDto customerDto);
    public CustomerDto activateOrDeactivateCustomerStatus(Long id, CustomerDto customerDto);
    public CustomerDto updateCustomerDetails(Long id, CustomerDto customerDto);
    public void deleteCustomer(Long id);

    /** Order $*/
    public OrderDto updateOrderStatusToCompleted(String orderNumber, OrderDto orderDto);

    public List<OrderDtoWithSpecificDetails> getAllOrdersByDate(LocalDate orderDate);

    public List<OrderDtoWithSpecificDetails> getAllOrders();

    public void deleteOrder(String orderNumber);

    public OrderDto updateOrderStatusToDelivery(String orderNumber, OrderDto orderDto);

    public OrderDto updateOrderStatusToShipping(String orderNumber, OrderDto orderDto);



}
