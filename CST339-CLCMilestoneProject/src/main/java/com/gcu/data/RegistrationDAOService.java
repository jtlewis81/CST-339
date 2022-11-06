package com.gcu.data;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.gcu.model.RegistrationModel;

@Service
public class RegistrationDAOService 
{
	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	public static List<String> usernames;
	
	// Constructor. 
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
	public boolean InsertIntoUsersTable(RegistrationModel registering)
	{
		String sql = "INSERT INTO users (FirstName, LastName, Phone, Email, Username, Password, ProfilePicture, Privacy) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try
		{
			int rows = jdbcTemplateObject.update(
				sql,
				registering.getFirstName(),
				registering.getLastName(),
				registering.getPhone(),
				registering.getEmail(),
				registering.getUsername(),
				registering.getPassword(),
				null,
				false
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
}
