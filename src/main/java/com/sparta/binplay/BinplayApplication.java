package com.sparta.binplay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableBatchProcessing //배치 기능 활성화 //boot 3.x부터는 필요없음
///@EnableScheduling
@EnableJpaAuditing
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.sparta.binplay.repository"})
public class BinplayApplication {

	public static void main(String[] args) {
		SpringApplication.run(BinplayApplication.class, args);
	}

}
