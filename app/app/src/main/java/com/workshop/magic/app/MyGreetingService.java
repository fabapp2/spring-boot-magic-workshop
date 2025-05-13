package com.workshop.magic.app;

import com.workshop.magic.service.AbstractGreetingService;

import org.springframework.stereotype.Service;

@Service
class MyGreetingService extends AbstractGreetingService {

    @Override
    public void greet(String name) {
        System.out.println(buildGreeting(name));
    }

}
