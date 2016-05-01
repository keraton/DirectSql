package com.github.keraton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class DirectSqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(DirectSqlApplication.class, args);

	}
}
