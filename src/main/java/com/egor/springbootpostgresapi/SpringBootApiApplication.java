package com.egor.springbootpostgresapi;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log
public class SpringBootApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootApiApplication.class, args);
	}
}
