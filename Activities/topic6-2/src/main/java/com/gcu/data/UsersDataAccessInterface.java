package com.gcu.data;

public interface UsersDataAccessInterface <T>
{
	public T findByUsername(String username);
}
