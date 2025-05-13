package com.workshop.magic.service.slf4j;

import com.workshop.magic.service.AbstractGreetingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerGreetingService extends AbstractGreetingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerGreetingService.class);

    public LoggerGreetingService() {
        super();
    }

    public LoggerGreetingService(String prefix) {
        super(prefix);
    }

    @Override
    public void greet(String name) {
        LOGGER.info(buildGreeting(name));
    }
}
