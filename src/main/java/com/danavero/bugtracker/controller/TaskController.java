package com.danavero.bugtracker.controller;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.danavero.bugtracker.dto.TaskCreate;
import com.danavero.bugtracker.dto.TaskDto;
import com.danavero.bugtracker.dto.TaskUpdate;
import com.danavero.bugtracker.service.TaskService;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService service;

    @PostMapping
    public ResponseEntity<TaskDto> create(@Valid @RequestBody final TaskCreate task,
        final HttpServletRequest request) {
        final TaskDto dto = service.create(task);
        return ResponseEntity
            .created(URI.create(request
                .getRequestURL()
                .append("/")
                .append(dto.getId())
                .toString()))
            .body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> read(@PathVariable final Long id) {
        return ResponseEntity.of(service.read(id));
    }

    @GetMapping
    public ResponseEntity<Page<TaskDto>> read(@RequestParam(required = false) final Long unit,
        final Pageable pageable) {
        return ResponseEntity.ok(service.read(unit, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable final Long id,
        @Valid @RequestBody final TaskUpdate task) {
        service.update(id, task);
        return ResponseEntity
            .ok()
            .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        service.delete(id);
        return ResponseEntity
            .ok()
            .build();
    }
}
