package com.spring.features;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class FeaturesApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeaturesApplication.class, args);
	}

}
