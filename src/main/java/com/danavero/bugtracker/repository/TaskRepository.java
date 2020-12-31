package com.danavero.bugtracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.danavero.bugtracker.model.Status;
import com.danavero.bugtracker.model.Task;
import com.danavero.bugtracker.model.User;

@SuppressWarnings("unused")
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Modifying
    @Query("UPDATE Task t SET t.status = :status WHERE t.id = :id")
    int updateStatus(@Param("status") final Status status, @Param("id") final Long id);

    @Modifying
    @Query("UPDATE Task t SET t.assignee = :assignee WHERE t.id = :id")
    int updateAssignee(@Param("assignee") final User assignee, @Param("id") final Long id);

    List<Task> getAllByAssignee_Unit_IdOrderByCreatedAsc(final Long id);

    List<Task> getAllByAssignee_Unit_NameOrderByCreatedAsc(final String name);
}
