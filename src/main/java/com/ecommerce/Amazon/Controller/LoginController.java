package com.ecommerce.Amazon.Controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ecommerce.Amazon.Models.Category;
import com.ecommerce.Amazon.Models.Product;
import com.ecommerce.Amazon.Models.User;
import com.ecommerce.Amazon.Repository.CategoryRepository;
import com.ecommerce.Amazon.Repository.ProductRepository;
import com.ecommerce.Amazon.Repository.UserRepository;

@Controller
public class LoginController {

	@Autowired
	UserRepository userRepository;
	@Autowired
	CategoryRepository repository;
	
	@Autowired
	ProductRepository productrepository;
    @GetMapping("/login")
    public String login() {
        return "login"; // Points to src/main/resources/templates/login.html
    }
    
    @GetMapping("/error")
    public String error() {
    	return "Error";
    }
    @GetMapping("/register")
    public String register() {
    	return "register";
    }
    
    @GetMapping
    public String dashboard() {
    	/*User user=new User("Tahir","tahir","Standard","tahir4112001@gmail.com","9960523876","tahir");
    	userRepository.save(user);
    	Category Electronics=new Category("Electronics",null);
		
		repository.saveAll(List.of(Electronics));
		Category MobilePhones=new Category("Mobile Phones",(Category)repository.findByCategoryName(Electronics.getCategoryName()));
		Category Laptops=new Category("Laptops",(Category)repository.findByCategoryName(Electronics.getCategoryName()));
		repository.saveAll(List.of(Electronics,MobilePhones,Laptops));
		
		Product iPhone = new Product(
			    "iPhone",
			    "Apple phone",
			    new BigDecimal("999.99"),
			    5,
			    (Long)repository.findByCategoryName("Mobile Phones").getCategoryId(),  // categoryId
			    "iPhone_16.jpg"  // relative image path
			);
		Product HpPavilion = new Product(
			    "HpPavilion",
			    "Hp Laptop",
			    new BigDecimal("10000.99"),
			    4,
			    (Long)repository.findByCategoryName("Laptops").getCategoryId(),  // categoryId
			    "gamingLaptop.jpg"  // relative image path
			);
		productrepository.saveAll(List.of(iPhone,HpPavilion));*/
    	return "dashboard";
    }
}

