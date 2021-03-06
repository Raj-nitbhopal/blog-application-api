package com.rajan.blog.backend.payloads;

import java.util.Date;

//import com.rajan.blog.backend.entities.Category;
//import com.rajan.blog.backend.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PostDto {
	private Integer postId;
	private String  postTitle;
	
	private String content;
	
	private String imageName;
	
	private Date addDate;
	
	private CategoryDto category;
	
	private UserDto user;
	
	
}
