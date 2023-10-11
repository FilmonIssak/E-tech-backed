package com.Etech.Repository;

import com.Etech.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {

    public List<Order> findOrderByOrderDate(String localDate);

//      @Query("SELECT c from Customer c where c.id = customerid")
//     public Customer findCustomerByOrderId(@Param("customerId") Long id);
}
