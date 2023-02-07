package com.example.todoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TodoAppApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TodoAppApplication.class, args);
	}

}
