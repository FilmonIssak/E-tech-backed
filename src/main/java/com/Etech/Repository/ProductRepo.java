package com.Etech.Repository;

import com.Etech.Model.Product;
import com.Etech.Model.enums.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    Optional<Product> findProductById(long id);

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByProductCategory(ProductCategory category);


}
