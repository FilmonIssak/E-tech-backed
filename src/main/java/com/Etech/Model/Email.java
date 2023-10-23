package com.Etech.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Email {
    private String receiverEmail;
    private String subject;
    private String body;
}
