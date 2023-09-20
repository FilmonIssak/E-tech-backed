package com.Etech.Model;

import com.Etech.Model.enums.ProductCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
//    private String status;
//    private String location;

    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;




}
