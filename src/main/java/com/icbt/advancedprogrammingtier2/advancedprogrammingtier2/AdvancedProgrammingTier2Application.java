package com.icbt.advancedprogrammingtier2.advancedprogrammingtier2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
//@EnableCircuitBreaker
public class AdvancedProgrammingTier2Application {

	public static void main(String[] args) {
		SpringApplication.run(AdvancedProgrammingTier2Application.class, args);
	}

}
