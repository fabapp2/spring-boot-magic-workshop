package com.workshop.magic.config;

import com.workshop.magic.service.GreetingService;
import com.workshop.magic.service.slf4j.BeepGreetingService;
import com.workshop.magic.service.slf4j.LoggerGreetingService;
import com.workshop.magic.service.stdout.StdOutGreetingService;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnClass(GreetingService.class)
@EnableConfigurationProperties(GreetingProperties.class)
public class GreetingAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(StdOutGreetingService.class)
    @ConditionalOnProperty(name = "workshop.greeting.type", havingValue = "stdout", matchIfMissing = true)
    GreetingService stdOutGreetingService(GreetingProperties properties) {
        return new StdOutGreetingService(properties.getText());
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(LoggerGreetingService.class)
    @ConditionalOnProperty(name = "workshop.greeting.type", havingValue = "logger")
    GreetingService slf4jGreetingService(GreetingProperties properties) {
        return new LoggerGreetingService(properties.getText());
    }

    @Bean
    @ConditionalOnMissingBean
    @MyCustomCondition
    @ConditionalOnClass(BeepGreetingService.class)
    GreetingService beepGreetingService(GreetingProperties properties) {
        return new BeepGreetingService(properties.getText());
    }
}
