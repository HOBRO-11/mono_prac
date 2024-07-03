package com.demo.mono_prac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class MonoPracApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonoPracApplication.class, args);
	}

}
