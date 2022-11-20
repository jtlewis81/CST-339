package com.gcu.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcu.business.PostBusinessServiceInterface;
import com.gcu.business.UserBusinessServiceInterface;
import com.gcu.data.entity.PostEntity;
import com.gcu.data.entity.UserEntity;

@Controller
@RequestMapping("/post")
public class PostsController 
{
	@Autowired
    private UserBusinessServiceInterface userService;     
    @Autowired
    private PostBusinessServiceInterface postService;
    
    /**
     * Display 
     * 
     * @param model
     * @returns
     */
    @GetMapping("/")
    public String display(UserEntity user, Model model) 
    {
        model.addAttribute("title", "Add Post");     
        model.addAttribute("pageName", "Create Post");
        model.addAttribute("userEntity", user);
        model.addAttribute("postModel", new PostEntity()); 
        
        return "newPost";
    }
    
    @GetMapping("/editPost")
    public String editPage(@RequestParam String postId, Model model, Principal principal)
    {
    	int id = Integer.valueOf(postId);
    	PostEntity post = postService.getPostById(id);
    	UserEntity user = userService.getUserByUsername(principal.getName());
		model.addAttribute("title", "Update Post");     
		model.addAttribute("pageName", "Edit Post");
		model.addAttribute("caption", post.getCaption());
		model.addAttribute("userEntity", user);
		model.addAttribute("postEntity", ""); 
		
		return "editPost";
    }
    
    @PostMapping("/addPost")
    public String addPost(@Valid PostEntity postEntity, Principal principal, BindingResult bindingResult, Model model) 
    {        
    	LocalDateTime timestamp = LocalDateTime.now();   
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a, M-dd-yyyy");
    	UserEntity user = userService.getUserByUsername(principal.getName());
       
    	// Set PostEntity properties. 
    	postEntity.setTitle("n/a"); 
    	postEntity.setImage("n/a");
        postEntity.setTimestamp(timestamp.format(formatter)); 
        postEntity.setUserId(user.getId());
        postEntity.setUsername(user.getUsername());
        
        if (postService.addPost(postEntity))
    		System.out.println("Post was successfully added to Posts table!");
        else 
        	System.out.println("An error occurred inserting new Post into Posts table.");

        model.addAttribute("userEntity", user);
        model.addAttribute("posts", postService.getAllPostsByUser(user));
        model.addAttribute("pageName", "Home");
        model.addAttribute("title", "Home");
        
        return "redirect:/home";
    }
    
    @PostMapping("/updatePost")
    public String updatePost(@Valid PostEntity postEntity, Principal principal, BindingResult bindingResult, Model model)
    {
    	System.out.println("trying to update or delete post with id " + postEntity.getId());
    	UserEntity user = userService.getUserByUsername(principal.getName());
    	try
    	{
    		postService.updatePost(postEntity);
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    	model.addAttribute("title", "Home");
    	model.addAttribute("pageName", "Home");
    	model.addAttribute("username", user.getUsername());
    	model.addAttribute("posts", postService.getAllPostsByUser(user));
    	
    	 return "redirect:/home";
    }
    
    @PostMapping("/deletePost")
    public String deletePost(@Valid PostEntity post, Principal principal, BindingResult bindingResult, Model model) throws Exception
    {
    	System.out.println("trying to delete post with id " + post.getId());
    	UserEntity user = userService.getUserByUsername(principal.getName());
    	try
    	{
    		postService.deletePost(post.getId());
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    	model.addAttribute("title", "Home");
    	model.addAttribute("pageName", "Home");
    	model.addAttribute("username", user.getUsername());
    	model.addAttribute("posts", postService.getAllPostsByUser(user));
    	
        return "redirect:/home";
    }
}