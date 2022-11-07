package com.gcu.model;

public class PostModel
{
    // VARIABLES 
    
	private int Id; 
	private String Title;
	private String Image; 
	private String Caption;    
	private String Timestamp; 
    private int UserId;     
    private int FriendsId; 
    
    public String Username; 
    
    
    // CONSTRUCTOR(S) 
    
    public PostModel(int id, String title, String image, String caption, String timestamp, int userId, int friendsId, String username)
    {
    	this.Id = id; 
    	this.Title = title; 
    	this.Image = image;
    	this.Caption = caption; 
    	this.Timestamp = timestamp; 
    	this.UserId = userId; 
    	this.FriendsId = friendsId;
    	this.Username = username;
    }
    
    public PostModel(){}
    
    
    // GETTERS
    
    public int getId() { return this.Id; }
    public String getTitle() { return this.Title; }
    public String getImage() { return this.Image; }
    public String getCaption() { return this.Caption; }
    public String getTimestamp() { return this.Timestamp; }
    public int getUserId() { return this.UserId; }
    public int getFriendsId() { return this.FriendsId; }
    
    
    // SETTERS
    
    public void setId(int id) { this.Id = id; }
    public void setTitle(String title) { this.Title = title; }
    public void setImage(String image) { this.Image = image; }
    public void setCaption(String caption) { this.Caption = caption; }
    public void setTimestamp(String timestamp) { this.Timestamp = timestamp; }   
    public void setUserId(int userId) { this.UserId = userId; }
    public void setFriendsId(int friendsId) { this.FriendsId = friendsId; }
}