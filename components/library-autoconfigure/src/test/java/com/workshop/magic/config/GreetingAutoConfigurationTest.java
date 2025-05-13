package com.workshop.magic.config;

import com.workshop.magic.service.GreetingService;
import com.workshop.magic.service.slf4j.BeepGreetingService;
import com.workshop.magic.service.slf4j.LoggerGreetingService;
import com.workshop.magic.service.stdout.StdOutGreetingService;
import org.junit.jupiter.api.Test;

import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.FilteredClassLoader;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

class GreetingAutoConfigurationTest {
    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(GreetingAutoConfiguration.class));

    @Test
    void shouldProvideStdOutGreetingServiceByDefault() {
        this.contextRunner.run(context -> {
            assertThat(context).hasSingleBean(StdOutGreetingService.class);
        });
    }

    @Test
    void shouldProvideStdOutGreetingServiceWhenPropertyIsSet() {
        this.contextRunner
                .withPropertyValues("workshop.greeting.type=stdout")
                .run(context -> {
                    assertThat(context).hasSingleBean(StdOutGreetingService.class);
                });
    }

    @Test
    void shouldProvideLoggerGreetingServiceWhenPropertyIsSet() {
        this.contextRunner
                .withPropertyValues("workshop.greeting.type=logger")
                .run(context -> {
                    assertThat(context).hasSingleBean(LoggerGreetingService.class);
                });
    }

    @Test
    void shouldBackOffIfGreetingServiceIsDefinedByUser() {
        this.contextRunner
                .withBean(GreetingService.class, UserGreetingService::new)
                .run(context -> {
                    assertThat(context).hasSingleBean(GreetingService.class);
                    assertThat(context).hasSingleBean(UserGreetingService.class);
                });
    }

    @Test
    void shouldNotUseStdOutGreetingServiceIfNotOnClasspath() {
        this.contextRunner
                .withPropertyValues("workshop.greeting.type=stdout")
                .withClassLoader(new FilteredClassLoader(StdOutGreetingService.class))
                .run(context -> {
                    assertThat(context).doesNotHaveBean(GreetingService.class);
                });
    }

    @Test
    void shouldNotUseLoggerGreetingServiceIfNotOnClasspath() {
        this.contextRunner
                .withPropertyValues("workshop.greeting.type=logger")
                .withClassLoader(new FilteredClassLoader(LoggerGreetingService.class))
                .run(context -> {
                    assertThat(context).doesNotHaveBean(GreetingService.class);
                });
    }

    @Test
    void shouldProvideBeepGreetingServiceIfSystemPropertyIsSet() {
        this.contextRunner
                .withPropertyValues("workshop.greeting.type=none")
                .withSystemProperties("my.custom.condition=true")
                .run(context -> {
                    assertThat(context).hasSingleBean(BeepGreetingService.class);
                });
    }

    @Test
    void shouldNotUseBeepGreetingServiceIfNotOnClasspath() {
        this.contextRunner
                .withPropertyValues("workshop.greeting.type=none")
                .withSystemProperties("my.custom.condition=true")
                .withClassLoader(new FilteredClassLoader(BeepGreetingService.class))
                .run(context -> {
                    assertThat(context).doesNotHaveBean(GreetingService.class);
                });
    }

    private static class UserGreetingService implements GreetingService {
        @Override
        public void greet(String name) {
            System.out.println("UserGreetingService: Hello " + name);
        }
    }
}
