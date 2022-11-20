package com.gcu.business;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gcu.data.PostDataAccessService;
import com.gcu.data.entity.PostEntity;
import com.gcu.data.entity.UserEntity;

@Service
public class PostBusinessService implements PostBusinessServiceInterface
{
	@Autowired
	PostDataAccessService postService;
	
	@Override
	public List<PostEntity> getAllPostsByUser(UserEntity userEntity)
	{
		return postService.getAllPostsByUser(userEntity);
	}
	
	
	
	@Override
	public boolean addPost(PostEntity postEntity)
	{
		return postService.add(postEntity);
	}

	@Override
	public boolean updatePost(PostEntity postEntity)
	{
		return postService.update(postEntity);	
	}

	@Override
	public boolean deletePost(int postId)
	{
		return postService.delete(postId);		
	}



	@Override
	public PostEntity getPostById(int postId) {
		
		return postService.getPostById(postId);
	}

}
