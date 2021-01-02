package com.danavero.bugtracker.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.danavero.bugtracker.dto.TaskRequest;
import com.danavero.bugtracker.dto.TaskDto;
import com.danavero.bugtracker.service.TaskService;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService service;

    @PostMapping
    public ResponseEntity<TaskDto> create(@Valid @RequestBody final TaskRequest task) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(service.create(task));
    }
}
