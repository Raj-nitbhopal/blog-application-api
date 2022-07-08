package com.rajan.blog.backend.payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PostResponse {

		private List<PostDto> content;
		private int PageNumber;
		private int pageSize;
		private long totalElement;
		private int totalPages;
		private boolean lastPage;
		
		
}
