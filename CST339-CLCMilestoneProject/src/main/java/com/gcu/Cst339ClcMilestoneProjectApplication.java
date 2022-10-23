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
		// temp registered users
    	Users.add(new UserModel("jlewis", "password"));
    	Users.add(new UserModel("eperez", "password"));
    	Users.add(new UserModel("jgooch", "password"));
    	Users.add(new UserModel("ssuhail", "password"));
		
		SpringApplication.run(Cst339ClcMilestoneProjectApplication.class, args);
	}

}
