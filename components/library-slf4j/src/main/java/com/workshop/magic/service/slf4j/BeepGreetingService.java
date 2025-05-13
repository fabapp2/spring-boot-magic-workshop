package com.workshop.magic.service.slf4j;

import java.awt.*;

import com.workshop.magic.service.AbstractGreetingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeepGreetingService extends AbstractGreetingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeepGreetingService.class);

    public BeepGreetingService() {
        super();
    }

    public BeepGreetingService(String prefix) {
        super(prefix);
    }

    @Override
    public void greet(String name) {
        LOGGER.info(buildGreeting(name));
        Toolkit.getDefaultToolkit().beep();
    }

}
