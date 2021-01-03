package com.danavero.bugtracker.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CommentCreate {

    @NotBlank(message = "'description' must not be blank")
    @Size(message = "'description' size exceeds the limit (2048)", max = 2048)
    private String description;

    @NotNull(message = "'author' must not be null")
    private Long author;

    @NotNull(message = "'task' must not be null")
    private Long task;
}
