package com.Etech.Service.Impl;

import com.Etech.Dto.OrderDto;
import com.Etech.Exception.ResourceException;
import com.Etech.Model.Order;
import com.Etech.Model.Product;
import com.Etech.Model.enums.OrderStatus;
import com.Etech.Model.enums.ProductStatus;
import com.Etech.Repository.AddressRepo;
import com.Etech.Repository.CustomerRepo;
import com.Etech.Repository.OrderRepo;
import com.Etech.Repository.ProductRepo;
import com.Etech.Service.OrderService;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    private AddressRepo addressRepo;


    @Override
    public OrderDto findOrderById(long id) {
        Order toGet = orderRepo.findById(id).orElseThrow(() -> new ResourceException("Order with order id: " + id + " is not present"));
        return modelMapper.map(toGet, OrderDto.class);
    }

              /**
             * first we need to create a product and customer
               **/
//    @Override
//    public OrderDto addOrder(OrderDto orderDto) {
//        Long orderId = orderDto.getId();
//
//        if (orderId != null) {
//            Optional<Order> toBeAdded = orderRepository.findById(orderDto.getId());
//            if (toBeAdded.isPresent()) {
//                throw new ResourceException("Order with order id = " + orderDto.getId() + " is already present", HttpStatus.CONFLICT);
//            }
//        }
//       Order order = modelMapper.map(orderDto, Order.class);
//
//        Address address = order.getAddress();
//        if (address.getId() == null) {
//            address = addressRepository.save(address);
//
//            order.setAddress(address);
//        }
//
//        /**
//         * first we need to create a cart and customer */
//
//       orderRepository.save(order);
//       return modelMapper.map(order, OrderDto.class);
//    }


              @Override
              public OrderDto cancelOrderByOrderId(long id) {

                  Order order= orderRepo.findById(id).orElseThrow(()->new ResourceException("No order exists with given OrderId "+ id));
                  if(order.getOrderStatus()== OrderStatus.PENDING) {
                      order.setOrderStatus(OrderStatus.CANCELED);
                      orderRepo.save(order);
                      return modelMapper.map(order, OrderDto.class);
                  }
                  else if(order.getOrderStatus()==OrderStatus.SUCCESS) {
                      order.setOrderStatus(OrderStatus.CANCELED);
                      List<Product> productsInCartList= order.getProductCartItems();

                      for(Product p : productsInCartList ) {
                          Integer addedQuantity = p.getQuantity()+ 1;
                          p.setQuantity(addedQuantity);
                          if(p.getProductStatus() == ProductStatus.OUTOFSTOCK) {
                              p.setProductStatus(ProductStatus.AVAILABLE);
                          }
                      }

                      orderRepo.save(order);
                      return modelMapper.map(order, OrderDto.class);
                  }
                  else {
                      throw new ResourceException("Order was already cancelled");
                  }

              }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // get all orders
//    @Override
//    public List<OrderDto> getAllOrders() {
//        List<Order> orders = orderRepository.findAll();
//        return orders.stream().map(order -> modelMapper.map(order, OrderDto.class)).collect(Collectors.toList());
//    }



              ////////////////////

    @Override
    public OrderDto placeOrder(Long customerId) {
        // Generate a unique order number or use a custom logic
        String orderNumber = generateUniqueOrderNumber(customerId);

        // Create a new order entity
        Order order = new Order();
        order.setCustomer(customerRepo.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found")));
        order.setOrderNumber(orderNumber);
//        order.setOrderDate(Data.));
        order.setOrderStatus(OrderStatus.PENDING);
        // Set other order details

        // Save the order to the repository
        orderRepo.save(order);
        return modelMapper.map(order, OrderDto.class);
    }

    private String generateUniqueOrderNumber(Long customerId) {
        DateTimeFormatter timestampFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timestamp = LocalDateTime.now().format(timestampFormatter);
        return timestamp + customerId;
    }


    @Override
    public OrderStatus checkOrderStatus(String orderNumber) {
        Order order = orderRepo.findOrderByOrderNumber(orderNumber);

        if (order == null) {
            throw new ResourceException("Order with order number: " + orderNumber + " is not present");
        }
        return order.getOrderStatus();
    }


}
