package com.danavero.bugtracker.service;

import javax.transaction.Transactional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;

import com.danavero.bugtracker.dto.TaskDto;
import com.danavero.bugtracker.dto.TaskRequest;
import com.danavero.bugtracker.exception.StatusNotFoundException;
import com.danavero.bugtracker.exception.UserNotFoundException;
import com.danavero.bugtracker.model.Task;
import com.danavero.bugtracker.model.TaskStatus;
import com.danavero.bugtracker.repository.StatusRepository;
import com.danavero.bugtracker.repository.TaskRepository;
import com.danavero.bugtracker.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService {

    private final TaskRepository taskRepo;

    private final UserRepository userRepo;

    private final StatusRepository statusRepo;

    private final ModelMapper mapper;

    @Transactional
    public TaskDto create(@NonNull final TaskRequest task) {
        return mapper.map(taskRepo.save(Task
            .builder()
            .title(task.getTitle())
            .description(task.getDescription())
            .status(statusRepo
                .findByName(TaskStatus.NEW.getName())
                .orElseThrow(() -> StatusNotFoundException.forName(TaskStatus.NEW.getName())))
            .author(userRepo
                .findById(task.getAuthor())
                .orElseThrow(() -> UserNotFoundException.forId(task.getAuthor())))
            .assignee(userRepo
                .findById(task.getAssignee())
                .orElseThrow(() -> UserNotFoundException.forId(task.getAssignee())))
            .build()), TaskDto.class);
    }
}
