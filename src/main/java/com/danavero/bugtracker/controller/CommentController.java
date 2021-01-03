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
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.danavero.bugtracker.dto.CommentDto;
import com.danavero.bugtracker.dto.CommentCreate;
import com.danavero.bugtracker.dto.CommentUpdate;
import com.danavero.bugtracker.service.CommentService;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService service;

    @PostMapping
    public ResponseEntity<CommentDto> create(@Valid @RequestBody final CommentCreate comment,
        final HttpServletRequest request) {
        final CommentDto dto = service.create(comment);
        return ResponseEntity
            .created(URI.create(request
                .getRequestURL()
                .append("/")
                .append(dto.getId())
                .toString()))
            .body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> read(@PathVariable final Long id) {
        return ResponseEntity.of(service.read(id));
    }

    @GetMapping
    public ResponseEntity<Page<CommentDto>> read(final Pageable pageable) {
        return ResponseEntity.ok(service.read(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable final Long id,
        @Valid @RequestBody final CommentUpdate comment) {
        service.update(id, comment);
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
