package com.Etech.Dto;

import com.Etech.Model.enums.ProductCategory;
import com.Etech.Model.enums.ProductStatus;
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
   private int quantity;
    private ProductStatus productStatus;
    private ProductCategory productCategory;

}
