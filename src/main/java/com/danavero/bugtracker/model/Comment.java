package com.danavero.bugtracker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import lombok.Data;

@Entity
@Table(indexes = { @Index(columnList = "task_id, created ASC") })
@Data
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 2048)
    private String description;

    @ManyToOne(optional = false)
    private User author;

    @ManyToOne(optional = false)
    private Task task;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Long created;
}
