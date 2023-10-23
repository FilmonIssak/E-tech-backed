package com.Etech.Dto;

import com.Etech.Model.enums.ProductCategory;
import com.Etech.Model.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductWithOutQuantityDetailDto {

    private Long id;
    private String name;
    private String description;
    private double price;
    private ProductStatus productStatus;
    private ProductCategory productCategory;

}
