package com.danavero.bugtracker.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.modelmapper.ModelMapper;

import com.danavero.bugtracker.dto.UserDto;
import com.danavero.bugtracker.exception.UnitNotFoundException;
import com.danavero.bugtracker.model.User;
import com.danavero.bugtracker.repository.UnitRepository;
import com.danavero.bugtracker.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepo;

    private final UnitRepository unitRepo;

    private final ModelMapper modelMapper;

    private final ObjectMapper objectMapper;

    private final RestTemplate restTemplate;

    @Value("${service.rating.endpoint.users}")
    private String route;

    @Value("${service.rating.default}")
    private int rating;

    @Transactional
    public List<UserDto> create(@NonNull final UserDto... users) {
        return userRepo
            .saveAll(Arrays
                .stream(users)
                .map(user -> User
                    .builder()
                    .name(user.getName())
                    .unit(unitRepo
                        .findById(user
                            .getUnit()
                            .getId())
                        .orElseThrow(() -> UnitNotFoundException.forId(user
                            .getUnit()
                            .getId())))
                    .build())
                .collect(Collectors.toList()))
            .stream()
            .map(status -> modelMapper.map(status, UserDto.class))
            .collect(Collectors.toList());
    }

    public UserDto rate(@NonNull final UserDto user) {
        try {
            user.setRating(objectMapper
                .readTree(restTemplate
                    .getForEntity(route + user.getId(), String.class)
                    .getBody())
                .path("data")
                .path("id")
                .asInt(rating));
        } catch (final RuntimeException | JsonProcessingException e) {
            log.warn("Unable to obtain user '{}' rating: {}", user.getId(), e.getMessage());
            user.setRating(rating);
        }
        return user;
    }
}
