package com.Etech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class ETechApplication{

	public static void main(String[] args) {
		SpringApplication.run(ETechApplication.class, args);
		System.out.println("Welcome E-Tech");

	}

}
