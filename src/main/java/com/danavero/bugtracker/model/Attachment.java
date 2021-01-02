package com.danavero.bugtracker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(indexes = { @Index(columnList = "task_id") })
@Data
@EqualsAndHashCode(callSuper = false)
public class Attachment extends Auditable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false, length = 768)
    private String location;

    @ManyToOne(optional = false)
    @JoinColumn(updatable = false)
    private Task task;
}
