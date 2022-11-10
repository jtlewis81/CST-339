package com.gcu.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Hashtable;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import com.gcu.business.SecurityBusinessService;
import com.gcu.model.PostModel;
import com.gcu.model.UserModel;

@Service
public class RegistrationDAOService 
{
	@Autowired
	@SuppressWarnings("unused")
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplateObject;
	
	public static List<String> usernames;
	
	@Autowired
	private SecurityBusinessService securityService;
	
	// Constructor
	public RegistrationDAOService(DataSource dataSource)
	{
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	/**
	 *  Register new user into Users database table.
	 * @param registering
	 * @return
	 */
	public Hashtable<Integer, UserModel> InsertIntoUsersTable(UserModel userModel)
	{
		boolean insertSuccess = false; 
		String sql = "INSERT INTO users (FirstName, LastName, Phone, Email, Username, Password, ProfilePicture, Privacy) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try
		{
			int rows = jdbcTemplateObject.update(
				sql,
				userModel.getFirstName(),
				userModel.getLastName(),
				userModel.getPhone(),
				userModel.getEmail(),
				userModel.getUsername(),
				userModel.getPassword(),
				null,
				false
			);
			insertSuccess = (rows == 1) ? true : false;

			if (insertSuccess)
				return new Hashtable<Integer, UserModel>(){
					{ put(1, GetUserMetadata(userModel.getUsername())); }
				};
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return new Hashtable<Integer, UserModel>(){
			{ put(0, null); }
		};
	}
	
	/**
	 *  Get all existing Username values from Users table.
	 * @return 
	 */
	public List<String> getAllUsernames()
	{
		String sql = "SELECT DISTINCT Username FROM users"; 
		usernames = new ArrayList<String>();
		try
		{
			SqlRowSet record = jdbcTemplateObject.queryForRowSet(sql);
			while (record.next())
				usernames.add(record.getString("Username"));
		}
		catch (Exception e){
			e.printStackTrace();
		}		
		return usernames; 
	}
	
	/**
	 *  Get all UserModel entities from Users table.
	 * @return
	 */
	public List<UserModel> getAllUsers(){
		String sql = "SELECT * FROM users"; 
		List<UserModel> users = new ArrayList<UserModel>();
		try{
			SqlRowSet record = jdbcTemplateObject.queryForRowSet(sql);
			while (record.next()){
				users.add(new UserModel(
						record.getInt("ID"),
						record.getString("FirstName"),
						record.getString("LastName"),
						record.getString("Phone"),
						record.getString("Email"),
						record.getString("Username"),
						record.getString("Password"),
						record.getString("ProfilePicture"),
						record.getBoolean("Privacy")));
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}		
		return users; 
	}
	
	/**
	 *  Add a new user Post to Posts database table. 
	 * @param postModel
	 * @return
	 */
	public boolean InsertIntoPostsTable(PostModel postModel)
	{
		String sql = "INSERT INTO userposts (ID, Title, Image, Caption, Timestamp, users_ID, friends_ID) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try
		{
			int rows = jdbcTemplateObject.update(
				sql,
				null,
				postModel.getTitle(),
				postModel.getImage(),
				postModel.getCaption(),
				postModel.getTimestamp(),
				postModel.getUserId(),
				postModel.getFriendsId()
			);
			return rows == 1 ? true: false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false; 
	}
	
	/**
	 *  Get all Posts for a User from Posts database table.
	 *  
	 * @param userModel
	 * @return
	 */
	public List<PostModel> GetUserPosts(UserModel userModel)
	{
		String sql = "SELECT * FROM userposts WHERE users_ID = " + userModel.getId(); 
		List<PostModel> posts = new ArrayList<PostModel>();
		try{
			SqlRowSet record = jdbcTemplateObject.queryForRowSet(sql);
			while (record.next()){
				posts.add(new PostModel(
						record.getInt("ID"),
						record.getString("Title"),
						record.getString("Image"),
						record.getString("Caption"),
						record.getString("Timestamp"),
						record.getInt("users_ID"),
						record.getInt("friends_ID"),
						securityService.getCurrentlyLoggedIn().getUsername()));
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}		
		
		Collections.reverse(posts);
		return posts; 
	}
	
	/**
	 * helper method for updating newly registered user
	 * 
	 * @param username
	 * @return
	 */
	public UserModel GetUserMetadata(String username)
	{
		String sql = "SELECT * FROM users WHERE Username = '" + username + "'"; 
		try {
			SqlRowSet searchResult = jdbcTemplateObject.queryForRowSet(sql);
			while (searchResult.next())
			{
				return new UserModel(
						searchResult.getInt("ID"),
						searchResult.getString("FirstName"),
						searchResult.getString("LastName"),
						searchResult.getString("Phone"),
						searchResult.getString("Email"),
						searchResult.getString("Username"),
						searchResult.getString("Password"),
						searchResult.getString("ProfilePicture"),
						searchResult.getBoolean("Privacy"));
			}				
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null; 
	}
	
}
