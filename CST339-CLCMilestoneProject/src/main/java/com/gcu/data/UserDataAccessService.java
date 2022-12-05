package com.gcu.data;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.gcu.data.entity.UserEntity;

@Service
public class UserDataAccessService implements UserDataAccessInterface
{
	@Autowired
	@SuppressWarnings("unused")
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplateObject;
	
	// Constructor
	
	public UserDataAccessService(DataSource dataSource)
	{
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	
	/**
	 *  Get a UserEntity entities from Users table by username
	 *  @param username
	 *  @return
	 */
	@Override
	public UserEntity getUserByUsername(String username)
	{
		String sql = "SELECT * FROM users WHERE Username = '" + username + "'"; 
		UserEntity user = null;
		try
		{
			SqlRowSet record = jdbcTemplateObject.queryForRowSet(sql);
			if (record.next())
			{
				user = new UserEntity(record.getInt("ID"),
										record.getString("FirstName"),
										record.getString("LastName"),
										record.getString("Phone"),
										record.getString("Email"),
										record.getString("Username"),
										record.getString("Password"),
										record.getString("ProfilePicture"));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}		
		return user;
	}


	@Override
	public String getUsernameByUserId(int userId) {
		String sql = "SELECT * FROM users WHERE ID = '" + userId + "'";
		String username = "";
		try
		{
			SqlRowSet record = jdbcTemplateObject.queryForRowSet(sql);
			if (record.next())
			{
				username = record.getString("Username");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}		
		return username;
	}
	
	
	/**
	 *  Get all UserEntity entities from Users table.
	 * @return
	 */
	@Override
	public List<UserEntity> getAllUsers()
	{
		String sql = "SELECT * FROM users"; 
		List<UserEntity> users = new ArrayList<UserEntity>();
		try{
			SqlRowSet record = jdbcTemplateObject.queryForRowSet(sql);
			while (record.next()){
				users.add(new UserEntity(
						record.getInt("ID"),
						record.getString("FirstName"),
						record.getString("LastName"),
						record.getString("Phone"),
						record.getString("Email"),
						record.getString("Username"),
						record.getString("Password"),
						record.getString("ProfilePicture")));
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}		
		return users; 
	}

	
	@Override
	public boolean add(UserEntity userEntity)
	{
		boolean insertSuccess = false; 
		String sql = "INSERT INTO users (FirstName, LastName, Phone, Email, Username, Password, ProfilePicture) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try
		{
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			String encodedPassword = encoder.encode(userEntity.getPassword());
			int rows = jdbcTemplateObject.update(
				sql,
				userEntity.getFirstName(),
				userEntity.getLastName(),
				userEntity.getPhone(),
				userEntity.getEmail(),
				userEntity.getUsername(),
				encodedPassword,
				null
			);
			insertSuccess = (rows == 1) ? true : false;

			if (insertSuccess)
			{
				return true;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	

	@Override
	public boolean update(UserEntity userEntity)
	{
		// TODO To be added when a user has the option to edit their account information in their profile settings.
		return false;
	}

	@Override
	public boolean delete(UserEntity userEntity)
	{
		// TODO To be added when a user has the option to delete their account in their profile settings.
		// 		Must also delete all of their posts!
		
		return false;
	}


	@Override
	public List<UserEntity> getAllFriends(String selfUsername)
	{
		String sql = "SELECT * FROM users WHERE Username = '" + selfUsername + "'";
		List<UserEntity> friendList = new ArrayList<UserEntity>();
		try
		{
			String friends = "";
			SqlRowSet record = jdbcTemplateObject.queryForRowSet(sql);
			
			while (record.next())
			{
				friends = record.getString("Friends");
			}
			if (friends != null)
			{
				String[] parsedList = friends.split(",");
				for(int i = 0; i < parsedList.length; i++)
				{
					friendList.add(getUserByUsername(parsedList[i]));
				}
			}
			else
			{
				return null;
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return friendList;
	}


	@Override
	public boolean addFriend(String selfUsername, String friendUsername)
	{
		String sql = "";
		String sql2 = "";
		if(getAllFriends(selfUsername) == null)
		{
			sql = "UPDATE users SET friends = '" + friendUsername + ",' WHERE Username = '" + selfUsername + "'";
		}
		else
		{
			sql = "UPDATE users SET friends = CONCAT(friends, '" + friendUsername + ",') WHERE Username = '" + selfUsername + "'";
		}
		
		if(getAllFriends(friendUsername) == null)
		{
			sql2 = "UPDATE users SET friends = '" + selfUsername + ",' WHERE Username = '" + friendUsername + "'";
		}
		else
		{
			sql2 = "UPDATE users SET friends = CONCAT(friends, '" + selfUsername + ",') WHERE Username = '" + friendUsername + "'";
		}
		
		
		try
		{
			int update = jdbcTemplateObject.update(sql);
			int update2 = jdbcTemplateObject.update(sql2);
			if(update == 1 && update2 == 1)
			{
				return true;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public boolean deleteFriend(String selfUsername, String friendUsername)
	{
		String sql = "SELECT * FROM users WHERE Username = '" + selfUsername + "'";
		String sql2 = "SELECT * FROM users WHERE Username = '" + friendUsername + "'";
		String updatedFriends = "";
		String updatedFriends2 = "";
		
		try
		{
			// remove friend from self
			SqlRowSet record = jdbcTemplateObject.queryForRowSet(sql);
			String friends = "";
			while (record.next())
			{
				friends = record.getString("Friends");
			}
			String[] parsedList = friends.split(",");
			for(int i = 0; i < parsedList.length; i++)
			{
				if (!(parsedList[i].compareTo(friendUsername) == 0))
				{
					updatedFriends += parsedList[i] + ",";
				}
			}
			String sql3 = "UPDATE users SET friends = '" + updatedFriends + "' WHERE Username = '" + selfUsername + "'";
			int update = jdbcTemplateObject.update(sql3);

			// remove self from removed friend
			SqlRowSet record2 = jdbcTemplateObject.queryForRowSet(sql2);
			String friends2 = "";
			while (record2.next())
			{
				friends2 = record2.getString("Friends");
			}
			parsedList = friends2.split(",");
			for(int i = 0; i < parsedList.length; i++)
			{
				if (!(parsedList[i].compareTo(selfUsername) == 0))
				{
					updatedFriends2 += parsedList[i] + ",";
				}
			}
			String sql4 = "UPDATE users SET friends = '" + updatedFriends2 + "' WHERE Username = '" + friendUsername + "'";
			int update2 = jdbcTemplateObject.update(sql4);
			
			// do checks and return
			if(update == 1 && update2 == 1)
			{
				return true;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
}
