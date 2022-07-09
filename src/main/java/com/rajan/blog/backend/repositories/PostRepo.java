package com.rajan.blog.backend.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rajan.blog.backend.entities.Category;
import com.rajan.blog.backend.entities.Post;
import com.rajan.blog.backend.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	Page<Post> findByUserId(Integer userId, Pageable pageable);
}
