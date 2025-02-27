package com.ilil.alba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AlbaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlbaApplication.class, args);
	}

}
