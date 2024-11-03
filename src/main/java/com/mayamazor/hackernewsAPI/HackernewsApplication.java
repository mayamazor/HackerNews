package com.mayamazor.hackernewsAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing

public class HackernewsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HackernewsApplication.class, args);
	}
}
