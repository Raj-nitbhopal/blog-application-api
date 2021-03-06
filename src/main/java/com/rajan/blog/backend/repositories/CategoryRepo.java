package com.rajan.blog.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rajan.blog.backend.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
