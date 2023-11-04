package com.Etech.Model;

import com.Etech.Model.enums.ProductCategory;
import com.Etech.Model.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double price;
    @Positive
    private int quantity;
    public void deductQuantity(int quantity) {
        this.quantity -= quantity;
    }


    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;
}
