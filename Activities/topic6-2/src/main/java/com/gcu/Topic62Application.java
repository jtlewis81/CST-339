package com.gcu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
@EnableEncryptableProperties
@ComponentScan({ "com.gcu" })
public class Topic62Application
{

	public static void main(String[] args)
	{
		SpringApplication.run(Topic62Application.class, args);		
	}

}
