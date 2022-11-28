package com.gcu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Topic72esApplication {

	public static void main(String[] args) {
		SpringApplication.run(Topic72esApplication.class, args);
	}

}
