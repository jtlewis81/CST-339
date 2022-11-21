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

@Service
public class PostDataAccessService implements PostDataAccessInterface
{
	@Autowired
	@SuppressWarnings("unused")
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplateObject;
	
	// Constructor
	public PostDataAccessService(DataSource dataSource)
	{
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	

	@Override
	public List<PostEntity> getAllPostsByUser(UserEntity userEntity)
	{
		String sql = "SELECT * FROM posts WHERE User_ID = '" + userEntity.getId() + "'"; 
		List<PostEntity> posts = new ArrayList<PostEntity>();
		try{
			SqlRowSet record = jdbcTemplateObject.queryForRowSet(sql);
			while (record.next()){
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
		catch (Exception e){
			e.printStackTrace();
		}		
		
		Collections.reverse(posts);
		return posts; 
	}
	
	@Override
	public PostEntity getPostById(int postId)
	{
		String sql = "SELECT * FROM posts WHERE ID = '" + postId + "'"; 
		PostEntity post = null;
		try
		{
			SqlRowSet record = jdbcTemplateObject.queryForRowSet(sql);
			while (record.next()){
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
		
		return post; 
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
	 *  Add (create) a new Post 
	 * @param postEntity
	 * @return
	 */
	@Override
	public boolean add(PostEntity postEntity)
	{
		LocalDateTime timestamp = LocalDateTime.now();   
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a, M-dd-yyyy");
        postEntity.setTimestamp(timestamp.format(formatter));
        
		String sql = "INSERT INTO posts (ID, Title, Image, Caption, Timestamp, User_ID, Username) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try
		{
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
		
		LocalDateTime timestamp = LocalDateTime.now();   
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a, M-dd-yyyy");
        postEntity.setTimestamp(timestamp.format(formatter)); 
		String sql = "UPDATE posts SET Caption = '" + postEntity.getCaption() +
						"', Timestamp = '" + postEntity.getTimestamp() +
						"' WHERE ID = '" + postEntity.getId() + "'";
		
		try
		{
			int rows = jdbcTemplateObject.update(sql);
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
		String sql = "DELETE FROM posts WHERE ID = '" + postId + "'";
		try
		{
			int rows = jdbcTemplateObject.update(sql);
			
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
}
