package com.gcu.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.gcu.business.PostBusinessService;
import com.gcu.business.UserBusinessService;
import com.gcu.data.entity.PostEntity;
import com.gcu.data.entity.UserEntity;

/***
 * Use this class to retrieve information on user posts from FriendZone database.
 * @author FriendZone developers
 *
 */
@RestController
@RequestMapping("/api")
public class PostRestService
{
	// VARIABLES 
	@Autowired
	UserBusinessService userService;
	@Autowired
	PostBusinessService postService;
	
	/***
	 * Get all user posts by a requested username.
	 * @param username
	 * @return 
	 */
	@GetMapping(path="/allUserPosts")
	public ResponseEntity<?> getAllUserPosts(@RequestParam String username)
	{
		try
		{
			// search for username in database  
			UserEntity user = userService.getUserByUsername(username);
			
			// if that UserEntity is null 
			if (user == null)
			{
				// return HTTP 404 Not Found
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			else
			{
				// make a query to the database to get all user's posts 
				List<PostEntity> posts = postService.getAllPostsByUser(user);
				
				// return HTTP 200 SUCCESS 
				return new ResponseEntity<>(posts, HttpStatus.OK);
			}
		}
		catch (Exception e)
		{
			// Error: Return HTTP 500 Server Error 
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/***
	 * Get most recent (lastest) post for a requested username. 
	 * @param username
	 * @return
	 */
	@GetMapping(path="/lastPostByUser")
	public ResponseEntity<?> getLastUserPost(@RequestParam String username)
	{
		try
		{
			// Search for username in database 
			UserEntity user = userService.getUserByUsername(username);
			
			// if username does not exist 
			if (user == null)
			{
				// return not found
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			else
			{
				// success: get user's all posts 
				PostEntity post = postService.getLastPostsByUser(user);
				
				// return posts data 
				return new ResponseEntity<>(post, HttpStatus.OK);
			}
		}
		catch (Exception e)
		{
			// Error: return server error 
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}