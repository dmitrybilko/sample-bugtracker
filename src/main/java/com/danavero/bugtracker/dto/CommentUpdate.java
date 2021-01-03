package com.danavero.bugtracker.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CommentUpdate {

    @NotBlank(message = "'description' must not be blank")
    @Size(message = "'description' size exceeds the limit (2048)", max = 2048)
    private String description;
}
