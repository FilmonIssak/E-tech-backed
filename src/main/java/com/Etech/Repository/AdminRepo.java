package com.Etech.Repository;

import com.Etech.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {

    /**
     * @Author: Filmon.
     * TODO: 9/12/23 Add Admin Repo Methods Here
     *
     */

//    @Query("DELETE FROM Product p WHERE p.id = :id")
//    void deleteProduct(@Param("id") long id);


    @Query("UPDATE Product p SET p.description = ?2 WHERE p.id = ?1")
    void updateProductDescription(long productId, String description);}
