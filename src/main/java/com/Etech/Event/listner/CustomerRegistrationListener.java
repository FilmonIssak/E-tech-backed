package com.Etech.Event.listner;

import com.Etech.Dto.CustomerRegistrationDTO;
import com.Etech.Model.Email;
import com.Etech.Service.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class CustomerRegistrationListener {

    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = {"new-customer"})
    public void listenWhenCustomerRegistered(@Payload String memberDTO) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            System.out.println("Received new customer details ....");
            CustomerRegistrationDTO messageUserDetails = objectMapper.readValue(memberDTO, CustomerRegistrationDTO.class);

            String body = "<p>Welcome to E-tech online Shopping System<p>\n" +
                    "</b>Please use the following credentials to login to the system.</p>\n" +
                    "<p>username: <b>" + messageUserDetails.getFirstName() + "</b><br/>\n" +
                    "password: <b>" + messageUserDetails.getPassword() + "</b></p>"+
                    "<p>Thank you for choosing E-tech online Shopping System <p>\n" +
                    "<p>etechonlineshopping2023@gmail.com  <p>\n";

            Email email = new Email(messageUserDetails.getEmail(), "Welcome to E-tech online Shopping System", body);
            emailService.sendWithHTMLBody(email);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
