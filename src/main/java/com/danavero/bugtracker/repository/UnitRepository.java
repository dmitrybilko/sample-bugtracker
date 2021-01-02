package com.danavero.bugtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danavero.bugtracker.model.Unit;

public interface UnitRepository extends JpaRepository<Unit, Long> { }
