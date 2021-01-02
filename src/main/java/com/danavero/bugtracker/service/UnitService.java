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

import com.danavero.bugtracker.dto.UnitDto;
import com.danavero.bugtracker.model.Unit;
import com.danavero.bugtracker.repository.UnitRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class UnitService {

    private final UnitRepository repo;

    private final ModelMapper mapper;

    @Transactional
    public List<UnitDto> create(@NonNull final String... names) {
        log.info("Creating unit entries");
        return repo
            .saveAll(Arrays
                .stream(names)
                .map(name -> Unit
                    .builder()
                    .name(name)
                    .build())
                .collect(Collectors.toList()))
            .stream()
            .map(status -> mapper.map(status, UnitDto.class))
            .collect(Collectors.toList());
    }
}
