package com.danavero.bugtracker.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.danavero.bugtracker.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> getAllByAssignee_Unit_Id(final Long id, final Pageable pageable);
}
