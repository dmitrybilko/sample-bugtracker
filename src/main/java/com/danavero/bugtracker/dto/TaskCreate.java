package com.danavero.bugtracker.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class TaskCreate {

    @NotBlank(message = "'title' must not be blank")
    @Size(message = "'title' size exceeds the limit (255)", max = 255)
    private String title;

    @Size(message = "'description' size exceeds the limit (2048)", max = 2048)
    private String description;

    @NotNull(message = "'author' must not be null")
    private Long author;

    @NotNull(message = "'assignee' must not be null")
    private Long assignee;
}
