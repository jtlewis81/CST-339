package com.gcu.business;

import java.util.List;
import com.gcu.data.entity.UserEntity;

public interface UserBusinessServiceInterface
{
	public List<UserEntity> getAllUsers();
	public UserEntity getUserByUsername(String username);
	public boolean addUser(UserEntity userEntity);
	public boolean updateUser(UserEntity userEntity);
	public boolean deleteUser(UserEntity userEntity);
	public List<UserEntity> getAllFriends(String username);
	boolean addFriend(String selfUsername, String friendUsername);
}
