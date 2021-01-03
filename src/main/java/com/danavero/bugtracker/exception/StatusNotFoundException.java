package com.danavero.bugtracker.exception;

import lombok.Getter;

@Getter
public class StatusNotFoundException extends RuntimeException {

    private final Long id;

    private StatusNotFoundException(final Long id) {
        super();
        this.id = id;
    }

    public static StatusNotFoundException forId(final Long id) {
        return new StatusNotFoundException(id);
    }
}
