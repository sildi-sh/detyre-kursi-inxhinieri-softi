package com.example.Inxhinieri.Softi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.example.Inxhinieri.Softi"})
@EnableJpaRepositories(basePackages = {"com.example.Inxhinieri.Softi"})
public class InxhinieriSoftiApplication {
	public static void main(String[] args) {
		SpringApplication.run(InxhinieriSoftiApplication.class, args);
	}
}