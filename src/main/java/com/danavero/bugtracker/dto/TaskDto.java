package com.danavero.bugtracker.dto;

import java.time.Instant;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    private Long id;

    private String title;

    private String description;

    private StatusDto status;

    private UserDto author;

    private UserDto assignee;

    private Instant created;

    private List<CommentDto> comments;

    private List<AttachmentDto> attachments;
}
