package com.gcu.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import com.gcu.data.entity.PostEntity;
import com.gcu.data.entity.UserEntity;

/***
 * This service class contains methods running queries against a database relating to user posts features. 
 * @author FriendZone developers
 *
 */
@Service
public class PostDataAccessService implements PostDataAccessInterface
{
	// VARIABLES 
	@SuppressWarnings("unused")
	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplateObject;
	
	// Constructor
	public PostDataAccessService(DataSource dataSource)
	{
		// dependency injection 
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	/***
	 * Get all posts by a user and return them as a list object. 
	 */
	@Override
	public List<PostEntity> getAllPostsByUser(UserEntity userEntity)
	{
		// create database query 
		String sql = "SELECT * FROM posts WHERE User_ID = '" + userEntity.getId() + "'"; 
		
		// container to hold post objects 
		List<PostEntity> posts = new ArrayList<PostEntity>();
		
		try{
			// run the query 
			SqlRowSet record = jdbcTemplateObject.queryForRowSet(sql);
			
			// loop through all return query elements 
			while (record.next()){
				// add each element as a post object to our list 
				posts.add(new PostEntity(
						record.getInt("ID"),
						record.getString("Title"),
						record.getString("Image"),
						record.getString("Caption"),
						record.getString("Timestamp"),
						record.getInt("User_ID"),
						record.getString("Username")));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}		
		// order posts to start with latest posts first 
		Collections.reverse(posts);
		
		return posts; 
	}
	
	/***
	 * Get a post by its record Id. Return it as a post object. 
	 */
	@Override
	public PostEntity getPostById(int postId)
	{
		// make a database query 
		String sql = "SELECT * FROM posts WHERE ID = '" + postId + "'"; 
		
		// object to hold retrieved post 
		PostEntity post = null;
		
		try
		{
			// run the query 
			SqlRowSet record = jdbcTemplateObject.queryForRowSet(sql);
			 
			while (record.next()){
				// instantiate our post object 
				post = new PostEntity(
						record.getInt("ID"),
						record.getString("Title"),
						record.getString("Image"),
						record.getString("Caption"),
						record.getString("Timestamp"),
						record.getInt("User_ID"),
						record.getString("Username"));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}		
		// return the post object 
		return post; 
	}
	
	/**
	 *  Add (create) a new Post 
	 * @param postEntity
	 * @return
	 */
	@Override
	public boolean add(PostEntity postEntity)
	{
		// Timestamp the post 
		LocalDateTime timestamp = LocalDateTime.now();   
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a, M-dd-yyyy");
        postEntity.setTimestamp(timestamp.format(formatter));
        
        // create insert query 
		String sql = "INSERT INTO posts (ID, Title, Image, Caption, Timestamp, User_ID, Username) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try
		{
			// attempt adding post object to database
			int rows = jdbcTemplateObject.update(
				sql,
				null,
				postEntity.getTitle(),
				postEntity.getImage(),
				postEntity.getCaption(),
				postEntity.getTimestamp(),
				postEntity.getUserId(),
				postEntity.getUsername()
			);
			// return status 
			return rows == 1 ? true: false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Update a post
	 * @param postEntity
	 * @return
	 */
	@Override
	public boolean update(PostEntity postEntity)
	{
		System.out.println("POST UPDATE METHOD CALLED! post id = " + postEntity.getId());
		
		// Timestamp our post 
		LocalDateTime timestamp = LocalDateTime.now();   
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a, M-dd-yyyy");
        postEntity.setTimestamp(timestamp.format(formatter)); 
        
        // create update query 
		String sql = "UPDATE posts SET Caption = '" + postEntity.getCaption() +
						"', Timestamp = '" + postEntity.getTimestamp() +
						"' WHERE ID = '" + postEntity.getId() + "'";		
		try
		{
			// run the query 
			int rows = jdbcTemplateObject.update(sql);
			
			// return a status 
			if (rows == 1)
			{
				System.out.println("POST UPDATE SUCCESS");
				return true;
			}
		}
		catch (Exception e)
		{
			System.out.println("POST UPDATE FAILURE");
			e.printStackTrace();
		}
		return false;
	}
	
	/**  
	 * Delete a post 
	 * @param postEntity
	 * @return
	 */
	@Override
	public boolean delete(int postId)
	{
		// create delete query 
		String sql = "DELETE FROM posts WHERE ID = '" + postId + "'";
		try
		{
			// run query 
			int rows = jdbcTemplateObject.update(sql);
			
			// return a status 
			if (rows == 1)
			{
				System.out.println("POST DELETE SUCCESS");
				return true;
			}
		}
		catch (Exception e)
		{
			System.out.println("POST DELETE FAILURE");
			e.printStackTrace();
		}
		return false;
	}

	/***
	 * Get last post by a user. 
	 */
	@Override
	public PostEntity getLastPostsByUser(UserEntity userEntity)
	{
		return getAllPostsByUser(userEntity).get(0);
	}
}
