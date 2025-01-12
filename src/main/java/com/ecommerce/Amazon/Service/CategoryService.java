package com.ecommerce.Amazon.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.Amazon.Models.Category;
import com.ecommerce.Amazon.Repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryrepository;
	
	public void savecategory(Category category,String parentCategoryName,Long parentCategoryId) {
		if(category.getParentCategory()==null) {
			if(parentCategoryId!=null) {
				category.setParentCategory(findCategoryById(parentCategoryId));
			}
			else if(parentCategoryName!=""){
				category.setParentCategory(findCategoryByName(parentCategoryName));
			}
		}
		categoryrepository.save(category);
	}
	
	public Category findCategoryById(Long categoryId) {
		return categoryrepository.findByCategoryId(categoryId);
	}
	
	public Category findCategoryByName(String categoryName) {
		return categoryrepository.findByCategoryName(categoryName);
	}
	
	public List<Category> findAllTheCategory(){
		return categoryrepository.findAll();
	}
	
	public void deleteCategory(Long categoryId) {
		categoryrepository.deleteCategory(categoryId);
	}
	
	public void updateCategoryName(Long categoryId,String categoryName) {
		categoryrepository.updateCategoryName(categoryId,categoryName);
	}
	
	
    public Map<Category, List<Category>> getCategoryTree() {
        List<Category> categories = categoryrepository.findAll(); // Fetch all categories
        Map<Category, List<Category>> categoryTree = buildCategoryTree(categories);
        return categoryTree;
    }

    private Map<Category, List<Category>> buildCategoryTree(List<Category> categories) {
        Map<Category, List<Category>> categoryTree = new LinkedHashMap<>();
        Map<Long, Category> categoryMap = categories.stream()
            .collect(Collectors.toMap(Category::getCategoryId, c -> c));

        for (Category category : categories) {
            if (category.getParentCategory() == null) {
                categoryTree.put(category, new ArrayList<>()); // Add top-level category
            } else {
                Category parent = category.getParentCategory();
                categoryTree.computeIfAbsent(categoryMap.get(parent.getCategoryId()), k -> new ArrayList<>())
                            .add(category);
            }
        }

        return categoryTree;
    }
    
    public void deleteAndUpdateCategory(Long categoryId) {
    	try {
    	categoryrepository.UpdateDeletedCategory(categoryId);
    	categoryrepository.deleteCategory(categoryId);
    	}
    	catch(Exception ex) {
    		System.out.print(ex.getMessage());
    	}
    }
}
