package com.rajan.blog.backend.services;

import java.util.List;

import com.rajan.blog.backend.entities.Post;
import com.rajan.blog.backend.payloads.PostDto;

public interface PostService {
	

	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	PostDto updatePost(PostDto postDto, Integer postId);
	
	void deletePost(Integer postId);
	
	
	List<PostDto> getAllpost();
	
	PostDto getPostById(Integer postId);
	
	// get all post by category
	List<PostDto> getPostByCategory(Integer categoryId);
	
	//get post by user
	List<PostDto> getPostByUser(Integer userId);
	
	//search post
	
	List<Post> searchPost(String keyword);
}
