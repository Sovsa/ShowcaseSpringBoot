package com.sato.sosplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.sato.sosplatform.repos")
public class SoSplatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoSplatformApplication.class, args);
	}
}
