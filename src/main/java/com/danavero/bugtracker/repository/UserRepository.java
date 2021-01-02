package com.danavero.bugtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danavero.bugtracker.model.User;

public interface UserRepository extends JpaRepository<User, Long> { }
