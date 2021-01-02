package com.danavero.bugtracker.exception;

import lombok.Getter;

@Getter
public class StatusNotFoundException extends RuntimeException {

    private final String name;

    private StatusNotFoundException(final String name) {
        super();
        this.name = name;
    }

    public static StatusNotFoundException forName(final String name) {
        return new StatusNotFoundException(name);
    }
}
