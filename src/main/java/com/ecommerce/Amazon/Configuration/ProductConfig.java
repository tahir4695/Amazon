package com.ecommerce.Amazon.Configuration;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ecommerce.Amazon.Models.Product;
import com.ecommerce.Amazon.Repository.CategoryRepository;
import com.ecommerce.Amazon.Repository.ProductRepository;

@Configuration
public class ProductConfig {
	@Autowired
	CategoryRepository categoryrepository;
	@Bean
	CommandLineRunner commandLineRunner(ProductRepository repository) {
		return args->{
			/*Product iPhone = new Product(
				    "iPhone",
				    "Apple phone",
				    new BigDecimal("999.99"),
				    5,
				    (Long)categoryrepository.findByCategoryName("Mobile Phones").getCategoryId(),  // categoryId
				    "images/iPhone_16.jpg"  // relative image path
				);
			Product HpPavilion = new Product(
				    "HpPavilion",
				    "Hp Laptop",
				    new BigDecimal("10000.99"),
				    4,
				    (Long)categoryrepository.findByCategoryName("Laptops").getCategoryId(),  // categoryId
				    "images/iPhone_16.jpg"  // relative image path
				);*/
			Product iPhone = new Product(
				    "iPhone",
				    "Apple phone",
				    new BigDecimal("999.99"),
				    5,
				    1L,  // categoryId
				    "iPhone_16.jpg"  // relative image path
				);
			Product HpPavilion = new Product(
				    "HpPavilion",
				    "Hp Laptop",
				    new BigDecimal("10000.99"),
				    4,
				    2L,  // categoryId
				    "iPhone_16.jpg"  // relative image path
				);
			repository.saveAll(List.of(iPhone,HpPavilion));
		};
	}
}
