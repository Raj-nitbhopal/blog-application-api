package com.rajan.blog.backend.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.rajan.blog.backend.entities.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private Integer id;
	
	@NotEmpty
	@Size(min=3,message="Name should be greater than length of 3")
	private String name;
	
	@Email
	private String email;
	
	@NotEmpty
	@Size(min=8,max=20, message="Password must be length of 5-15 Alphanumeric Characters ")
	private String password;
	
	@NotEmpty
	private String about;
	
	private Set<RoleDto> roles = new HashSet<>();
}
