package com.stolfa.marketsurveys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MarketsurveysApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketsurveysApplication.class, args);
	}
	
}
