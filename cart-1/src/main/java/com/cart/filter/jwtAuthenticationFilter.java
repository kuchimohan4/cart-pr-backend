package com.cart.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class jwtAuthenticationFilter extends OncePerRequestFilter {
	
	private String username;
	
	@Autowired
	private com.cart.util.jwtServiceutil jwtServiceutil;
	
	@Autowired
	private routeValidater routeValidater;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		
		
		if (!routeValidater.isSecured.test(request)) {
            filterChain.doFilter(request, response);
            return;
        }
		
		String jwtToken = extractJwtToken(request);
		if (jwtToken != null) {
			
			if (jwtTokenIsValid(jwtToken)) {
				
				request.setAttribute("userName", username);
				
				filterChain.doFilter(request, response);
		    }
			else {
				
				String newuri = "/order/unauthorized";
				HttpServletRequest wRequest = new HttpServletRequestWrapper(request) {
					@Override
					public String getMethod() {
						return "GET";
					}
				};
				
				request.getRequestDispatcher(newuri).forward(wRequest, response);
			}
		}
		else {

			String newuri = "/order/missingAuth";
			HttpServletRequest wRequest = new HttpServletRequestWrapper(request) {
				@Override
				public String getMethod() {
					return "GET";
				}
			};
			
			request.getRequestDispatcher(newuri).forward(wRequest, response);
			
		}
		
	 
	    
		
		
	}
	private String extractJwtToken(HttpServletRequest request) {
	    String header = request.getHeader("Authorization");
	    if (header != null && header.startsWith("Bearer ")) {
	        return header.substring(7);
	    }
	    return null;
	}
	 
	private boolean jwtTokenIsValid(String jwtToken) {
		try {
			
	       this.username= jwtServiceutil.validateToken(jwtToken);
	        
	        return true;
	    } catch (Exception ex) {
	        return false;
	    }
	}

}
