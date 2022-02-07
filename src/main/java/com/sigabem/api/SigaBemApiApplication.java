package com.sigabem.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SigaBemApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SigaBemApiApplication.class, args);
	}

}
