package com.rajan.blog.backend.services.imple;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rajan.blog.backend.entities.Category;
import com.rajan.blog.backend.entities.Post;
import com.rajan.blog.backend.entities.User;
import com.rajan.blog.backend.exceptions.ResourceNotFoundException;
import com.rajan.blog.backend.payloads.PostDto;
import com.rajan.blog.backend.payloads.PostResponse;
import com.rajan.blog.backend.repositories.CategoryRepo;
import com.rajan.blog.backend.repositories.PostRepo;
import com.rajan.blog.backend.repositories.UserRepo;
import com.rajan.blog.backend.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		// TODO Auto-generated method stub

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User ", "user Id", userId));
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", "Category Id", categoryId));

		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setUser(user);
		post.setCategory(category);
		post.setAddDate(new Date());

		Post newPost = this.postRepo.save(post);

		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		post.setPostTitle(postDto.getPostTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());

		Post updatedPost = this.postRepo.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		this.postRepo.delete(post);

	}

	@Override
	public PostResponse getAllpost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
//		int pageNumber=1;
//		int pageSize = 5;
//		Pageable P = PageRequest.of(pageNumber, pageSize);
//		Pageable P = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
//		Pageable P = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
		Sort sort = null;
		if (sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		} else {
			sort = Sort.by(sortBy).descending();
		}
		Pageable P = PageRequest.of(pageNumber, pageSize, sort);

		Page<Post> pagePosts = this.postRepo.findAll(P);
		List<Post> allPosts = pagePosts.getContent();
		List<PostDto> postDtos = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePosts.getNumber());
		postResponse.setPageSize(pagePosts.getSize());
		postResponse.setTotalElement(pagePosts.getTotalElements());
		postResponse.setTotalPages(pagePosts.getTotalPages());
		postResponse.setLastPage(pagePosts.isLast());

		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Category id", categoryId));
		List<Post> posts = this.postRepo.findByCategory(category);
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(posts, PostDto.class))
				.collect(Collectors.toList());

		return postDtos;
	}

	@Override
	public PostResponse getPostByUser(Integer userId, Integer pageNumber, Integer pageSize) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "User id", userId));
		Pageable p = PageRequest.of(pageNumber, pageSize);
		Page<Post> postPage = this.postRepo.findByUserId(userId, p);
		List<Post> allPosts = postPage.getContent();

		List<PostDto> postDtos = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(postPage.getNumber());
		postResponse.setPageSize(postPage.getSize());
		postResponse.setTotalPages(postPage.getTotalPages());
		postResponse.setTotalElement(postPage.getTotalElements());
		postResponse.setLastPage(postPage.isLast());
		return postResponse;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
//		List<Post> posts = this.postRepo.findByPostTitleContaining(keyword);
		List<Post> posts = this.postRepo.findByPostTitle("%" +keyword+ "%");
		List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

}
