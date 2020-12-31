package com.danavero.bugtracker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(indexes = { @Index(columnList = "task_id") })
@Data
public class Attachment {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false, length = 2048)
    private String location;

    @ManyToOne(optional = false)
    private Task task;
}
