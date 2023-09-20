package com.Etech.Dto;

import com.Etech.Model.enums.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private double price;
//    private String image;
//    private String category;
   private int quantity;
//    private String status;
//    private String location;
private ProductCategory productCategory;

}
