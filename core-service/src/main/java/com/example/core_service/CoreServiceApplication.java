package com.example.core_service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.core_service.repository")
@EntityScan(basePackages = "com.example.core_service.model")
public class CoreServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(CoreServiceApplication.class, args);
	}
}
