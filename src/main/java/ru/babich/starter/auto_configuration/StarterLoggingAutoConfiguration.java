package ru.babich.starter.auto_configuration;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.babich.starter.aspect.InternalAspect;
import ru.babich.starter.properties.StarterLoggingProperties;
@Configuration
@AutoConfiguration
public class StarterLoggingAutoConfiguration {
    @Bean
    public InternalAspect apiLoggingAspect() {
        return new InternalAspect(starterLoggingProperties());
    }

    @Bean
    public StarterLoggingProperties starterLoggingProperties() {
        return new StarterLoggingProperties();
    }
}
