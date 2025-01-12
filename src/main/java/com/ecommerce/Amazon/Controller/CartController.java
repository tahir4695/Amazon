package com.ecommerce.Amazon.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.Amazon.Models.*;
import com.ecommerce.Amazon.Service.CartService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/amazon/cart")
public class CartController {
	
	@Autowired
	CartService cartService;
	
	@GetMapping
	public String getAllCart(HttpSession session, Model model) {
		try {
	    List<CartProductDTO> cartProductDTO = new ArrayList<>();
	    if (session.getAttribute("loginId") != null) {
	    	cartProductDTO = cartService.findCartDetailsByUserId((Long)session.getAttribute("loginId"));
	    }
	    model.addAttribute("carts", cartProductDTO);
		}
		catch(Exception ex) {
			System.out.println("Error"+ex.getMessage());		
		}
	    return "cart";
	}
	
	@PostMapping("/addToCart")
	public ResponseEntity<String> addProductToCart(@RequestParam Long productId, @RequestParam int quantity,HttpSession session) {
	    try {
	    	User user=(User)session.getAttribute("user");
	        cartService.addProductToCart(productId, quantity,user);
	        return ResponseEntity.ok("Product added to cart successfully.");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Failed to add product to cart: " + e.getMessage());
	    }
	}
}
