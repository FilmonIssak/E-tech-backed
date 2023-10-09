package com.Etech.Repository;

import com.Etech.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

    Optional<Customer> findCustomersById(Long id);
    Optional<Customer> findCustomersByPhone(String phone);
    Optional<Customer> findCustomersByEmail(String email);

}
