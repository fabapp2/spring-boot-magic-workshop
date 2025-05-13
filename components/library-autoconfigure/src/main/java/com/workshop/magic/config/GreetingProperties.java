package com.workshop.magic.config;

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
