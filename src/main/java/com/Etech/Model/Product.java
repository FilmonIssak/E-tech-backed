package com.Etech.Model;

import com.Etech.Model.enums.ProductCategory;
import com.Etech.Model.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double price;
    private int quantity;

    //    private String image;

    //        List<Product> productsInCartList = order.getProductCartItems();
//        for (Product p : productsInCartList) {
//            int addedQuantity = p.getQuantity() - 1;
//            p.setQuantity(addedQuantity);
//        }

    public void deductQuantity(int quantity) {
        this.quantity -= quantity;
    }


    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;
}
