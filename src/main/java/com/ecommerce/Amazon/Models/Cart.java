package com.ecommerce.Amazon.Models;
import jakarta.persistence.*;

@Entity
@Table(name="Cart")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CartID")
    private Long cartId;
	
	@ManyToOne
    @JoinColumn(name = "UserID",referencedColumnName ="UserID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName ="productId",nullable = false)
    private Product product;

    @Column(name = "Quantity", nullable = false)
    private int quantity;
    
    public Long getCartId() {
		return cartId;
	}

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
