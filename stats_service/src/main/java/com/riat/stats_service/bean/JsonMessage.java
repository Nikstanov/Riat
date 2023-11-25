package com.riat.stats_service.bean;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JsonMessage {
    private long number;
    private String message;
}
