package com.rajan.blog.backend;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rajan.blog.backend.config.AppConstant;
import com.rajan.blog.backend.entities.Role;
import com.rajan.blog.backend.repositories.RoleRepo;

@SpringBootApplication
public class BlogAppApisApplication implements CommandLineRunner{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper(){
		
		return new ModelMapper();
	}

	@Autowired
	private RoleRepo roleRepo;
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println(this.passwordEncoder.encode("xyz"));
		
		try {
			Role role = new Role();
			role.setId(AppConstant.ROLE_ADMIN);
			role.setName("ROLE_ADMIN");
			
			Role role1 = new Role();
			role1.setId(AppConstant.ROLE_USER);
			role1.setName("ROLE_USER");
			
			List<Role> roles = List.of(role,role1);
			
			List<Role> result = this.roleRepo.saveAll(roles);
			
			result.forEach(r->{
				System.out.println(r.getName());
			});
		} catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
		}
		
	}

}
