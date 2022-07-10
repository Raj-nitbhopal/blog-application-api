package com.rajan.blog.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rajan.blog.backend.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
