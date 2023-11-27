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

            String body = "<html>" +
                    "<head>" +
                    "<style>" +
                    "body {" +
                    "    font-family: Arial, sans-serif;" +
                    "    background-color: #f4f4f4;" +
                    "    text-align: center;" +
                    "}" +
                    ".container {" +
                    "    max-width: 600px;" +
                    "    margin: 0 auto;" +
                    "    padding: 20px;" +
                    "    background-color: #fff;" +
                    "    border-radius: 5px;" +
                    "    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);" +
                    "}" +
                    "h1 {" +
                    "    color: #007bff;" +
                    "}" +
                    "ul {" +
                    "    list-style-type: none;" +
                    "}" +
                    "li {" +
                    "    color: #28a745;" +
                    "}" +
                    "a.button {" +
                    "    background-color: #007bff;" +
                    "    color: white;" +
                    "    padding: 10px 20px;" +
                    "    text-decoration: none;" +
                    "    display: inline-block;" +
                    "    border-radius: 5px;" +
                    "    margin-right: 10px;" +
                    "}" +
                    "a.button:hover {" +
                    "    background-color: #0056b3;" +
                    "}" +
                    "</style>" +
                    "</head>" +
                    "<body>" +
                    "<div class='container'>" +
                    "<h1>Welcome to E-tech Online Shopping System</h1>" +
                    "<p>Please use the following credentials to login to the system:</p>" +
                    "<ul>" +
                    "    <li><strong>Username:</strong> " + messageUserDetails.getFirstName() + "</li>" +
                    "    <li><strong>Password:</strong> " + messageUserDetails.getPassword() + "</li>" +
                    "</ul>" +
                    "<p>Thank you for choosing E-tech Online Shopping System</p>" +
                    "<a href='mailto:etechonlineshopping2023@gmail.com' class='button'>Contact us via Email</a>" +
                    "<a href='tel:641-233-2353' class='button'>Call us at 843-653-6506</a>" +
                    "</div>" +
                    "</body>" +
                    "</html>";



            Email email = new Email(messageUserDetails.getEmail(), "Welcome to E-tech online Shopping System", body);
            emailService.sendWithHTMLBody(email);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
