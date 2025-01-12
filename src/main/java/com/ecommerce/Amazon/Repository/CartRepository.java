package com.ecommerce.Amazon.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommerce.Amazon.Models.Cart;

import jakarta.transaction.Transactional;

public interface CartRepository extends JpaRepository<Cart, Long>{
	@Query("SELECT c FROM Cart c WHERE c.user.UserID= :userId")
	List<Cart> findCartByUserId(@Param("userId") Long userId);
	
	@Query("SELECT c.cartId,p.productId,p.name,c.quantity,p.imageUrl,p.description FROM Cart c JOIN c.product p WHERE c.user.UserID = :userId")
	List<Object[]> findCartDetailsByUserId(@Param("userId") Long userId);

	
	@Query("SELECT c FROM Cart c WHERE c.cartId = :cartId")
	Cart findCartByCartId(@Param("userId") Long cartId);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM Cart c WHERE c.cartId = :cartId")
	int deleteByCartId(@Param("cartId") Long cartId);
	
	@Modifying
	@Transactional
	@Query("UPDATE Cart c SET c.quantity=:quantity WHERE c.cartId = :cartId")
	int updateByCartId(@Param("cartId") Long cartId,@Param("quantity") int quantity);
	
	@Modifying
	@Transactional
	@Query("UPDATE Cart c SET c.quantity = :quantity WHERE c.user.UserID = :UserID AND c.product.productId = :productId")
	int updateByuserIdproductId(@Param("UserID") Long UserID, @Param("productId") Long productId, @Param("quantity") int quantity);
	
	@Query("SELECT c FROM Cart c WHERE c.user.UserID= :userId AND c.product.productId = :productId")
	Cart findCartByUserIdandProductId(@Param("userId") Long userId, @Param("productId") Long productId);
}
