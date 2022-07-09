package com.rajan.blog.backend.services;

import java.util.List;

import com.rajan.blog.backend.entities.Post;
import com.rajan.blog.backend.payloads.PostDto;
import com.rajan.blog.backend.payloads.PostResponse;

public interface PostService {
	

	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	PostDto updatePost(PostDto postDto, Integer postId);
	
	void deletePost(Integer postId);
	
	
	//List<PostDto> getAllpost(Integer pageNumber, Integer pageSize);
	PostResponse getAllpost(Integer pageNumber, Integer pageSize,String sortBy, String sortDir);
	
	PostDto getPostById(Integer postId);
	
	// get all post by category
	List<PostDto> getPostByCategory(Integer categoryId);
	
	//get post by user
	PostResponse getPostByUser(Integer userId, Integer pageNumber, Integer pageSize);
	
	//search post
	
	List<PostDto> searchPost(String keyword);
}
