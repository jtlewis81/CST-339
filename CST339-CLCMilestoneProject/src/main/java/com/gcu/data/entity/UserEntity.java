package com.gcu.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("USERS")
public class UserEntity 
{
	// VARIABLES
	
	@Id
	private int id; 
    @Column("FirstName")
	private String firstName;
    @Column("LastName")
	private String lastName;
    @Column("Email")
	private String email;
    @Column("Phone")
	private String phone;
    @Column("Username")
	private String username;
    @Column("Password")
	private String password;    
    @Column("ProfilePicture")
	private String profilePic;
    @Column("Friends")
    private String friends;
    

    // CONSTRUCTORS    
    
    /**
     * full Constructor
     * 
     * @param id
     * @param firstName
     * @param lastName
     * @param phone
     * @param email
     * @param username
     * @param password
     * @param profilePic
     */
    public UserEntity(int id, String firstName, String lastName, String phone, String email, String username, String password, String profilePic)
    {
    	this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.username = username;
		this.password = password;
		this.profilePic = profilePic;
		this.friends = "";
	}
    
    /**
     * minimal UserEntity
     * 
     * @param username
     * @param password
     */
    public UserEntity(String username, String password)
    {
    	this.username = username;
    	this.password = password;
    }
    
    /**
     * default constructor
     */
    public UserEntity(){}
    
    
	// getters and setters for member variables of user model
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
	public String getProfilePic() { return profilePic; }
	public void setProfilePic(String profilePic) { this.profilePic = profilePic; }

	public String getFriendIDs(){ return friends;	}
	public void setFriendIDs(String friendIDs) { this.friends = friendIDs; }
}