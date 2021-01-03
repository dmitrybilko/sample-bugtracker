package com.danavero.bugtracker.dto;

import java.time.Instant;

import lombok.Data;

@Data
public class AuditableDto {

    private Instant created;
}
