package com.corso.ticketrain.application;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.corso.ticketrain.config.AppConfig;
import com.corso.ticketrain.service.UserService;

public class Test1 {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext factory = new AnnotationConfigApplicationContext(AppConfig.class);
		UserService userService = factory.getBean(UserService.class);
		
		try {
			userService.registrazione("Sergio", "1234", "Italia");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
