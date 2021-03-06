package com.rajan.blog.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rajan.blog.backend.config.AppConstant;
import com.rajan.blog.backend.payloads.ApiResponse;
import com.rajan.blog.backend.payloads.PostDto;
import com.rajan.blog.backend.payloads.PostResponse;
import com.rajan.blog.backend.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;
	//create
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(
			@RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId
			)
	{
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		
		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
	}
	
	//get post by user id
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<PostResponse> getPostByuser(
			@PathVariable Integer userId,
			@RequestParam(value="pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false)Integer pageNumber,
			@RequestParam(value="pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false)Integer pageSize)
	{
		PostResponse posts = this.postService.getPostByUser(userId,pageNumber, pageSize);
		return new ResponseEntity<PostResponse>(posts, HttpStatus.OK);
	}
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostBycategory(@PathVariable Integer categoryId)
	{
		List<PostDto> posts = this.postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}
	
	// get all post
	@GetMapping("/posts") 
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER,required = false)Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE,required = false)Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstant.SORT_BY, required = false)String sortBy,
			@RequestParam(value = "sortDir",defaultValue = AppConstant.SORT_DIR, required = false)String sortDir)
	{
		   PostResponse postResponse = this.postService.getAllpost(pageNumber, pageSize,sortBy,sortDir);
		  return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}
	
	//get post by post id;
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId)
	{
		PostDto postById = this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postById, HttpStatus.OK);
	}
	
	//delete post by id
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId)
	{
		this.postService.deletePost(postId);
		return new ApiResponse("Post is Successfully Deleted !!! ", true);
	}
	
	//update post
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId)
	{
		PostDto updatedPost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
	}
	
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> SearchPostByTitle(@PathVariable ("keywords")String keywords)
	{
		List<PostDto> searchedPost = this.postService.searchPost(keywords);
		return new ResponseEntity<List<PostDto>>(searchedPost, HttpStatus.OK);
	}
}
