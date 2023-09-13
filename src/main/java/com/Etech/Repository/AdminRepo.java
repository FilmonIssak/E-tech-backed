package com.Etech.Repository;

import com.Etech.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {

    /**
     * @Author: Filmon.
     * TODO: 9/12/23 Add Admin Repo Methods Here
     *
     */

    public void deleteProduct(long id);

    public void updateProductDescription(long id, String description);
}
