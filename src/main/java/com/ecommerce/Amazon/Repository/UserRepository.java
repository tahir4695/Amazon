package com.ecommerce.Amazon.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommerce.Amazon.Models.User;

//@Repository //("UserRepository") /*The code will work as expected without @Repository because Spring Data JPA automatically recognizes and configures repository interfaces that extend JpaRepository.*/
public interface UserRepository extends JpaRepository<User, Long> {
	@Query("SELECT u FROM User u WHERE u.loginId=:loginId")
    User findByLoginId(@Param("loginId") String loginId);
}

