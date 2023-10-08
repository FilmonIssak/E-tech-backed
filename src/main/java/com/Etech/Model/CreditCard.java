package com.Etech.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard {

    private String cardName;
    @CreditCardNumber
    private String cardNumber;
    private String cardExpiration;
    private String cardCvv;
    private String cardZip;

}
