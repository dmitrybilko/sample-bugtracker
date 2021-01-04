package com.danavero.bugtracker.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;

import com.danavero.bugtracker.dto.StatusDto;
import com.danavero.bugtracker.model.Status;
import com.danavero.bugtracker.repository.StatusRepository;

@Service
@RequiredArgsConstructor
public class StatusService {

    private final StatusRepository statusRepo;

    private final ModelMapper modelMapper;

    @Transactional
    public List<StatusDto> create(@NonNull final String... names) {
        return statusRepo
            .saveAll(Arrays
                .stream(names)
                .map(name -> Status
                    .builder()
                    .name(name)
                    .build())
                .collect(Collectors.toList()))
            .stream()
            .map(status -> modelMapper.map(status, StatusDto.class))
            .collect(Collectors.toList());
    }
}
