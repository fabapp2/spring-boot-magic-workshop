package com.workshop.magic.config;

import java.util.Locale;

import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

class OnCustomCondition extends SpringBootCondition {
    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String value = System.getProperty("my.custom.condition");
        if (value == null) {
            return ConditionOutcome.noMatch("No 'my.custom.condition' system property found");
        }
        if (value.toLowerCase(Locale.ROOT).equals("true")) {
            return ConditionOutcome.match("'my.custom.condition' system property is true");
        }
        return ConditionOutcome.noMatch("'my.custom.condition' system property is '%s'".formatted(value));
    }
}
