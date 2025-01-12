package com.ecommerce.Amazon.Models;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
@Entity
@Table(name = "Users")
public class User implements Serializable{//, UserDetails
	@Id
	@SequenceGenerator(
		    name = "user_sequence",
		    sequenceName = "user_sequence",
		    initialValue = 1,
		    allocationSize = 1
		)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long UserID;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String loginId;

    @Column(nullable = false)
    private String accountType= "Standard";

    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;
    
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
    // Getters and setters
	public Long getId() {
		return UserID;
	}

	public void setId(Long id) {
		this.UserID = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User(String name, String loginId, String accountType, String email, String phoneNumber, String password) {
		super();
		this.name = name;
		this.loginId = loginId;
		this.accountType = accountType;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
	}

	public User() {
		super();
	}
	
	/*@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    return List.of(new SimpleGrantedAuthority(this.accountType));
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		//return loginId;
		return null;
	}*/
}
