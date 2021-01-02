package com.danavero.bugtracker.configuration;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

import com.danavero.bugtracker.dto.UnitDto;
import com.danavero.bugtracker.dto.UserDto;
import com.danavero.bugtracker.model.TaskStatus;
import com.danavero.bugtracker.service.StatusService;
import com.danavero.bugtracker.service.UnitService;
import com.danavero.bugtracker.service.UserService;

@Configuration
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {

    private final UnitService units;

    private final UserService users;

    private final StatusService statuses;

    @Override
    public void run(final ApplicationArguments args) {
        loadUnits();
        loadUsers();
        loadStatuses();
    }

    private void loadUnits() {
        units.create("Administration", "Financial Department", "Engineering Department");
    }

    private void loadUsers() {
        users.create(UserDto
            .builder()
            .name("Anna")
            .unit(UnitDto
                .builder()
                .id(1L)
                .build())
            .build(), UserDto
            .builder()
            .name("Ivan")
            .unit(UnitDto
                .builder()
                .id(2L)
                .build())
            .build(), UserDto
            .builder()
            .name("Max")
            .unit(UnitDto
                .builder()
                .id(3L)
                .build())
            .build());
    }

    private void loadStatuses() {
        statuses.create(TaskStatus.NEW.getName(), TaskStatus.IN_PROGRESS.getName(),
            TaskStatus.DONE.getName());
    }
}
