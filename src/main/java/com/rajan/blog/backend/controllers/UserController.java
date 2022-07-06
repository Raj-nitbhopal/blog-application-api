package com.rajan.blog.backend.controllers;

import java.util.List;
//import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajan.blog.backend.payloads.ApiResponse;
import com.rajan.blog.backend.payloads.UserDto;
import com.rajan.blog.backend.services.UserServices;

@RestController
@RequestMapping("api/users")
public class UserController {

	@Autowired
	private UserServices userService;
	
	//POST - Create User
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser( @Valid @RequestBody UserDto userDto)
	{
		UserDto createUserDto = this.userService.createUser(userDto);
		
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	}
	
	//PUT - Update user
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateuser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer uId)
	{
		UserDto updatedUser = this.userService.updateUser(userDto, uId);
		return ResponseEntity.ok(updatedUser);
	}
	
	
	//DELETE - Delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uId)
	{
		this.userService.deleteUser(uId);
		//return ResponseEntity.ok(Map.of("message", "User Deleted Successfuy"));
		//		return new ResponseEntity(Map.of("message", "User Deleted Successfuy"), HttpStatus.OK);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);
	}
	
	//GET - get All user
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllusers()
	{
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	
	//GET - get single user
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getAllusers(@PathVariable  Integer userId)
	{
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}
	
	
}
