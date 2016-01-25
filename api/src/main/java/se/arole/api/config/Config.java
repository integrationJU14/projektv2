package se.arole.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import se.arole.api.controller.UserController;

@Configuration
public class Config {

	@Bean
	public UserController userController(){
		return new UserController();
	}
	
}
