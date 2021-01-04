package com.danavero.bugtracker.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;

import com.danavero.bugtracker.dto.UnitDto;
import com.danavero.bugtracker.model.Unit;
import com.danavero.bugtracker.repository.UnitRepository;

@Service
@RequiredArgsConstructor
public class UnitService {

    private final UnitRepository unitRepo;

    private final ModelMapper modelMapper;

    @Transactional
    public List<UnitDto> create(@NonNull final String... names) {
        return unitRepo
            .saveAll(Arrays
                .stream(names)
                .map(name -> Unit
                    .builder()
                    .name(name)
                    .build())
                .collect(Collectors.toList()))
            .stream()
            .map(status -> modelMapper.map(status, UnitDto.class))
            .collect(Collectors.toList());
    }
}
