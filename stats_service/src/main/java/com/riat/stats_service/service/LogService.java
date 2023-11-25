package com.riat.stats_service.service;


import com.riat.stats_service.bean.JsonMessage;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LogService {

    @Value(value = "${logging.mark}")
    private String log_mark;

    @KafkaListener(id = "stats_listener1", topics = "${kafka.topics.logs-topic}", containerFactory = "kafkaListenerContainerFactory")
    public void logging(@Payload  JsonMessage jsonMessage){
        log.info(MarkerFactory.getMarker(log_mark), jsonMessage.getMessage());
    }

    @KafkaListener(id = "stats_listener2", topics = "${kafka.topics.errors-topic}", containerFactory = "kafkaListenerContainerFactory")
    public void errors(@Payload JsonMessage jsonMessage){
        log.error(MarkerFactory.getMarker(log_mark), jsonMessage.getMessage());
    }
}
