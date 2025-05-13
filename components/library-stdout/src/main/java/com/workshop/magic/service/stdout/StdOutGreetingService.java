package com.workshop.magic.service.stdout;

import com.workshop.magic.service.AbstractGreetingService;

public class StdOutGreetingService extends AbstractGreetingService {
    public StdOutGreetingService() {
        super();
    }

    public StdOutGreetingService(String prefix) {
        super(prefix);
    }

    @Override
    public void greet(String name) {
        System.out.println(buildGreeting(name));
    }
}
