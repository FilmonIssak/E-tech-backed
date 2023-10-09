package com.Etech.Service.Impl;

import com.Etech.Dto.OrderDto;
import com.Etech.Exception.ResourceException;
import com.Etech.Model.Order;
import com.Etech.Model.Product;
import com.Etech.Model.enums.OrderStatus;
import com.Etech.Model.enums.ProductStatus;
import com.Etech.Repository.AddressRepository;
import com.Etech.Repository.OrderRepository;
import com.Etech.Service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AddressRepository addressRepository;


    @Override
    public OrderDto findOrderById(long id) {
        Order toGet = orderRepository.findById(id).orElseThrow(() -> new ResourceException("Order with order id: " + id + " is not present"));
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

                  Order order= orderRepository.findById(id).orElseThrow(()->new ResourceException("No order exists with given OrderId "+ id));
                  if(order.getOrderStatus()== OrderStatus.PENDING) {
                      order.setOrderStatus(OrderStatus.CANCELED);
                      orderRepository.save(order);
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

                      orderRepository.save(order);
                      return modelMapper.map(order, OrderDto.class);
                  }
                  else {
                      throw new ResourceException("Order was already cancelled");
                  }

              }
}
