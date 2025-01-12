package com.ecommerce.Amazon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
//import org.springframework.stereotype.Controller;

import com.ecommerce.Amazon.Component.CustomAuthenticationFilter;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableSpringHttpSession
public class AmazonApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmazonApplication.class, args);
	}
	
	@Bean
    public FilterRegistrationBean<CustomAuthenticationFilter> authenticationFilter(CustomAuthenticationFilter customAuthenticationFilter) {
        FilterRegistrationBean<CustomAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(customAuthenticationFilter);
        registrationBean.addUrlPatterns("/amazon/*"); // Set the URL patterns to apply the filter /api/v1/*
        return registrationBean;
    }
}
