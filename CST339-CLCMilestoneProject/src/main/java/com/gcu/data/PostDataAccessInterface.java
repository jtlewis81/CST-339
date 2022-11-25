package com.gcu.data;

import java.util.List;
import com.gcu.data.entity.PostEntity;
import com.gcu.data.entity.UserEntity;

public interface PostDataAccessInterface
{
	public List<PostEntity> getAllPostsByUser(UserEntity userEntity);
	public String getUsernameByUserId(int userId);
	public PostEntity getPostById(int postId);
	public boolean add(PostEntity postEntity);
	public boolean update(PostEntity postEntity);
	public boolean delete(int postId);
	PostEntity getLastPostsByUser(UserEntity userEntity);
}
