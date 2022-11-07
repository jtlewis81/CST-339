package com.gcu;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gcu.model.UserModel;

@SpringBootApplication
public class Cst339ClcMilestoneProjectApplication {

	public static List<UserModel> Users = new ArrayList<UserModel>();
	
	public static void main(String[] args)
	{		
		SpringApplication.run(Cst339ClcMilestoneProjectApplication.class, args);
	}

}
