package com.danavero.bugtracker.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TaskStatus {

    NEW("New"), IN_PROGRESS("In Progress"), DONE("Done");

    private final String name;
}
