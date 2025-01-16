package com.ecommerce.Amazon.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.Amazon.Models.*;
import com.ecommerce.Amazon.Repository.CartRepository;
import java.util.stream.Collectors;
import jakarta.servlet.http.HttpSession;

@Service
public class CartService {
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	ProductService productService;
	
	//HttpSession session;
	public List<CartProductDTO> findCartDetailsByUserId(Long userId){
		try {
			List<Object[]> results = cartRepository.findCartDetailsByUserId(userId);
	        List<CartProductDTO> cartProductDTO = results.stream()
	            .map(c -> new CartProductDTO(
	                (Long) c[0],  // cartId
	                (Long) c[1],  // productId
	                (String) c[2],  // productName
	                (int) c[3],  // quantity
	                (String) c[4],  // imageUrl
	                (String) c[5]   // description
	            ))
	            .collect(Collectors.toList());
			return cartProductDTO;
		}
		catch(Exception ex) {
			System.out.print(ex.getMessage());
			return null;
		}
	}
	public int addProductToCart(Long productId, int quantity,User user) {
		try {
			Cart cart=new Cart();
			cart=cartRepository.findCartByUserIdandProductId(user.getId(),productId);
			if(cart!=null) {
				Product product=productService.findByProductId(cart.getProduct().getProductId());
				if(product.getStock()<cart.getQuantity()+quantity) {
					return 0;
				}
				cartRepository.updateByuserIdproductId(user.getId(),productId,cart.getQuantity()+quantity);
			}
			Product product=productService.findByProductId(productId);
			//User user=(User)session.getAttribute("user");
			if(product!=null && user!=null && cart==null) {
				cart=new Cart();
				cart.setProduct(product);
				cart.setUser(user);
				cart.setQuantity(quantity);
				return cartRepository.save(cart)!=null?1:0;
			}
			return 0;
		}
		catch(Exception ex) {
			System.out.print(ex.getMessage());
			return 0;
		}
	}
	
	public int updateByCartId(Long cartId,int quantity) {
		try {
			int updatedrow=0;
			Cart cart=cartRepository.findCartByCartId(cartId);
			Product product=productService.findByProductId(cart.getProduct().getProductId());
			if(product.getStock()<cart.getQuantity()+quantity) {
				return 0;
			}
			if(cart.getQuantity()>quantity) {
				int remaing=cart.getQuantity()-quantity;
				updatedrow=cartRepository.updateByCartId(cartId,remaing);
			}
			else if(cart.getQuantity()<=quantity) {
				updatedrow = cartRepository.deleteByCartId(cartId);
			}
			return updatedrow;
		}
		catch(Exception ex) {
			System.out.print(ex.getMessage());
			return 0;
		}
		
	}
	
	public String deleteCart(Long cartId) {
	    
	    try {
	    	int rowsDeleted = cartRepository.deleteByCartId(cartId);
		    if (rowsDeleted > 0) {
		        return "Cart item deleted successfully.";
		    } else {
		        return "Cart item not found.";
		    }
		}
		catch(Exception ex) {
			System.out.print(ex.getMessage());
			return "Cart item not found.";
		}
	}
}
