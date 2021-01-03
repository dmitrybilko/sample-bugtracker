package com.danavero.bugtracker.exception;

import lombok.Getter;

@Getter
public class CommentNotFoundException extends RuntimeException {

    private final Long id;

    private CommentNotFoundException(final Long id) {
        super();
        this.id = id;
    }

    public static CommentNotFoundException forId(final Long id) {
        return new CommentNotFoundException(id);
    }
}
