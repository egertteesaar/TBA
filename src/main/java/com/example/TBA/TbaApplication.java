package com.example.TBA;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@SpringBootApplication
public class TbaApplication {
	private static final Logger log = LoggerFactory.getLogger(TbaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TbaApplication.class, args);
	}
}
