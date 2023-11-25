package com.riat.stats_service;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Level;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class StatsServiceApplication {

    @Value(value = "${logging.mark}")
    private String log_mark;

    public static void main(String[] args) {
        SpringApplication.run(StatsServiceApplication.class, args);
        log.info(MarkerFactory.getMarker("KAFKA"), "Some");
    }
    
}
