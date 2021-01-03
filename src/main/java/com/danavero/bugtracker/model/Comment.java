package com.danavero.bugtracker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(indexes = { @Index(columnList = "task_id, created ASC") })
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Comment extends Auditable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 2048)
    private String description;

    @ManyToOne(optional = false)
    private User author;

    @ManyToOne(optional = false)
    @JoinColumn(updatable = false)
    private Task task;
}
