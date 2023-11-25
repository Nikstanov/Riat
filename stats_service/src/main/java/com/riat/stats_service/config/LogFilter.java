package com.riat.stats_service.config;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

public class LogFilter extends Filter<ILoggingEvent> {

    @Override
    public FilterReply decide(ILoggingEvent event) {
        if(event.getMarkerList().stream().anyMatch(marker -> marker.contains("KAFKA"))){
            return FilterReply.ACCEPT;
        }
        else{
            return FilterReply.DENY;
        }
    }
}
