package com.Etech.Dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderPayDto {

    private double price;
    private String currency;
    @NonNull
    private String method;
    private String intent;
    private String description;
}
