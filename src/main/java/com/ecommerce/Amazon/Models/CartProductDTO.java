package com.ecommerce.Amazon.Models;

public class CartProductDTO {
	 	private Long cartId;
	 	private Long productId;
	    private String productName;
	    private int quantity;
	    private String imageUrl;
	    private String description;
	    
	    
		public CartProductDTO(Long cartId, Long productId, String productName, int quantity, String imageUrl,
				String description) {
			super();
			this.cartId = cartId;
			this.productId = productId;
			this.productName = productName;
			this.quantity = quantity;
			this.imageUrl = imageUrl;
			this.description = description;
		}
		public Long getCartId() {
			return cartId;
		}
		public void setCartId(Long cartId) {
			this.cartId = cartId;
		}
		public Long getProductId() {
			return productId;
		}
		public void setProductId(Long productId) {
			this.productId = productId;
		}
		public String getProductName() {
			return productName;
		}
		public void setProductName(String productName) {
			this.productName = productName;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		public String getImageUrl() {
			return imageUrl;
		}
		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
}