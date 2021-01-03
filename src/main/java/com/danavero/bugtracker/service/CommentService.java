package com.danavero.bugtracker.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;

import com.danavero.bugtracker.dto.CommentDto;
import com.danavero.bugtracker.dto.CommentCreate;
import com.danavero.bugtracker.dto.CommentUpdate;
import com.danavero.bugtracker.exception.CommentNotFoundException;
import com.danavero.bugtracker.exception.TaskNotFoundException;
import com.danavero.bugtracker.exception.UserNotFoundException;
import com.danavero.bugtracker.model.Comment;
import com.danavero.bugtracker.repository.CommentRepository;
import com.danavero.bugtracker.repository.TaskRepository;
import com.danavero.bugtracker.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepo;

    private final UserRepository userRepo;

    private final TaskRepository taskRepo;

    private final ModelMapper mapper;

    @Transactional
    public CommentDto create(@NonNull final CommentCreate comment) {
        return mapper.map(commentRepo.save(Comment
            .builder()
            .description(comment.getDescription())
            .author(userRepo
                .findById(comment.getAuthor())
                .orElseThrow(() -> UserNotFoundException.forId(comment.getAuthor())))
            .task(taskRepo
                .findById(comment.getTask())
                .orElseThrow(() -> TaskNotFoundException.forId(comment.getTask())))
            .build()), CommentDto.class);
    }

    public Optional<CommentDto> read(@NonNull final Long id) {
        return commentRepo
            .findById(id)
            .map(comment -> mapper.map(comment, CommentDto.class));
    }

    public Page<CommentDto> read(final Pageable pageable) {
        return commentRepo
            .findAll(pageable)
            .map(comment -> mapper.map(comment, CommentDto.class));
    }

    @Transactional
    public void update(@NonNull final Long id, @NonNull final CommentUpdate comment) {
        commentRepo
            .findById(id)
            .orElseThrow(() -> CommentNotFoundException.forId(id))
            .setDescription(comment.getDescription());
    }

    @Transactional
    public void delete(@NonNull final Long id) {
        commentRepo.delete(commentRepo
            .findById(id)
            .orElseThrow(() -> CommentNotFoundException.forId(id)));
    }
}
