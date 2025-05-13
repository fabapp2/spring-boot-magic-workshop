package com.workshop.magic.service;

public abstract class AbstractGreetingService implements GreetingService {

    private final String prefix;

    protected AbstractGreetingService() {
        this("Hola");
    }

    protected AbstractGreetingService(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public abstract void greet(String name);

    protected String buildGreeting(String name) {
        if (this.prefix != null && !this.prefix.isEmpty()) {
            return "%s: %s %s".formatted(getClass().getSimpleName(), this.prefix, name);
        }
        return "%s: %s".formatted(getClass().getSimpleName(), name);
    }
}
