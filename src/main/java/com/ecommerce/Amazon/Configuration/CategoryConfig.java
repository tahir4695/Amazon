package com.ecommerce.Amazon.Configuration;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ecommerce.Amazon.Models.Category;
import com.ecommerce.Amazon.Repository.CategoryRepository;

@Configuration
public class CategoryConfig {
	@Bean
	CommandLineRunner commandlinerunner(CategoryRepository repository){
		
		return args->{
			Category Electronics=new Category("Electronics",null);
			Category MobilePhones=new Category("Mobile Phones",(Category)repository.findByCategoryName(Electronics.getCategoryName()));
			Category Laptops=new Category("Laptops",(Category)repository.findByCategoryName(Electronics.getCategoryName()));
			/*
			 * Category Electronics=new Category("Electronics",1L); Category
			 * MobilePhones=new Category("Mobile Phones",2L); Category Laptops=new
			 * Category("Laptops",3L);
			 */
			repository.saveAll(List.of(Electronics,MobilePhones,Laptops));
		};
	}
}
