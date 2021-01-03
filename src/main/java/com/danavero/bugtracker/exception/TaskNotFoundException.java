package com.danavero.bugtracker.exception;

import lombok.Getter;

@Getter
public class TaskNotFoundException extends RuntimeException {

    private final Long id;

    private TaskNotFoundException(final Long id) {
        super();
        this.id = id;
    }

    public static TaskNotFoundException forId(final Long id) {
        return new TaskNotFoundException(id);
    }
}
