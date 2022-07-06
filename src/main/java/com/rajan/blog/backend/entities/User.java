package com.rajan.blog.backend.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Users")
@NoArgsConstructor
@Getter
@Setter 
public class User {
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)	
		private Integer id;
		
		@Column(name="User_Name", nullable = false, length = 100)
		private String name;
		
		@Column(name="User_Email", nullable = false, length = 100)
		private String email;
		
		@Column(name="User_Password", nullable = false, length = 15)
		private String password;
		
		@Column(name="About_User", nullable = true, length = 500)
		private String about;
		
		@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
		private List<Post> posts = new ArrayList<>();
		
}
