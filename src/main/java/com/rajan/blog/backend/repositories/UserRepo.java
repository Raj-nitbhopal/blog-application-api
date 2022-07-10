package com.rajan.blog.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rajan.blog.backend.entities.User;

public interface  UserRepo extends JpaRepository<User, Integer> {

	
	Optional<User> findByEmail(String email);
}
