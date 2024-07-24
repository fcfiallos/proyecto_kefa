package com.software.kefa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main class for the Kefa application.
 * This class is responsible for starting the Spring Boot application.
 */
@SpringBootApplication
public class KefaApplication {

	/**
	 * The main entry point for the Kefa application.
	 *
	 * @param args The command line arguments passed to the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(KefaApplication.class, args);
	}

}
