package com.gcu.business;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gcu.data.PostDataAccessService;
import com.gcu.data.entity.PostEntity;
import com.gcu.data.entity.UserEntity;

/***
 * Use this business layer class to make calls to user posts service methods.
 * @author FriendZone developers
 *
 */
@Service
public class PostBusinessService implements PostBusinessServiceInterface
{
	// VARIABLES 
	@Autowired
	PostDataAccessService postService;
	
	/***
	 * Get data on a user's posts and return all posts as a list object.
	 */
	@Override
	public List<PostEntity> getAllPostsByUser(UserEntity userEntity)
	{
		return postService.getAllPostsByUser(userEntity);
	}
		
	/***
	 * Add a post to the database. 
	 */
	@Override
	public boolean addPost(PostEntity postEntity)
	{
		return postService.add(postEntity);
	}

	/***
	 * Send a post model to the database to update its corresponding record columns. 
	 */
	@Override
	public boolean updatePost(PostEntity postEntity)
	{
		return postService.update(postEntity);	
	}

	/***
	 * Delete a post from the database by its Id. 
	 */
	@Override
	public boolean deletePost(int postId)
	{
		return postService.delete(postId);		
	}

	/***
	 * Get a post by its record Id. 
	 */
	@Override
	public PostEntity getPostById(int postId) {
		
		return postService.getPostById(postId);
	}

	/***
	 * 
	 */
	@Override
	public PostEntity getLastPostsByUser(UserEntity userEntity)
	{
		return postService.getLastPostsByUser(userEntity);
	}
	
	@Override
	public List<PostEntity> getUserFeed(UserEntity userEntity, List<UserEntity> friends)
	{
		return postService.getUserFeed(userEntity, friends);
	}

}
