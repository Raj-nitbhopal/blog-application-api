package com.rajan.blog.backend.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	
		
		//1.	get token
		
		String requestToken = request.getHeader("Authorization");
		
		// Bearer 2352523sdgsg
		System.out.println(requestToken);
		String username = null;
		String token = null;
		if(requestToken!=null && requestToken .startsWith("Bearer"))
		{
			 token = requestToken.substring(7);
			 try {
				  username = this.jwtTokenHelper.getUsernameFromToken(token);
			 }
			catch(IllegalArgumentException e)
			{
				System.out.println("Unable to get JWT Token");
			}
			 catch(ExpiredJwtException e)
			 {
				 System.out.println("JWT Token Has expired");
			 }
			 catch (MalformedJwtException e) {
				System.out.println("Invalid JWT");
			}
		}
		else
		{
			System.out.println("Jwt Token does not with begin with Bearer");
		}
		
		// once we get the token, now validate
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
		{
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			if(this.jwtTokenHelper.validateToken(token, userDetails))
			{
				// shi work kr raha hai
				// Auhtentication krna hai
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
			else
			{
				System.out.println("Invalid jwt Token");
			}
		}
		else
		{
			System.out.println("username is null or context is not null");
		}
		
		filterChain.doFilter(request, response);
		
	}

}
