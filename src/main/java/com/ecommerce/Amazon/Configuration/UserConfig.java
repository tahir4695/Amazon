package com.ecommerce.Amazon.Configuration;


import java.util.List;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ecommerce.Amazon.Models.User;
import com.ecommerce.Amazon.Repository.UserRepository;

@Configuration
public class UserConfig {
	@Bean
	CommandLineRunner commandLineRunnerUser(UserRepository repository) {
		return args->{
			User tahir=new User("tahir","tahir","Standard","tahir@gmail.com","9960523876","tahir");
			
			repository.saveAll(List.of(tahir));
		};
	}
}
