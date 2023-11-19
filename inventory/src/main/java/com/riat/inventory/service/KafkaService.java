package com.riat.inventory.service;

import com.riat.inventory.dto.JsonMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaService {

    @Value("${kafka.topics.emails-topic}")
    private String email_topic;

    @Value("${kafka.topics.errors-topic}")
    private String error_topic;

    @Value("${kafka.topics.logs-topic}")
    private String logs_topic;

    private int messageNumber = 0;
    private final KafkaTemplate<Object, Object> kafkaTemplate;
    public void sendEmail(){
        messageNumber++;
        JsonMessage jsonMessage = JsonMessage.builder()
                .number(messageNumber)
                .message("New items in inventory")
                .build();
        kafkaTemplate.send(email_topic, String.valueOf(ThreadLocalRandom.current().nextLong()), jsonMessage);
    }

    public void log(String message){
        JsonMessage jsonMessage = JsonMessage.builder()
                .number(ThreadLocalRandom.current().nextLong())
                .message(message)
                .build();
        kafkaTemplate.send(logs_topic, String.valueOf(ThreadLocalRandom.current().nextLong()), jsonMessage);
    }

    public void error(String message){
        JsonMessage jsonMessage = JsonMessage.builder()
                .number(ThreadLocalRandom.current().nextLong())
                .message(message)
                .build();
        kafkaTemplate.send(error_topic, String.valueOf(ThreadLocalRandom.current().nextLong()), jsonMessage);
    }
}
