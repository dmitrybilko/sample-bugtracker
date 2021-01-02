package com.danavero.bugtracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danavero.bugtracker.model.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {

    Optional<Status> findByName(final String name);
}
