package com.workshop.magic.config;

import com.workshop.magic.service.GreetingService;
import com.workshop.magic.service.slf4j.LoggerGreetingService;
import com.workshop.magic.service.stdout.StdOutGreetingService;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnClass(GreetingService.class)
public class GreetingAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(StdOutGreetingService.class)
    @ConditionalOnMissingClass("com.workshop.magic.service.slf4j.LoggerGreetingService")
    GreetingService stdOutGreetingService() {
        return new StdOutGreetingService();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(LoggerGreetingService.class)
    GreetingService slf4jGreetingService() {
        return new LoggerGreetingService();
    }

}
