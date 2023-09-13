package com.Etech.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard {

    private String cardName;
    private String cardNumber;
    private String cardExpiration;
    private String cardCvv;
    private String cardZip;

}
