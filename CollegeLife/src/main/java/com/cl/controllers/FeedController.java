package com.cl.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import views.FriendFeeds;

import com.cl.models.User;

@Controller
public class FeedController {
	
	
	/**
	   * Create a new user with an auto-generated id and email and name as passed 
	   * values.
	   */
	  @RequestMapping(value="/friend_feeds", headers="accept=application/json")
	  @ResponseBody
	  public FriendFeeds create(String userId) {
		  
		FriendFeeds friends = null;
		  
	    try {
	      friends = new FriendFeeds();
	    }
	    catch (Exception ex) {
	      //return "Error creating the friend's feeds: " + ex.toString();
	    }
	    return friends;
	  }
	  
}
