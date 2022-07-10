package com.rajan.blog.backend.services;

import com.rajan.blog.backend.payloads.CommentDto;


public interface CommentService {

	CommentDto createComment(CommentDto commentDto, Integer postId);
	
	void deleteComment(Integer commentId);
}
