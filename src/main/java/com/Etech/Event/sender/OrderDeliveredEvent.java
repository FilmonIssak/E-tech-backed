package com.Etech.Event.sender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderDeliveredEvent {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void sendOrderDeliveredEvent(String message){
        log.info("Inside send method of OrderShippedEvent");
        kafkaTemplate.send("order-delivered",message);
    }

}
