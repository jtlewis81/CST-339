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
    
    @PostMapping("/addPost")
    public String addPost(@Valid PostEntity postEntity, Principal principal, BindingResult bindingResult, Model model) 
    {
    	UserEntity user = userService.getUserByUsername(principal.getName());
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

    @GetMapping("/editPost")
    public String editPage(@RequestParam String postId, PostEntity postEntity, Model model, Principal principal)
    {
    	System.out.println("trying to edit post with id " + postId);
    	
    	int id = Integer.valueOf(postId);
    	postEntity = postService.getPostById(id);
    	UserEntity user = userService.getUserByUsername(principal.getName());
		model.addAttribute("title", "Update Post");     
		model.addAttribute("pageName", "Edit Post");
		model.addAttribute("postId", postId);
		model.addAttribute("updatePostEntity", postEntity);
		model.addAttribute("deletePostEntity", postEntity);
		model.addAttribute("caption", postEntity.getCaption());
		model.addAttribute("userEntity", user);
		
		return "editPost";
    }
    
    @PostMapping("/updatePost")
    public String updatePost(@Valid PostEntity postEntity, String postId, String caption, BindingResult bindingResult, Model model, Principal principal)
    {
    	System.out.println("trying to update post with id " + postId);
    	postEntity = postService.getPostById(Integer.valueOf(postId));
    	postEntity.setCaption(caption);
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
    
    @PostMapping("/deletePost")
    public String deletePost(@Valid PostEntity postEntity, String postId, Principal principal, BindingResult bindingResult, Model model)
    {
    	System.out.println("trying to delete post with id " + postId);
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