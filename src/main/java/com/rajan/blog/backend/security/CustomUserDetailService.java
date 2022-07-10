package com.rajan.blog.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rajan.blog.backend.entities.User;
import com.rajan.blog.backend.exceptions.ResourceNotFoundException;
import com.rajan.blog.backend.repositories.UserRepo;

@Service
public class CustomUserDetailService  implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		//loading user from database by email
		
		User user = this.userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User", " email : "+username, 0));
		
		
		return user;
	}

}
