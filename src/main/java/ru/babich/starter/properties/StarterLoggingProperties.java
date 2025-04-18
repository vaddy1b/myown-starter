package ru.babich.starter.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties
public class StarterLoggingProperties {

    @Value("${starter.logging.enabled}")
    private boolean enabled;

    @Value("${starter.logging.level}")
    private String level;

    @Value("${starter.logging.log-headers}")
    private boolean logHeadersGlobal;

    @Value("${starter.logging.log-execution-time}")
    private boolean logExecutionTimeGlobal;
}
