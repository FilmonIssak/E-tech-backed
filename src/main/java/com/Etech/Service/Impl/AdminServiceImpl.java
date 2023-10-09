package com.Etech.Service.Impl;

import com.Etech.Dto.*;
import com.Etech.Exception.ResourceException;
import com.Etech.Model.*;
import com.Etech.Model.enums.OrderStatus;
import com.Etech.Model.enums.ProductStatus;
import com.Etech.Repository.*;
import com.Etech.Service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private ViewerRepo viewerRepo;





    @Override
    public ProductDto addProduct(ProductDto productDto) {
        Long productId = productDto.getId();

        if (productId != null) {
            Optional<Product> toBeAdded = productRepo.findProductById(productId);

            if (toBeAdded.isPresent()) {
                throw new ResourceException("Product with ID " + productDto.getId() + " already exists", HttpStatus.CONFLICT);
            }
        }
        Product product = modelMapper.map(productDto, Product.class);
        productRepo.save(product);
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public ProductDto findProductById(long id) {
        Product product = productRepo.findProductById(id).orElseThrow(() -> new ResourceException("Product with id: "+ id + " is not present"));
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public ProductDto updateProduct(long id, ProductDto productDto) {
        Product toBeUpdated = productRepo.findProductById(id).orElseThrow(() -> new ResourceException("Product with id: "+ id + " is not present", HttpStatus.NOT_FOUND));
        toBeUpdated.setName(productDto.getName());
        toBeUpdated.setQuantity(productDto.getQuantity());
        toBeUpdated.setPrice(productDto.getPrice());
        toBeUpdated.setDescription(productDto.getDescription());
        toBeUpdated.setProductStatus(productDto.getProductStatus());
        toBeUpdated.setProductCategory(productDto.getProductCategory());
        productRepo.save(toBeUpdated);
        return modelMapper.map(toBeUpdated, ProductDto.class);
    }
    @Override
    public ProductDto updateProductDescription(long id, ProductDto productDto) {
        Product toBeUpdated = productRepo.findProductById(id).orElseThrow(() -> new ResourceException("Product with id: "+ id + " is not present", HttpStatus.NOT_FOUND));
        toBeUpdated.setDescription(productDto.getDescription());
        productRepo.save(toBeUpdated);
        return modelMapper.map(toBeUpdated, ProductDto.class);

    }

    @Override
    public ProductDto updateProductPrice(long id, ProductDto productDto) {
        Product toBeUpdated = productRepo.findProductById(id).orElseThrow(() -> new ResourceException("Product with id: "+ id + " is not present", HttpStatus.NOT_FOUND));
        toBeUpdated.setPrice(productDto.getPrice());
        productRepo.save(toBeUpdated);
        return modelMapper.map(toBeUpdated, ProductDto.class);

    }
    @Override
    public ProductDto updateProductCategory(long id, ProductDto productDto) {
        Product toBeUpdated = productRepo.findProductById(id).orElseThrow(() -> new ResourceException("Product with id: "+ id + " is not present", HttpStatus.NOT_FOUND));
        toBeUpdated.setProductCategory(productDto.getProductCategory());
        productRepo.save(toBeUpdated);
        return modelMapper.map(toBeUpdated, ProductDto.class);

    }

    @Override
    public ProductDto updateProductStatus(long id, ProductDto productDto) {
        Product toBeUpdated = productRepo.findProductById(id).orElseThrow(() -> new ResourceException("Product with id: "+ id + " is not present", HttpStatus.NOT_FOUND));
        toBeUpdated.setProductStatus(productDto.getProductStatus());
        productRepo.save(toBeUpdated);
        return modelMapper.map(toBeUpdated, ProductDto.class);
    }

    @Override
    public List<ProductDto> findAllProduct() {

        List<Product> productList = productRepo.findAll();
        return productList.stream().map(product -> modelMapper.map(product,ProductDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteProduct(long id) {

        Product toBeDeleted = productRepo.findProductById(id).orElseThrow(() -> new ResourceException("Product to be deleted not found"));
        productRepo.delete(toBeDeleted);
    }

    /** Customer $*/

    @Override
    public CustomerDto updateCustomerPassword(Long id, CustomerDto customerDto) {
        Customer toBeUpdated = customerRepo.findCustomersById(id).orElseThrow(() -> new ResourceException("Customer with id: "+ id + " is not present", HttpStatus.NOT_FOUND));
        toBeUpdated.setPassword(customerDto.getPassword());
        customerRepo.save(toBeUpdated);
        return modelMapper.map(toBeUpdated, CustomerDto.class);
    }

/**
 * Will update the updatePassword with this change password later $
 *
 * @Override
    public void changePassword(long id, PasswordDTO passwordDTO) {
        Customer customer = customerRepo.findById(id).orElseThrow(()->new ResourceException("Customer Not found"));
        if (!passwordDTO.getNewPassword().equals(passwordDTO.getVerifyNewPassword())){
            throw new ResourceException("Password must match",HttpStatus.CONFLICT);
        }
        customer.setId(id);
        customer.setPassword(bCryptPasswordEncoder.encode(passwordDTO.getVerifyNewPassword()));
        customerRepo.save(customer);
    }
 */

    @Override
    public CustomerDto updateCustomerPhone(Long id, CustomerDto customerDto) {
        Customer toBeUpdated = customerRepo.findCustomersById(id).orElseThrow(() -> new ResourceException("Customer with id: "+ id + " is not present", HttpStatus.NOT_FOUND));
        toBeUpdated.setPhone(customerDto.getPhone());
        customerRepo.save(toBeUpdated);
        return modelMapper.map(toBeUpdated, CustomerDto.class);
    }

    @Override
    public CustomerDto updateCustomerEmail(Long id, CustomerDto customerDto) {
        Customer toBeUpdated = customerRepo.findCustomersById(id).orElseThrow(() -> new ResourceException("Customer with id: "+ id + " is not present", HttpStatus.NOT_FOUND));
        toBeUpdated.setEmail(customerDto.getEmail());
        customerRepo.save(toBeUpdated);
        return modelMapper.map(toBeUpdated, CustomerDto.class);
    }

    @Override
    public CustomerDto activateOrDeactivateCustomerStatus(Long id, CustomerDto customerDto) {
        Customer toBeUpdated = customerRepo.findCustomersById(id).orElseThrow(() -> new ResourceException("Customer with id: "+ id + " is not present", HttpStatus.NOT_FOUND));
        toBeUpdated.setCustomerStatus(customerDto.getCustomerStatus());
        customerRepo.save(toBeUpdated);
        return modelMapper.map(toBeUpdated, CustomerDto.class);
    }

    @Override
    public CustomerDto updateCustomerDetails(Long id, CustomerDto customerDto) {
        Customer toBeUpdated = customerRepo.findCustomersById(id).orElseThrow(() -> new ResourceException("Customer with id: "+ id + " is not present", HttpStatus.NOT_FOUND));
        toBeUpdated.setFirstName(customerDto.getFirstName());
        toBeUpdated.setLastName(customerDto.getLastName());
        toBeUpdated.setPassword(customerDto.getPassword());
        toBeUpdated.setRole(customerDto.getRole());
        toBeUpdated.setPhone(customerDto.getPhone());
        toBeUpdated.setEmail(customerDto.getEmail());
        toBeUpdated.setCustomerStatus(customerDto.getCustomerStatus());
        toBeUpdated.setCreditCard(customerDto.getCreditCard());
        customerRepo.save(toBeUpdated);
        return modelMapper.map(toBeUpdated, CustomerDto.class);
    }

    @Override
    public void deleteCustomer(Long id) {

        Customer toBeDeleted = customerRepo.findCustomersById(id).orElseThrow(() -> new ResourceException("Customer to be deleted not found"));
        customerRepo.delete(toBeDeleted);

    }


    /** Order $ */

    @Override
    public List<OrderDto> getAllOrdersByDate(String orderDate) {
        List<Order> listOfOrdersOntheDay = orderRepository.findOrderByOrderDate(orderDate);
        return listOfOrdersOntheDay.stream().map(order -> modelMapper.map(order, OrderDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orderList = orderRepository.findAll();
        return orderList.stream().map(order -> modelMapper.map(order, OrderDto.class)).collect(Collectors.toList());
    }


    /**
     * @Author: Filmon Issak check it out and will discuss it later
     * Those are the new implemented methods
     * @method updateOrderStatusToProcessing
     * @method deleteOrder
     * @method updateOrderStatusToDelivery
     * @method updateOrderStatusToShipping
     * @method addProductToCartForViewer
     * @method deleteProductFromCartForViewer
     * @method placeOrder
     */



    @Override
    public OrderDto updateOrderStatusToProcessing(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceException("Order with id: " + orderId + " is not present", HttpStatus.NOT_FOUND));
        order.setOrderStatus(OrderStatus.PENDING);
        orderRepository.save(order);
        return modelMapper.map(order, OrderDto.class);
    }

    @Override
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceException("Order to be deleted not found"));
        orderRepository.delete(order);
    }

    @Override
    public OrderDto updateOrderStatusToDelivery(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceException("Order with id: " + orderId + " is not present", HttpStatus.NOT_FOUND));
        order.setOrderStatus(OrderStatus.SUCCESS);
        orderRepository.save(order);
        return modelMapper.map(order, OrderDto.class);
    }


    @Override
    public OrderDto updateOrderStatusToShipping(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceException("Order with id: " + orderId + " is not present", HttpStatus.NOT_FOUND));
        order.setOrderStatus(OrderStatus.Shipped);  // This is based on your provided enums. You might need a new enum for SHIPPING.
        orderRepository.save(order);
        return modelMapper.map(order, OrderDto.class);
    }

    @Override
    public CartDto addProductToCartForViewer(Long viewerId, Long productId) {
        Viewer viewer = viewerRepo.findById(viewerId).orElseThrow(() -> new ResourceException("Viewer not found"));
        Product product = productRepo.findById(productId).orElseThrow(() -> new ResourceException("Product not found"));
        Cart viewerCart = viewer.getCart();
        viewerCart.addProduct(product);
        cartRepo.save(viewerCart);
        return modelMapper.map(viewerCart, CartDto.class);
    }


    @Override
    public CartDto deleteProductFromCartForViewer(Long viewerId, Long productId) {
        Viewer viewer = viewerRepo.findById(viewerId).orElseThrow(() -> new ResourceException("Viewer not found"));
        Product product = productRepo.findById(productId).orElseThrow(() -> new ResourceException("Product not found"));
        Cart viewerCart = viewer.getCart();
        viewerCart.removeProduct(product);
        cartRepo.save(viewerCart);
        return modelMapper.map(viewerCart, CartDto.class);
    }



    //customer place an order
    @Override
    public OrderDto placeOrder(Long customerId, OrderDto orderDto) {
        Customer customer = customerRepo.findById(customerId).orElseThrow(() -> new ResourceException("Customer not found"));
        Order order = modelMapper.map(orderDto, Order.class);
        order.setCustomer(customer);
        orderRepository.save(order);
        return modelMapper.map(order, OrderDto.class);
    }


}
