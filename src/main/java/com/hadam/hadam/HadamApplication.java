package com.hadam.hadam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HadamApplication {

	public static void main(String[] args) {
		SpringApplication.run(HadamApplication.class, args);
	}

}
