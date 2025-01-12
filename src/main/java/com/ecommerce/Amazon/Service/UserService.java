package com.ecommerce.Amazon.Service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.Amazon.Models.User;
import com.ecommerce.Amazon.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void registerUser(User user) {
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    public User getUser(String loginId) {
    	return userRepository.findByLoginId(loginId);
    }
    public boolean authenticate(String loginId, String password) {
        User user = userRepository.findByLoginId(loginId);
        //return user != null && passwordEncoder.matches(password, user.getPassword());
        return user != null && password.equals(user.getPassword());
    }
}
