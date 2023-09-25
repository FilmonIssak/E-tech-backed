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

    Optional<Product> findProductById(long id);
    Optional<Product> getProductByName(@Param ("name") String name);
    List<Product> findProductsByProductCategory(@Param("catagory") ProductCategory category);
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyWord, '%'))")
    List<Product> findAllByKeyWord(@Param("keyWord") String keyWord);
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByProductCategory(ProductCategory category);



}
