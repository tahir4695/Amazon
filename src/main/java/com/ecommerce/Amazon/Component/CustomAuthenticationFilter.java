package com.ecommerce.Amazon.Component;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ecommerce.Amazon.Models.User;
import com.ecommerce.Amazon.Service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter {
    
	@Autowired
	HttpSession session;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
    		User user=(User)session.getAttribute("user");
            if (user!=null) {
                // Successful authentication; proceed to the next filter
            	try {
            		filterChain.doFilter(request, response);
            	}
                catch(Exception e) {
                	System.out.println("error");
                }
                return;
            }

        // Authentication failed; send 401 response
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Unauthorized");
    }
}