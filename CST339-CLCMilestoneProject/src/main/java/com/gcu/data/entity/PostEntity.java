package com.gcu.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("POSTS")
public class PostEntity
{
    // VARIABLES 
    
	@Id
	private int id; 
	@Column("Title")
    private String title;
	@Column("Image")
    private String image; 
	@Column("Caption")
    private String caption;    
	@Column("Timestamp")
    private String timestamp; 
	@Column("User_ID")
    private int userId;
	@Column("Username")
	private String username;
    
    
    // CONSTRUCTORS 
    
    public PostEntity(int id, String title, String image, String caption, String timestamp, int userId, String username)
    {
    	this.id = id; 
    	this.title = title; 
    	this.image = image;
    	this.caption = caption; 
    	this.timestamp = timestamp; 
    	this.userId = userId;
    	this.username = username;
    }
    
    public PostEntity(){}
    
    
    // GETTERS
    
    public int getId() { return this.id; }
    public String getTitle() { return this.title; }
    public String getImage() { return this.image; }
    public String getCaption() { return this.caption; }
    public String getTimestamp() { return this.timestamp; }
    public int getUserId() { return this.userId; }
	public String getUsername() { return username; }
    
    
    // SETTERS
    
    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setImage(String image) { this.image = image; }
    public void setCaption(String caption) { this.caption = caption; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }   
    public void setUserId(int userId) { this.userId = userId; }
	public void setUsername(String username) { this.username = username; }
}