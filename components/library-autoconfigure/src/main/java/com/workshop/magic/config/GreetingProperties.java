package com.workshop.magic.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.DeprecatedConfigurationProperty;

@ConfigurationProperties(prefix = "workshop.greeting")
public class GreetingProperties {
    private Type type = Type.STDOUT;
    private String prefix = "Hello";

    @DeprecatedConfigurationProperty(replacement = "workshop.greeting.prefix")
    @Deprecated
    public String getText() {
        return this.prefix;
    }

    public void setText(String text) {
        this.prefix = text;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public enum Type {
        STDOUT,
        LOGGER,
        NONE
    }
}
