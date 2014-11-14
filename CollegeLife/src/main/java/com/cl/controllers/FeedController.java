package com.cl.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import views.FriendFeeds;

import com.cl.models.Post;
import com.cl.models.User;
import com.cl.models.dao.PostDao;

@Controller
public class FeedController {
	
	@Autowired
	private PostDao _postDao;
	
	/**
	   * Create a new user with an auto-generated id and email and name as passed 
	   * values.
	   */
	  @RequestMapping(value="/friend_feeds", method = RequestMethod.GET, produces="application/json")
	  @ResponseBody
	  public FriendFeeds getFriendFeeds(String userId) {
		
		 /*
		  * This method is required to return Posts of the logged in User and his friends (which may range from 1 - 1000).
		  * Each user maximum 3 post should be pulled from history of his postings. The 3 posts is categorized into 
		  * Current, Most Recent, and Popular. The logical steps could be
		  * 
		  *   1. Prepare the list of logged in userId, and his friends using the UserFriends entity.
		  *   2. Get current post for each user in the list
		  *   3. Sort the list in chronological (DESC) order based on posting date.
		  *   4. Then pull the most popular post and most recent post for each user in the list.
		  *   5. Fetch the user information for each user and fill the FriendFeeds object to return the JSON.
		  *   
		  *   The complexity is in terms of performance i.e.
		  *   1. If user has 1000 friends (In FaceBook a user may have 5000+ friends) then an array of 1000 userId 
		  *   needs to be prepared and used as input for IN query. Here all user may not have posting , and 
		  *   still we need to prepare array of 1000 users.
		  *   2. It does not seem to be possible to implement using Single SQL, it is multi-step , which is time and memory 
		  *   consuming.
		  */
		FriendFeeds friends = null;
		  
	    try {
	    	
	    	
	      friends = new FriendFeeds();
	      
	      List<Post> lstPost = _postDao.getAll(userId);
	      
	    }
	    catch (Exception ex) {
	      //return "Error creating the friend's feeds: " + ex.toString();
	    }
	    return friends;
	  }
	  
}
