package com.rajan.blog.backend.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {

		private Integer categoryId;
		
		@NotEmpty
		@Size(min=4, max=50,message="Must give a valid Category Title")
		private String categoryTitle;
		
		
		@NotEmpty
		@Size(min=10, max=500, message="Category Description must be Length of 10 and Above")
		private String categoryDescription;
}
