package com.ecommerce.Amazon.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommerce.Amazon.Models.Category;

import jakarta.transaction.Transactional;

//@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	@Query("SELECT c FROM Category c WHERE c.categoryName = :categoryName")
	Category findByCategoryName(@Param("categoryName") String categoryName);

	@Query("SELECT c FROM Category c WHERE c.categoryId = :categoryId")
	Category findByCategoryId(@Param("categoryId") Long categoryId);
	
	@Query("SELECT c FROM Category c WHERE (:parentCategory IS NULL AND c.parentCategory IS NULL) OR (c.parentCategory.categoryId = :parentCategory)")
	List<Category> findByParentId(@Param("parentCategory") Long parentCategory);
	
	@Query("SELECT c FROM Category c")
	List<Category> findAll();
	
	@Modifying
	@Transactional // Required for modifying queries
	@Query("UPDATE Category c SET c.parentCategory = null WHERE c.parentCategory.categoryId = :categoryId")
	void UpdateDeletedCategory(@Param("categoryId") Long categoryId);

	
	@Modifying //@Modifying: This annotation is required for any @Query that modifies the database (like DELETE, UPDATE, or INSERT statements).
	@Transactional // Required for modifying queries
	@Query("DELETE FROM Category c WHERE c.categoryId = :categoryId") //:categoryId: A placeholder for the method parameter categoryId.
	void deleteCategory(@Param("categoryId") Long categoryId);//@Param: Used to bind the method parameter to the placeholder in the query.
	
	@Modifying
	@Query("UPDATE Category c SET c.categoryName = :categoryName WHERE c.categoryId = :categoryId")
	void updateCategoryName(@Param("categoryId") Long categoryId, @Param("categoryName") String categoryName);
}
