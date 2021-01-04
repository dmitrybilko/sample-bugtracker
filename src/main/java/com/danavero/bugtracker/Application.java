package com.danavero.bugtracker;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.modelmapper.ModelMapper;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public RestTemplate restTemplate(@Value("${service.rating.root}") final String root,
        @Value("${service.rating.timeout.connect}") final Long connect,
        @Value("${service.rating.timeout.read}") final Long read) {
        return new RestTemplateBuilder()
            .rootUri(root)
            .setConnectTimeout(Duration.ofMillis(connect))
            .setReadTimeout(Duration.ofMillis(read))
            .defaultHeader("User-Agent", "")
            .build();
    }

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
