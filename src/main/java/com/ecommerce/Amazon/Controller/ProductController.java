package com.ecommerce.Amazon.Controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.Amazon.Models.Category;
import com.ecommerce.Amazon.Models.Product;
import com.ecommerce.Amazon.Repository.CategoryRepository;
import com.ecommerce.Amazon.Repository.ProductRepository;
import com.ecommerce.Amazon.Service.ProductService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/amazon/product")
public class ProductController {

	@Autowired
	ProductRepository productrepository;
	
	@Autowired
	CategoryRepository categoryrepository;
	
	@Autowired
	ProductService productService;
	@GetMapping
    public String listProducts(Model model) {
        List<Product> products = productService.getAllProducts(); // Fetch all products
        model.addAttribute("products", products);
        return "productList"; // Name of the Thymeleaf template
    }
	
	// Show form to add a new product
    @GetMapping("/addProduct")
    public String showAddProductForm(Model model) {
        // Retrieve all categories to show in the dropdown
        List<Category> categories = categoryrepository.findAll();
        model.addAttribute("categories", categories);
        return "add-product";  // The name of the HTML form file
    }

    // Handle form submission for adding a new product
    @PostMapping("/addProduct")
    public String addProduct(@RequestParam String name,
                             @RequestParam String description,
                             @RequestParam BigDecimal price,
                             @RequestParam Integer stock,
                             @RequestParam Long categoryId,
                             @RequestParam MultipartFile image) throws IOException {

    	try {
    		// Create a new product object
            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setStock(stock);
            product.setCategoryId(categoryId);

            // Save the image and set the image URL
            String imageUrl = saveImage(image);
            product.setImageUrl(imageUrl);

            // Save the new product to the database
            productrepository.save(product);

            //return "redirect:/product/details/" + product.getProductId();  // Redirect to the product details page
            return "redirect:/register";
    	}
    	catch(Exception ex) {
    		return "redirect:/login";
    	}
        
    }

    // Helper method to save the uploaded image
    private String saveImage(MultipartFile image) throws IOException {
        // Define the directory where images will be stored
        String imagePath = image.getOriginalFilename();
        Path path = Paths.get("src/main/resources/static/images/" + imagePath);

        // Save the image to the directory
        Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        return imagePath;
    }
    
    
 // Show form for updating a product
    @GetMapping("/update/updateProductForm")
    public String showUpdateProductForm(@RequestParam Long productId, Model model) {
        // Retrieve product by ID and pass it to the form
        Product product = productrepository.findById(productId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid product ID"));
        model.addAttribute("product", product);
        List<Category> categories = categoryrepository.findAll();
        model.addAttribute("categories", categories);
        return "updateproduct";  // The name of the HTML file
    }

    // Handle form submission for updating product
    @PostMapping("/update/updateProduct")
    public String updateProduct(@RequestParam Long productId,
                                @RequestParam String name,
                                @RequestParam String description,
                                @RequestParam BigDecimal price,
                                @RequestParam Integer stock,
                                @RequestParam Long categoryId,
                                @RequestParam(required = false) MultipartFile image) throws IOException {
    	try {
        // Retrieve the existing product
        Product product = productrepository.findById(productId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid product ID"));

        // Update the product's fields
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setStock(stock);
        product.setCategoryId(categoryId);

        // If a new image is uploaded, save it
        if (image != null && !image.isEmpty()) {
            String imageUrl = saveImage(image);
            product.setImageUrl(imageUrl);
        }

        // Save the updated product
        productrepository.save(product);

        //return "redirect:/product/details/" + productId;  // Redirect to the product details page
        return "redirect:/register";
    	}
    	catch(Exception ex) {
    		return "redirect:/login";
    	}
    }
}
