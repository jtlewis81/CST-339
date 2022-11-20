package com.gcu.business;

import java.util.List;
import com.gcu.data.entity.PostEntity;
import com.gcu.data.entity.UserEntity;

public interface PostBusinessServiceInterface
{	
	public List<PostEntity> getAllPostsByUser(UserEntity userEntity);
	public boolean addPost(PostEntity postEntity);
	public boolean updatePost(PostEntity postEntity);
	public boolean deletePost(int postId);
	public PostEntity getPostById(int postId);
}
