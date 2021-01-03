package com.danavero.bugtracker.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.danavero.bugtracker.exception.CommentNotFoundException;
import com.danavero.bugtracker.exception.StatusNotFoundException;
import com.danavero.bugtracker.exception.TaskNotFoundException;
import com.danavero.bugtracker.exception.UnitNotFoundException;
import com.danavero.bugtracker.exception.UserNotFoundException;

@ControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        final MethodArgumentNotValidException e, final HttpHeaders headers, final HttpStatus status,
        final WebRequest request) {
        return handleException(e, "Check the request body: " + e
            .getBindingResult()
            .getAllErrors()
            .stream()
            .findFirst()
            .orElse(new ObjectError("", ""))
            .getDefaultMessage(), request);
    }

    @ExceptionHandler(UnitNotFoundException.class)
    public ResponseEntity<Object> handleUnitNotFoundException(final UnitNotFoundException e,
        final WebRequest request) {
        return handleException(e, "Unable to find a unit with the ID '" + e.getId() + "'", request);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(final UserNotFoundException e,
        final WebRequest request) {
        return handleException(e, "Unable to find a user with the ID '" + e.getId() + "'", request);
    }

    @ExceptionHandler(StatusNotFoundException.class)
    public ResponseEntity<Object> handleStatusNotFoundException(final StatusNotFoundException e,
        final WebRequest request) {
        return handleException(e, "Unable to find a task status with the ID '" + e.getId() + "'",
            request);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<Object> handleTaskNotFoundException(final TaskNotFoundException e,
        final WebRequest request) {
        return handleException(e, "Unable to find a task with the ID '" + e.getId() + "'", request);
    }

    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<Object> handleCommentNotFoundException(final CommentNotFoundException e,
        final WebRequest request) {
        return handleException(e, "Unable to find a comment with the ID '" + e.getId() + "'",
            request);
    }

    private ResponseEntity<Object> handleException(final Exception e, final String message,
        final WebRequest request) {
        return handleExceptionInternal(e, GeneralError
            .builder()
            .status(HttpStatus.BAD_REQUEST.value())
            .message(message)
            .build(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
