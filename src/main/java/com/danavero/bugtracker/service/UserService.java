package com.danavero.bugtracker.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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

    private final ModelMapper mapper;

    @Transactional
    public List<UserDto> create(@NonNull final UserDto... users) {
        log.info("Creating user entries");
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
            .map(status -> mapper.map(status, UserDto.class))
            .collect(Collectors.toList());
    }
}
