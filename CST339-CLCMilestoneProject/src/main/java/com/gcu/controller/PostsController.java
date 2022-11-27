package com.gcu.controller;

import java.security.Principal;
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

/**
 * 
 * @author FriendZone developers 
 *
 */
@Controller
@RequestMapping("/post")
public class PostsController 
{
	// VARIABLES 
	@Autowired
    private UserBusinessServiceInterface userService;     
    @Autowired
    private PostBusinessServiceInterface postService;
    
    /**
     * Display create a post page 
     * 
     * @param model
     * @returns
     */
    @GetMapping("/")
    public String display(UserEntity user, Model model) 
    {
    	// return default values 
        model.addAttribute("title", "Add Post");     
        model.addAttribute("pageName", "Create Post");
        model.addAttribute("userEntity", user);
        model.addAttribute("postModel", new PostEntity()); 
        
        return "newPost";
    }
    
    /***
     * Add a post to the database. 
     * @param postEntity
     * @param principal
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping("/addPost")
    public String addPost(@Valid PostEntity postEntity, Principal principal, BindingResult bindingResult, Model model) 
    {
    	// retrieve post username
    	UserEntity user = userService.getUserByUsername(principal.getName());
        postEntity.setUserId(user.getId());
        postEntity.setUsername(user.getUsername());
        
        // attempt to add post to database
        if (postService.addPost(postEntity))
    		System.out.println("Post was successfully added to Posts table!");
        else 
        	System.out.println("An error occurred inserting new Post into Posts table.");

        // return home page
        model.addAttribute("userEntity", user);
        model.addAttribute("posts", postService.getAllPostsByUser(user));
        model.addAttribute("pageName", "Home");
        model.addAttribute("title", "Home");
        
        return "redirect:/home";
    }

    /***
     * Edit a post. 
     * @param postId
     * @param postEntity
     * @param model
     * @param principal
     * @return
     */
    @GetMapping("/editPost")
    public String editPage(@RequestParam String postId, PostEntity postEntity, Model model, Principal principal)
    {
    	System.out.println("trying to edit post with id " + postId);
    	
    	int id = Integer.valueOf(postId);
    	postEntity = postService.getPostById(id);
    	UserEntity user = userService.getUserByUsername(principal.getName());
    	
    	// return auto-filled fields for selected post
		model.addAttribute("title", "Update Post");     
		model.addAttribute("pageName", "Edit Post");
		model.addAttribute("postId", postId);
		model.addAttribute("updatePostEntity", postEntity);
		model.addAttribute("deletePostEntity", postEntity);
		model.addAttribute("caption", postEntity.getCaption());
		model.addAttribute("userEntity", user);
		
		return "editPost";
    }
    
    /***
     * Update a post in the database. 
     */
    @PostMapping("/updatePost")
    public String updatePost(@Valid PostEntity postEntity, String postId, String caption, BindingResult bindingResult, Model model, Principal principal)
    {
    	// print a console log 
    	System.out.println("trying to update post with id " + postId);
    	
    	// retrieve updating post's metadata from database 
    	postEntity = postService.getPostById(Integer.valueOf(postId));
    	
    	// update the caption
    	postEntity.setCaption(caption);
    	
    	// attempt to update the post 
    	if (postService.updatePost(postEntity))
    	{
    		System.out.println("Post was successfully updated!");
    	}
		else
		{
			System.out.println("An error occurred updating Post.");
		}
        
    	// return to home
    	model.addAttribute("title", "Home");
    	model.addAttribute("pageName", "Home");
    	model.addAttribute("username", principal.getName());
    	model.addAttribute("posts", postService.getAllPostsByUser(userService.getUserByUsername(principal.getName())));
    	
    	return "redirect:/home";
    }
    
    /***
     * Delete a post by its record Id. 
     * @param postEntity
     * @param postId
     * @param principal
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping("/deletePost")
    public String deletePost(@Valid PostEntity postEntity, String postId, Principal principal, BindingResult bindingResult, Model model)
    {
    	// print a console log 
    	System.out.println("trying to delete post with id " + postId);
    	
    	// attempt delete post
    	if (postService.deletePost(Integer.valueOf(postId)))
    	{
    		System.out.println("Post was successfully deleted!");
    	}
		else
		{
			System.out.println("An error occurred deleting Post.");
		}
    	
    	// return to home
    	model.addAttribute("title", "Home");
    	model.addAttribute("pageName", "Home");
    	model.addAttribute("username", principal.getName());
    	model.addAttribute("posts", postService.getAllPostsByUser(userService.getUserByUsername(principal.getName())));
    	
        return "redirect:/home";
    }
}