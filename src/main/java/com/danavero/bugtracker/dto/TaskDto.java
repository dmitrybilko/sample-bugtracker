package com.danavero.bugtracker.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TaskDto extends AuditableDto {

    private Long id;

    private String title;

    private String description;

    private StatusDto status;

    private UserDto author;

    private UserDto assignee;

    private List<CommentDto> comments;

    private List<AttachmentDto> attachments;
}
