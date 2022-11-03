package com.gcu.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PostModel
{
    // VARIABLES 
    
	private String Title;
	
    private String Username; 
    
    private String Content;
    
    private LocalDateTime Timestamp; 
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm a, MM-dd-yyyy");
    
    
    // CONSTRUCTOR(S) 
    
    public PostModel(String title, String userId, String content, String timestamp2)
    {
    	this.Title = title;
        this.Username = userId; 
        this.Content = content; 
        this.Timestamp = LocalDateTime.parse(timestamp2, formatter); 
    }
    


	public PostModel(){}
    
    
    // GETTERS
	public String getTitle() { return Title; }
    public String getUsername() { return this.Username; }
    public String getContent() { return this.Content; }
    public String getTimestamp() { return this.Timestamp.format(formatter); }
    
    
    // SETTERS
    public void setTitle(String title) {Title = title;}
    public void setUsername(String userId) { this.Username = userId; }
    public void setContent(String content) { this.Content = content; }
    public void setTimestamp(LocalDateTime timestamp) { this.Timestamp = timestamp; }   
}