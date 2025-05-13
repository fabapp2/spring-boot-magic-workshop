package com.workshop.magic.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "workshop.greeting")
public class GreetingProperties {
    private String text = "Hello";
    private Type type = Type.STDOUT;

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type {
        STDOUT,
        LOGGER,
        NONE
    }
}
