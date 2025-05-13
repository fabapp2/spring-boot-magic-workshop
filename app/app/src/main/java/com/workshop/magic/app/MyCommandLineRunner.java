package com.workshop.magic.app;

import com.workshop.magic.service.GreetingService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
class MyCommandLineRunner implements CommandLineRunner {
    private final GreetingService greetingService;

    MyCommandLineRunner(GreetingService gs) {
        this.greetingService = gs;
    }

    @Override
    public void run(String... args) {
        this.greetingService.greet("Spring I/O Barcelona");
    }
}
