package com.danavero.bugtracker.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;

import com.danavero.bugtracker.dto.TaskDto;
import com.danavero.bugtracker.dto.TaskCreate;
import com.danavero.bugtracker.dto.TaskUpdate;
import com.danavero.bugtracker.exception.CommentNotFoundException;
import com.danavero.bugtracker.exception.StatusNotFoundException;
import com.danavero.bugtracker.exception.TaskNotFoundException;
import com.danavero.bugtracker.exception.UserNotFoundException;
import com.danavero.bugtracker.model.Task;
import com.danavero.bugtracker.model.TaskStatus;
import com.danavero.bugtracker.repository.StatusRepository;
import com.danavero.bugtracker.repository.TaskRepository;
import com.danavero.bugtracker.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final UserService userService;

    private final TaskRepository taskRepo;

    private final UserRepository userRepo;

    private final StatusRepository statusRepo;

    private final ModelMapper modelMapper;

    @Transactional
    public TaskDto create(@NonNull final TaskCreate task) {
        return extend(modelMapper.map(taskRepo.save(Task
            .builder()
            .title(task.getTitle())
            .description(task.getDescription())
            .status(statusRepo
                .findByName(TaskStatus.NEW.getName())
                .orElseThrow(() -> StatusNotFoundException.forId(-1L)))
            .author(userRepo
                .findById(task.getAuthor())
                .orElseThrow(() -> UserNotFoundException.forId(task.getAuthor())))
            .assignee(userRepo
                .findById(task.getAssignee())
                .orElseThrow(() -> UserNotFoundException.forId(task.getAssignee())))
            .comments(Collections.emptyList())
            .attachments(Collections.emptyList())
            .build()), TaskDto.class));
    }

    public Optional<TaskDto> read(@NonNull final Long id) {
        return taskRepo
            .findById(id)
            .map(task -> extend(modelMapper.map(task, TaskDto.class)));
    }

    public Page<TaskDto> read(final Long unit, final Pageable pageable) {
        final Page<Task> tasks = unit != null
            ? taskRepo.getAllByAssignee_Unit_Id(unit, pageable)
            : taskRepo.findAll(pageable);
        return tasks.map(task -> extend(modelMapper.map(task, TaskDto.class)));
    }

    @Transactional
    public void update(@NonNull final Long id, @NonNull final TaskUpdate dto) {
        final Task task = taskRepo
            .findById(id)
            .orElseThrow(() -> CommentNotFoundException.forId(id));
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(statusRepo
            .findById(dto.getStatus())
            .orElseThrow(() -> StatusNotFoundException.forId(dto.getStatus())));
        task.setAssignee(userRepo
            .findById(dto.getAssignee())
            .orElseThrow(() -> UserNotFoundException.forId(dto.getAssignee())));
    }

    @Transactional
    public void delete(@NonNull final Long id) {
        taskRepo.delete(taskRepo
            .findById(id)
            .orElseThrow(() -> TaskNotFoundException.forId(id)));
    }

    private TaskDto extend(@NonNull final TaskDto task) {
        task.setAuthor(userService.rate(task.getAuthor()));
        task.setAssignee(userService.rate(task.getAssignee()));
        return task;
    }
}
