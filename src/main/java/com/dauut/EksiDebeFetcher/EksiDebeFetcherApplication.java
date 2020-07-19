package com.dauut.EksiDebeFetcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EksiDebeFetcherApplication {

	public static void main(String[] args) {
		SpringApplication.run(EksiDebeFetcherApplication.class, args);
	}

}
