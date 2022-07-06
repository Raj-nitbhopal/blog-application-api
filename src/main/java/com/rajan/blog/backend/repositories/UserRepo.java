package com.rajan.blog.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rajan.blog.backend.entities.User;

public interface  UserRepo extends JpaRepository<User, Integer> {

}
