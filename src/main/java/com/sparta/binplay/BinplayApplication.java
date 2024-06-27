package com.sparta.binplay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BinplayApplication {

	public static void main(String[] args) {
		SpringApplication.run(BinplayApplication.class, args);
	}

}
