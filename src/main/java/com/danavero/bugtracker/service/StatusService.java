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

import com.danavero.bugtracker.dto.StatusDto;
import com.danavero.bugtracker.model.Status;
import com.danavero.bugtracker.repository.StatusRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatusService {

    private final StatusRepository repo;

    private final ModelMapper mapper;

    @Transactional
    public List<StatusDto> create(@NonNull final String... names) {
        log.info("Creating unit entries");
        return repo
            .saveAll(Arrays
                .stream(names)
                .map(name -> Status
                    .builder()
                    .name(name)
                    .build())
                .collect(Collectors.toList()))
            .stream()
            .map(status -> mapper.map(status, StatusDto.class))
            .collect(Collectors.toList());
    }
}
