package com.gcu.business;

import com.gcu.model.UserModel;

public interface UserBusinessServiceInterface
{
	public UserModel getUserByUsername(String username);
	
	public void addUser(UserModel userModel);
	
	public void updateUser(UserModel userModel);
	
	public void deleteUser(UserModel userModel);
}
