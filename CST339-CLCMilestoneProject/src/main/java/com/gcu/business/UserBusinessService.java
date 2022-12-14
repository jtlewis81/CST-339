package com.gcu.business;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.gcu.data.UserDataAccessService;
import com.gcu.data.entity.UserEntity;

/***
 * Use this class to access Users table methods. 
 * @author FriendZone developers
 *
 */
@Service
public class UserBusinessService implements UserBusinessServiceInterface, UserDetailsService
{
	// VARIABLES 
	@Autowired
	private UserDataAccessService userService;
	
	/***
	 * Retrieve an object that contains info on a user from users table.  
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// load a user object 
		UserEntity user = userService.getUserByUsername(username);
		
		// if username exists 
		if(user != null)
		{
			// instantiate a list of granted authority values
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			
			// add values to authorities list 
			authorities.add(new SimpleGrantedAuthority("USER"));
			
			// return a new user object 
			return new User(user.getUsername(), user.getPassword(), authorities);
		}
		else
		{
			throw new UsernameNotFoundException("username not found");
		}
	}

	/***
	 * Get all user records from database. 
	 */
	@Override
	public List<UserEntity> getAllUsers()
	{
		return userService.getAllUsers();
	}

	/***
	 * Get a user object by a requested username. 
	 */
	@Override
	public UserEntity getUserByUsername(String username)
	{
		return userService.getUserByUsername(username);
	}

	/***
	 * Add a user to the database. 
	 */
	@Override
	public boolean addUser(UserEntity userEntity)
	{
		return userService.add(userEntity);
	}

	/***
	 * Update a user record in the databse. 
	 */
	@Override
	public boolean updateUser(UserEntity userEntity)
	{
		return userService.update(userEntity);
	}

	/***
	 * Delete a user record from the database. 
	 */
	@Override
	public boolean deleteUser(UserEntity userEntity)
	{
		return userService.delete(userEntity);
	}

	@Override
	public List<UserEntity> getAllFriends(String username)
	{
		return userService.getAllFriends(username);
	}

	@Override
	public boolean addFriend(String selfUsername, String friendUsername)
	{
		return userService.addFriend(selfUsername, friendUsername);
	}

	@Override
	public boolean deleteFriend(String selfUsername, String friendUsername)
	{
		return userService.deleteFriend(selfUsername, friendUsername);		
	}

}
