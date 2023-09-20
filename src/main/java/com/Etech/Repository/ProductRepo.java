package com.Etech.Repository;

import com.Etech.Dto.ProductDto;
import com.Etech.Model.Product;
import com.Etech.Model.enums.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    Optional<Product> findById(long id);

    @Query("SELECT p FROM Product p WHERE p.productCategory = : category")
    List<Product> findByCategory(@Param ( "category" ) ProductCategory category);
}
