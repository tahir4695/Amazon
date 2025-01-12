package com.ecommerce.Amazon.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.Amazon.Models.Category;
import com.ecommerce.Amazon.Repository.CategoryRepository;
import com.ecommerce.Amazon.Service.CategoryService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/amazon/category")
public class CategoryController {
	@Autowired
	CategoryRepository categoryrepository;
	
	@Autowired
	CategoryService categoryservice;
	@GetMapping("/addCategory")
	public String addCategory(Model model) {
		List<Category> categories = categoryrepository.findAll();
        model.addAttribute("Parentcategories", categories);
		return "addCategory";
	}
	
	/*@PostMapping("/saveCategory")
	public String saveCategory(@ModelAttribute("category") Category category) {
	    if (category.getParentCategory() != null && category.getParentCategory().getCategoryId() == null) {
	        category.setParentCategory(null); // Set parentCategory to null if "None" is selected
	    }
	    categoryrepository.save(category);
	    return "redirect:/getCategory"; // Redirect to the category listing page after saving
	}*/

	@PostMapping("/saveCategory")
	public String saveCategory(HttpServletRequest request) {
	    Category category = new Category();
	    category.setCategoryName(request.getParameter("categoryName"));
	    if(request.getParameter("parentCategoryId")!=null) {
	    	Long parentId = Long.valueOf(request.getParameter("parentCategoryId"));
	    	category.setParentCategory(categoryservice.findCategoryById(parentId));
	    }
	    else {
	    	category.setParentCategory(null);
	    }
	    categoryrepository.save(category);
	    return "redirect:/getCategory/getCategory";
	}
	@GetMapping("/getCategory")
	public String getCategory(Model model) {
		model.addAttribute("categoryTree",categoryservice.getCategoryTree());
		return "getCategory";
	}
	
	/*@PostMapping("/deleteAndUpdateCategory")
	public String deleteAndUpdateCategory(@RequestParam Long categoryId) {
		categoryservice.deleteAndUpdateCategory(categoryId);
		return "/category/getCategory";
	}*/
	@PostMapping("/deleteAndUpdateCategory")
	public ResponseEntity<String> deleteAndUpdateCategory(@RequestParam Long categoryId) {
	    try {
	        categoryservice.deleteAndUpdateCategory(categoryId);
	        return ResponseEntity.ok("Category updated successfully"); // Return HTTP 200 OK with a success message
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Failed to update category: " + e.getMessage()); // Return HTTP 500 Internal Server Error with an error message
	    }
	}
}
