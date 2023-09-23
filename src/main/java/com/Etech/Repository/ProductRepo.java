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

//    @Query("SELECT p FROM Product p WHERE p.name= : name")
      Optional<Product> getProductByName(@Param ("name") String name);
//      @Query("SELECT p FROM Product p WHERE p.productCategory= : catagory")
      List<Product> findProductsByProductCategory(@Param("catagory") ProductCategory category);

//    @Query("SELECT p FROM Product p WHERE p.name LIKE %:keyWord%")

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyWord, '%'))")
    List<Product> findAllByKeyWord(@Param("keyWord") String keyWord);

    // List<Product> findProductsWithStatus(@Param("") ProductStatus productStatus);



}
