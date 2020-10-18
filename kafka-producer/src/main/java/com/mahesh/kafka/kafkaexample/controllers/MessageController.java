package com.mahesh.kafka.kafkaexample.controllers;

import com.mahesh.kafka.kafkaexample.model.Greeting;
import com.mahesh.kafka.kafkaexample.processor.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MessageController {

    @Autowired
    MessageProducer messageProducer;

    @PostMapping("/normaltopic")
    public ResponseEntity sendMessageToNormalTopic(@RequestBody String message){
        messageProducer.sendMessage(message);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/partitiontopic")
    public ResponseEntity sendToPartitionTopic(@RequestBody String message){
        for(int i=0;i<5;i++){
            messageProducer.sendMessageToPartition(message, i);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/greetingtopic")
    public ResponseEntity sendToGreetingTopic(@RequestBody Greeting message){
        messageProducer.sendGreetingMessage(message);
        return ResponseEntity.ok().build();
    }
}
