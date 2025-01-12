package com.ecommerce.Amazon.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import com.ecommerce.Amazon.Models.User;
import com.ecommerce.Amazon.Service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        try {
            userService.registerUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully!");
        } catch (DataIntegrityViolationException ex) {
            // Handle unique constraint violations, etc.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Duplicate entry for email or username.");
        } catch (IllegalArgumentException ex) {
            // Handle invalid arguments
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + ex.getMessage());
        } catch (Exception ex) {
            // Catch-all for unexpected errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Something went wrong. Please try again later.");
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String loginId, @RequestParam String password,HttpSession session,HttpServletRequest request) {
        try {
            boolean isAuthenticated = userService.authenticate(loginId, password);
            session = request.getSession();
            if (isAuthenticated) {
            	User user=userService.getUser(loginId);
            	session.setAttribute("loginId", user.getId());
            	session.setAttribute("accountType", user.getAccountType());
            	session.setAttribute("user", user);
                return ResponseEntity.status(HttpStatus.OK).body("Login successful!");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials!");
            }
        } catch (IllegalArgumentException ex) {
            // Handle invalid arguments
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + ex.getMessage());
        } catch (Exception ex) {
            // Catch-all for unexpected errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Something went wrong. Please try again later.");
        }
    }
    /*@PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String loginId, @RequestParam String password, HttpSession session, HttpServletRequest request) {
        try {
            boolean isAuthenticated = userService.authenticate(loginId, password);
            if (isAuthenticated) {
                User user = userService.getUser(loginId);
                
                // Save user in session
                session = request.getSession();
                session.setAttribute("loginId", user.getId());
                session.setAttribute("accountType", user.getAccountType());
                session.setAttribute("user", user);

                // Set SecurityContext
                UsernamePasswordAuthenticationToken authentication = 
                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

                return ResponseEntity.status(HttpStatus.OK).body("Login successful!");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials!");
            }
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Something went wrong. Please try again later.");
        }
    }*/

}

