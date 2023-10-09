package com.Etech;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class ETechApplication{

	private static final Logger logger = LoggerFactory.getLogger(ETechApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ETechApplication.class, args);
		String welcomeMessage ="\n" +
				"\033[34m" +
				"********************************\n" +
				"*                              *\n" +
				"*   Welcome to E-Tech          *\n" +
				"*                              *\n" +
				"********************************\n" +
				"\033[0m";

		logger.info(welcomeMessage);
	}

}
