package com.Etech.Event.listner;

import com.Etech.Dto.OrderPlacedDto;
import com.Etech.Model.Email;
import com.Etech.Service.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class OrderDeliveredListener {

    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = {"order-delivered"})
    public void listenWhenOrderDelivered(@Payload String memberDTO) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            System.out.println("Received new Order Updates ....");
            OrderPlacedDto messageUserDetails = objectMapper.readValue(memberDTO, OrderPlacedDto.class);

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
                    ".black-text {" +
                    "    color: black;" +
                    "}" +
                    ".red-text {" +
                    "    color: red;" +
                    "}" +
                    "</style>" +
                    "</head>" +
                    "<body>" +
                    "<div class='container'>" +
                    "<h1>Welcome to E-tech Online Shopping System</h1>" +
                    "<p>Your Order Delivery Status is as follows:</p>" +
                    "<ul>" +
                    "    <li><strong class='black-text'>Order Number:</strong> <span class='red-text'>" + messageUserDetails.getOrderNumber() + "</span></li>" +
                    "    <li><strong class='black-text'>Order Status:</strong> <span class='red-text'>" + messageUserDetails.getOrderStatus() + " successfully</span></li>" +
                    "    <li><strong class='black-text'>Order Date:</strong> <span class='red-text'>" + messageUserDetails.getOrderDate() + "</span></li>" +
                    "</ul>" +
                    "<p>Thank you for choosing E-tech Online Shopping System</p>" +
                    "<a href='mailto:etechonlineshopping2023@gmail.com' class='button'>Contact us via Email</a>" +
                    "<a href='tel:641-233-2353' class='button'>Call us at 843-653-6506</a>" +
                    "</div>" +
                    "</body>" +
                    "</html>";




            Email email = new Email(messageUserDetails.getCustomer().getEmail(), "Welcome to E-tech online Shopping System", body);
            emailService.sendWithHTMLBody(email);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
