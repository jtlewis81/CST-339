package com.gcu.business;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.DataAccessInterface;
import com.gcu.data.OrdersDataService;
import com.gcu.model.PostModel;

public class HomeBusinessService implements HomeBusinessServiceInterface {

	
	@Autowired
	private DataAccessInterface<PostModel> service;
	@Autowired
	public OrdersDataService post;
	
	public List<PostModel> posts;


	@Override
	public List<PostModel> getPosts() {
		
		return post.findAll();
	}

	@Override
	public void create(PostModel toPost) {	
		post.create(toPost);
	}
	
	@Override
	@PostConstruct
	public void init() {
		// TODO Auto-generated method stub
		posts = getPosts();
		System.out.println("init HomeBusinessService!");	
	}

	@Override
	@PreDestroy
	public void destroy() {
		// TODO Auto-generated method stub

		System.out.println("destroy OrdersBusinessService!");	
	}
}
