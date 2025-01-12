package com.ecommerce.Amazon.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommerce.Amazon.Models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findByname(String name);
	
	@Query("SELECT p FROM Product p WHERE p.categoryId IN (SELECT c.categoryId FROM Category c WHERE c.categoryId = :categoryId OR c.parentCategory.categoryId = :categoryId)")
	List<Product> findByCategoryId(Long categoryId);
	
	@Query("SELECT p FROM Product p")
	List<Product> findAll();
	
	@Query("SELECT p FROM Product p WHERE p.productId = :productId")
    Product findByProductId(@Param("productId") Long productId);
}
