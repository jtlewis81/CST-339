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

@RestController
@RequestMapping("/api")
public class PostRestService
{
	@Autowired
	UserBusinessService userService;
	@Autowired
	PostBusinessService postService;
	
	@GetMapping(path="/allUserPosts")
	public ResponseEntity<?> getAllUserPosts(@RequestParam String username)
	{
		try
		{
			UserEntity user = userService.getUserByUsername(username);
			
			if (user == null)
			{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			else
			{
				List<PostEntity> posts = postService.getAllPostsByUser(user);
				return new ResponseEntity<>(posts, HttpStatus.OK);
			}
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(path="/lastPostByUser")
	public ResponseEntity<?> getLastUserPost(@RequestParam String username)
	{
		try
		{
			UserEntity user = userService.getUserByUsername(username);
			
			if (user == null)
			{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			else
			{
				PostEntity post = postService.getLastPostsByUser(user);
				return new ResponseEntity<>(post, HttpStatus.OK);
			}
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}