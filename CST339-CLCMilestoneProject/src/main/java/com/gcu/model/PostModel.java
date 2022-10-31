package com.gcu.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PostModel
{
    // VARIABLES 
    
    private String Username; 
    
    private String Content;
    
    private LocalDateTime Timestamp; 
    
    
    // CONSTRUCTOR(S) 
    
    public PostModel(String userId, String content, LocalDateTime timestamp)
    {
        this.Username = userId; 
        this.Content = content; 
        this.Timestamp = timestamp; 
    }
    
    public PostModel(){}
    
    
    // GETTERS
    
    public String getUsername() { return this.Username; }
    public String getContent() { return this.Content; }
    public String getTimestamp() 
    {       
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a, M-dd-yyyy");
        return this.Timestamp.format(formatter); 
    }
    
    
    // SETTERS
    
    public void setUsername(String userId) { this.Username = userId; }
    public void setContent(String content) { this.Content = content; }
    public void setTimestamp(LocalDateTime timestamp) { this.Timestamp = timestamp; }   
}