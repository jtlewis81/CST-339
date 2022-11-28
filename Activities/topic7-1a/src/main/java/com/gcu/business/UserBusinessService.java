package com.gcu.business;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gcu.data.entity.UserEntity;
import com.gcu.data.repository.UsersRepository;
import com.gcu.model.UserModel;

@Service
public class UserBusinessService
{
	@Autowired
	private UsersRepository usersRepository;
	
	public UserBusinessService(UsersRepository usersRepository)
	{
		this.usersRepository = usersRepository;
	}
	
	// return a list of userModel converted from userEntity objects
	public List<UserModel> getAllUsers()
	{
		List<UserEntity> usersEntity = usersRepository.findAll();
		List<UserModel> usersDomain = new ArrayList<UserModel>();
		for(UserEntity user : usersEntity)
		{
			usersDomain.add(new UserModel(user.getId(), user.getUsername(), user.getPassword()));
		}
		return usersDomain;
	}

}
