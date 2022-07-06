package com.rajan.blog.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rajan.blog.backend.repositories.UserRepo;

@SpringBootTest
class BlogAppApisApplicationTests {

	@Autowired
	private UserRepo userRepo;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void repotest()
	{
		String className = this.userRepo.getClass().getName();
		String packname = this.userRepo.getClass().getPackageName();
		
		System.out.println(className);
		System.out.println(packname);
	}

}
