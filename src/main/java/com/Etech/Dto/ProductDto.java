package com.Etech.Dto;

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
    private String price;
    private String image;
    private String category;
    private String quantity;
    private String status;
    private String location;

}
