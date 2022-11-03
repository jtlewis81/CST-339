package com.gcu.business;

import java.util.List;

import com.gcu.model.PostModel;


public interface HomeBusinessServiceInterface {
	
	public List<PostModel> getPosts();
	
	public void init();
	
	public void destroy();

	void create(PostModel toPost);

}
