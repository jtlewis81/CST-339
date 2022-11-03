package com.gcu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gcu.business.HomeBusinessService;
import com.gcu.business.HomeBusinessServiceInterface;

@Configuration
public class SpringConfig {

	
	@Bean(name="orderBusinessService", initMethod="init", destroyMethod="destroy")
	public HomeBusinessServiceInterface getOrdersBusiness() {
		
		return new HomeBusinessService();
	}
}
