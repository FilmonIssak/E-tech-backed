package com.Etech.Event.listner;

import com.Etech.Dto.CustomerRegistrationDTO;
import com.Etech.Dto.OrderPlacedDto;
import com.Etech.Model.Email;
import com.Etech.Model.Product;
import com.Etech.Service.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class OrderPlacementListener {

    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = {"order-placed"})
    public void listenWhenOrderPlaced(@Payload String memberDTO) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            System.out.println("Received new Order details ....");
            OrderPlacedDto messageUserDetails = objectMapper.readValue(memberDTO, OrderPlacedDto.class);

            String body = "<p>Welcome to E-tech online Shopping System<p>\n" +
                    "</b> Your Order Details are as Follows</p>\n" +
                    "<p>Order Number: <b>" + messageUserDetails.getOrderNumber() + "</b><br/>\n" +
                    "Total Amount: <b>" + messageUserDetails.getOrderTotal() + "$"+ "</b></p>"+
                    "Order Date: <b>" + messageUserDetails.getOrderDate() + "</b></p>"+
                    "<p>Thank you for choosing E-tech online Shopping System <p>\n" +
                    "<p>etechonlineshopping2023@gmail.com  <p>\n";

            Email email = new Email(messageUserDetails.getCustomer().getEmail(), "Welcome to E-tech online Shopping System", body);
            emailService.sendWithHTMLBody(email);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
