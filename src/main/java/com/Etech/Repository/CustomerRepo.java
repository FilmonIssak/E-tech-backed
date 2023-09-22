package com.Etech.Repository;

import com.Etech.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

    Optional<Customer> findById(long id);
    Optional<Customer> findByEmail(String id);
    Optional<Customer> findByPhone(String phone);


}
