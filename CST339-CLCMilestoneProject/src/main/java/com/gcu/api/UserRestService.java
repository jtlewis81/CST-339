package com.gcu.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.gcu.business.UserBusinessService;
import com.gcu.data.entity.UserEntity;

@RestController
@RequestMapping("/api")
public class UserRestService
{
	@Autowired
	UserBusinessService service;
	
	@GetMapping(path="/user")
	public ResponseEntity<?> getUser(@RequestParam String username)
	{
		try
		{
			UserEntity user = service.getUserByUsername(username);
			
			if (user == null)
			{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			else
			{
				return new ResponseEntity<>(user, HttpStatus.OK);
			}
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
