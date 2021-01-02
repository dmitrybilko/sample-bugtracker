package com.danavero.bugtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import org.modelmapper.ModelMapper;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
