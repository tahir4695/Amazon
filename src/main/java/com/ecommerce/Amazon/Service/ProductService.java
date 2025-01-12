package com.ecommerce.Amazon.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.Amazon.Models.Product;
import com.ecommerce.Amazon.Repository.ProductRepository;
@Service
public class ProductService {

	@Autowired
	ProductRepository productrepository;
	
	public List<Product> findByCategoryId(Long categoryId){
		return productrepository.findByCategoryId(categoryId);
	}
	
	public void addProduct(Product product) {
		productrepository.save(product);
	}
	
	public List<Product> getAllProducts(){
		List<Product> products=productrepository.findAll();
		return products;
	}
	
	public Product findByProductId(Long productId) {
		return productrepository.findByProductId(productId);
	}
}
