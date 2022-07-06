package com.rajan.blog.backend.services;

import java.util.List;

//import com.rajan.blog.backend.entities.User;
import com.rajan.blog.backend.payloads.UserDto;

public interface UserServices {
	
	UserDto createUser(UserDto  user);
	
	UserDto updateUser(UserDto user, Integer userId);
	
	UserDto getUserById(Integer userId);
	
	List<UserDto> getAllUsers();
	
	void deleteUser(Integer userId);
	
	
}
