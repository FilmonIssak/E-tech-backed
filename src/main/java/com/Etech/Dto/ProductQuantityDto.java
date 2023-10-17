package com.Etech.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductQuantityDto {
    private ProductWithOutQuantityDetailDto product;
    private int quantity;
}