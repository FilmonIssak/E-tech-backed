package com.Etech.Repository;

import com.Etech.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {

    public List<Order> findOrderByOrderDate(String localDate);


    public Order findOrderByOrderNumber(String orderNumber);
        @Query("SELECT o FROM Order o WHERE o.orderNumber = :orderNumber AND o.customer.id = :customerId")
        Order findOrderByOrderNumberAndCustomerId(@Param("orderNumber") String orderNumber, @Param("customerId") Long customerId);

}
