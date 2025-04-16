package ru.babich.starter.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class StarterLoggingProperties {

    @Value("${api.logging.enabled}")
    private boolean enabled;

    @Value("${api.logging.level}")
    private String level;

    @Value("${api.logging.log-headers}")
    private boolean logHeadersGlobal;

    @Value("${api.logging.log-execution-time}")
    private boolean logExecutionTimeGlobal;
}
