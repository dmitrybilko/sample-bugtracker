package com.danavero.bugtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danavero.bugtracker.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> { }
