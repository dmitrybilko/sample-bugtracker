package com.danavero.bugtracker.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {

    private final Long id;

    private UserNotFoundException(final Long id) {
        super();
        this.id = id;
    }

    public static UserNotFoundException forId(final Long id) {
        return new UserNotFoundException(id);
    }
}
