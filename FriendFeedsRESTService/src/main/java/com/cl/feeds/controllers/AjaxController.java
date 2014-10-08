package com.cl.feeds.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cl.feeds.pojos.Feed;
import com.cl.feeds.pojos.FriendActivityFeed;
import com.cl.feeds.pojos.Post;

@Controller
public class AjaxController {
	/**
	 *  Note the annotations on the getFriendActivityFeed() method
	 */
	@RequestMapping(value="/ajax", method=RequestMethod.GET, headers="accept=application/json")
	public @ResponseBody FriendActivityFeed getFriendActivityFeed() {
		/*
		 * TODO:  Eventually, this will call the Spring/Hibernate DAO
		 * 
		 * Note the values of the following FriendActivityFeed properties
		 * This object will be serializable in JSON format 
		 */
		FriendActivityFeed friend = new FriendActivityFeed();
		friend.setPostType("Friend Feed Post");
		friend.setLoggedUserId(1);
		
		friend.setUserId(2);
		friend.setUserType("Student");
		friend.setName("Marty McFly");
		friend.setFacebookName("fcMarty");
		friend.setProfileImg("img/profiles/2.jpg");
		friend.setSchoolId(1);
		friend.setSchoolName("Alabama");
		friend.setSchoolImg("img/Alabama.jpg");
		
		Post popularPost = new Post();
		popularPost.setPostId(1);
		popularPost.setPostingDate("January 19, 2014");
		popularPost.setPostImg("img/posts/2_20140119185104.jpg");
		popularPost.setTags(new String[] {"2_24", "2_21"});
		popularPost.setLikesCount(104);
		popularPost.setCaption("My first day at college");
		popularPost.setSchoolId(1);
		popularPost.setSchoolName("Alabama");
		popularPost.setSchoolImg("img/Alabama.jpg");
		
		Post currentPost = new Post();
		currentPost.setPostId(2);
		currentPost.setPostingDate("July 19, 2014");
		currentPost.setPostImg("img/posts/2_20140719185104.jpg");
		currentPost.setTags(new String[] {"1_5", "2_3"});
		currentPost.setLikesCount(3);
		currentPost.setCaption("Results of hard work...");
		currentPost.setSchoolId(1);
		currentPost.setSchoolName("Alabama");
		currentPost.setSchoolImg("img/Alabama.jpg");
		
		Post mostRecentPost = new Post();
		mostRecentPost.setPostId(3);
		mostRecentPost.setPostingDate("July 19, 2014");
		mostRecentPost.setPostImg("img/posts/2_20140719185101.jpg");
		mostRecentPost.setTags(new String[] {"1_5", "2_11"});
		mostRecentPost.setLikesCount(5);
		mostRecentPost.setCaption("The real world, its all about taking care and respect.");
		mostRecentPost.setSchoolId(1);
		mostRecentPost.setSchoolName("Alabama");
		mostRecentPost.setSchoolImg("img/Alabama.jpg");
		
		Feed feedOne = new Feed("Popular Post", popularPost);
		Feed feedTwo = new Feed("Current Post", currentPost);
		Feed feedThree = new Feed("Most Recent Post", mostRecentPost);
		
		friend.setFeedList(new Feed[]{ feedOne, feedTwo, feedThree });
		
		return friend;	
	}
}