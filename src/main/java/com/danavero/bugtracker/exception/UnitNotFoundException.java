package com.danavero.bugtracker.exception;

import lombok.Getter;

@Getter
public class UnitNotFoundException extends RuntimeException {

    private final Long id;

    private UnitNotFoundException(final Long id) {
        super();
        this.id = id;
    }

    public static UnitNotFoundException forId(final Long id) {
        return new UnitNotFoundException(id);
    }
}
