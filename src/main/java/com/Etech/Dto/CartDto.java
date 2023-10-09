package com.Etech.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {

    private Long id;
    private int quantity;
    private double totalPrice;

    private List<ProductDto> products;  // Assuming you have a ProductDto defined

    private Long customerId;
    private Long viewerId;

    // You can add more attributes if needed from the Cart entity or related entities
}
