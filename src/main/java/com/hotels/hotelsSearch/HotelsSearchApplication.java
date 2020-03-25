package com.hotels.hotelsSearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.hotels.hotelsSearch")
public class HotelsSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelsSearchApplication.class, args);
	}

}
