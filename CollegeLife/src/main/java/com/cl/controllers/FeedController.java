package com.cl.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import views.FriendFeedDto;
import views.NationalFeedDto;
import views.PostDto;
import views.PostsDto;
import views.SchoolFeedDto;

import com.cl.models.NationalFeed;
import com.cl.models.Post;
import com.cl.models.PostCustomTags;
import com.cl.models.PostTags;
import com.cl.models.SchoolFeed;
import com.cl.models.Tag;
import com.cl.models.User;
import com.cl.models.dao.NationalFeedDao;
import com.cl.models.dao.PostDao;
import com.cl.models.dao.SchoolFeedDao;
import com.cl.models.dao.TagDao;
import com.cl.models.dao.UserDao;
import com.cl.models.dao.UserFriendsDao;

@Controller
public class FeedController {
	
	@Autowired
	private PostDao _postDao;
	
	@Autowired
	private UserFriendsDao _userFriendsDao;
	
	@Autowired
	private UserDao _userDao;
	
	@Autowired
	private TagDao _tagDao;
	
	@Autowired
	private SchoolFeedDao _schoolFeedDao;
	
	@Autowired
	private NationalFeedDao _nationaFeedDao;
	
	/** 
	 * Friend feeds, for generating VO. 
	 * 
	 */
	  @RequestMapping(value="/friend_feeds", method = RequestMethod.GET, produces="application/json")
	  @ResponseBody
	  public List<FriendFeedDto> getFriendFeeds(Long userId) {
		
		 /*
		  * This method is required to return Posts of the logged in User and his friends (which may range from 1 - 1000).
		  * Each user maximum 3 post should be pulled from history of his postings. The 3 posts is categorized into 
		  * Current, Most Recent, and Popular. The logical steps could be
		  * 
		  *   1. Prepare the list of logged in userId's friends using the UserFriends entity.
		  *   2. For each user get the current, most popular and most recent post,
		  *   3. Fetch the user information for each user and fill the FriendFeeds object to return the JSON.
		  *   
		  *   The complexity is in terms of performance i.e.
		  *   1. If user has 1000 friends (In FaceBook a user may have 5000+ friends) then an array of 1000 userId 
		  *   needs to be prepared and used as input for IN query. Here all user may not have posting , and 
		  *   still we need to prepare array of 1000 users.
		  *   2. It does not seem to be possible to implement using Single SQL, it is multi-step , which is time and memory 
		  *   consuming.
		  */
		List<FriendFeedDto> friendFeeds = null;
		  
	    try {
	    	
	    	
	    	friendFeeds = new ArrayList<FriendFeedDto>();
	    	
	    	List<Long> friends  = _userFriendsDao.getFriends(userId);
	    	
	    	/*
	    	 * To include logged user id along with their friends and fetch all their post in descending order by post date
	    	 */
	    	friends.add(userId); 
	      
	    	/*
	    	 * Get the userId order by posting date DESC mens latest first
	    	 */
	    	List<Long> postUserIds = _postDao.getUserPosts(friends);
	      
	    	for(int i = 0; i < postUserIds.size(); i++ ){
	    		
	    		Long postUserId = postUserIds.get(i);
	    		
	    		
	    		User user = _userDao.getUser(postUserId);
	    		
	    		FriendFeedDto friendFeed = new FriendFeedDto();
	    		
	    		friendFeed.setUserId(postUserId);
	    		friendFeed.setName(user.getFirstName() + " " + user.getLastName());
	    		//friendFeed.setFacebookName(user.getFa);
	    		friendFeed.setProfileImg(user.getProfilePictureUrl());
	    		friendFeed.setSchoolId(user.getCollege().getId());
	    		friendFeed.setSchoolImg(user.getCollege().getImgPath());
	    		friendFeed.setSchoolName(user.getCollege().getName());
	    		friendFeed.setUserType(user.getIsAlumni());
	    		
	    		PostsDto postsDto = new PostsDto();
	    		
	    		/*
	    		 * Find the current and most recent post 
	    		 */
	    		
	    		List<Post> posts = _postDao.getMostRecentPost(user.getId());
	    		
	    		/*
	    		 * Get the current post
	    		 */
	    		PostDto currentPost = new PostDto();
	    		if( posts != null && posts.size() > 0){
	    			
	    			Post post = posts.get(0);
	    			populatePostDto(currentPost, post);
		    		postsDto.setCurrentPost(currentPost);

	    		}
	    		
	    		/*
	    		 * The count should be 2 to get the most recent post, else current post is treated as most recent post
	    		 */
	    		PostDto mostRecentPost = new PostDto();
	    		if( posts != null && posts.size() == 2){
	    			Post post = posts.get(1);
	    			populatePostDto(mostRecentPost, post);
	    			
	    		}else{
	    			copyPostDto(mostRecentPost, currentPost);	
	    		}
	    		postsDto.setMostRecentPost(mostRecentPost);
	    		
	    		/*
	    		 * Find most popular post
	    		 */
	    		PostDto mostPopularPost = new PostDto();
	    		Post post = _postDao.getMostPopularPost(user.getId());
	    		
	    		if ( post != null ){
	    			populatePostDto(mostPopularPost, post);
	    		}else{
	    			copyPostDto(mostPopularPost, currentPost);
	    		}
	    		
	    		postsDto.setPopularPost(mostPopularPost);
	    		
	    		friendFeed.setPosts(postsDto);
	    		
	    		friendFeeds.add(friendFeed);
	    	}
	    	
	    }
	    catch (Exception ex) {
	      ex.printStackTrace();
	    }
	    return friendFeeds;
	  }
	  
	  private void copyPostDto(PostDto destination, PostDto source){
			 
		  destination.setPostId(source.getPostId());
		  destination.setPostImg(source.getPostImg());
		  destination.setCaption(source.getCaption());
		  destination.setLikes(source.getLikes());
		  destination.setPostingDate(source.getPostingDate());
		  destination.setProgressInd(source.getProgressInd());
		  destination.setTags(source.getTags());
		  destination.setCustomTags(source.getCustomTags());
	  }
	  
	  private void populatePostDto(PostDto destination, Post source){
		 
		  destination.setPostId(source.getId());
		  destination.setPostImg(source.getPostImgPath());
		  destination.setCaption(source.getCaption());
		  destination.setLikes(source.getLikes());
		  destination.setProgressInd(35);
  		
  		/*
  		 * Calculate the elapsed time in Hours, Min or Days from posting date to current date 
  		 */
		  destination.setPostingDate(calculateElapsedTime(source.getPostDate()));
		  
		  /*
		   * Populate tags
		   */
		  List<String> lstTags = new ArrayList<String>();
		  
		  for(int i=0; i < source.getLstPostTags().size(); i++){
			  
			  PostTags postTag = source.getLstPostTags().get(i);
			  Tag tag = _tagDao.getTag(postTag.getTagId());
			  
			  lstTags.add(tag.getSubTag());
			  
			  destination.setTagCategory(tag.getTagType());
		  }
		  
		  destination.setTags(lstTags);
		  
		  /*
		   * populate custom tags
		   */
		  List<String> lstCustomTags = new ArrayList<String>();
		  
		  for(int i = 0; i < source.getLstPostCustomTags().size();i++){
			  
			  PostCustomTags customPostTag = source.getLstPostCustomTags().get(i);
			  lstCustomTags.add(customPostTag.getTagName());
		  }
		  
		  destination.setCustomTags(lstCustomTags);
	  }
	  
	  private String calculateElapsedTime(Calendar dt){
		  
		  DateTime start = new DateTime(dt.get(Calendar.YEAR), dt.get(Calendar.MONTH), dt.get(Calendar.DAY_OF_MONTH) + 1, dt.get(Calendar.HOUR_OF_DAY), dt.get(Calendar.MINUTE));
		  DateTime end = new DateTime();
		  
		  //int years = Years.yearsBetween(start, end).getYears();
		  int days = Days.daysBetween(start.withTimeAtStartOfDay(), end.withTimeAtStartOfDay()).getDays();
		  int hours = Hours.hoursBetween(start, end).getHours();
		  int mins = Minutes.minutesBetween(start, end).getMinutes();
		  
		  String elspasedTime = "";
		  if( days > 0){
			  elspasedTime = days + " Days";
		  }else if(hours > 0){
			  elspasedTime = hours + " Hours";
		  }else if(mins > 0){
			  elspasedTime = mins + " Mins";
		  }
		 		  
		  return elspasedTime;
	  }
	  
	  @RequestMapping(value="/school_feeds", method = RequestMethod.GET, produces="application/json")
	  @ResponseBody
	  public List<SchoolFeedDto> getSchoolFeeds(Long collegeId) {
		  
		 List<SchoolFeedDto> lstSchoolFeedDto = new ArrayList<SchoolFeedDto>();
		 
		 List<SchoolFeed> feeds = _schoolFeedDao.getSchoolFeeds(collegeId);
		 
		 for(int i = 0; i < feeds.size(); i++){
			 SchoolFeedDto dto = new SchoolFeedDto();
			 
			 dto.setUserId(feeds.get(i).getUser().getId());
			 dto.setUserType(feeds.get(i).getUser().getIsAlumni());
			 dto.setName(feeds.get(i).getUser().getUserName());
			 dto.setSchoolId(feeds.get(i).getCollege().getId());
			 dto.setSchoolName(feeds.get(i).getCollege().getName());
			 dto.setSchoolImg(feeds.get(i).getCollege().getImgPath());
			 dto.setProfileImg(feeds.get(i).getUser().getProfilePictureUrl());
			 
			 PostDto postDto = new PostDto();
			 
			 populatePostDto(postDto, feeds.get(i).getPost());
			 
			 dto.setPost(postDto);
			 
			 lstSchoolFeedDto.add(dto);
			 
			 
			 /*postDto.setPostId(feeds.get(i).getPost().getId());
			 postDto.setPostImg(feeds.get(i).getPost().getPostImgPath());
			 postDto.setCaption(feeds.get(i).getPost().getCaption());
			 postDto.setPostingDate(calculateElapsedTime(feeds.get(i).getPost().getPostDate()));
			 postDto.setLikes(feeds.get(i).getPost().getLikes());*/
			 
			 
		 }
		 
		 return lstSchoolFeedDto;
	  }
	  
	  @RequestMapping(value="/national_feeds", method = RequestMethod.GET, produces="application/json")
	  @ResponseBody
	  public List<NationalFeedDto> getNationalFeeds(Long collegeId) {
		  
		 List<NationalFeedDto> lstNationalFeedDto = new ArrayList<NationalFeedDto>();
		 
		 List<NationalFeed> feeds = _nationaFeedDao.getNationalFeeds(collegeId);
		 
		 for(int i = 0; i < feeds.size(); i++){
			 NationalFeedDto dto = new NationalFeedDto();
			 
			 dto.setUserId(feeds.get(i).getUser().getId());
			 dto.setUserType(feeds.get(i).getUser().getIsAlumni());
			 dto.setName(feeds.get(i).getUser().getUserName());
			 dto.setSchoolId(feeds.get(i).getCollege().getId());
			 dto.setSchoolName(feeds.get(i).getCollege().getName());
			 dto.setSchoolImg(feeds.get(i).getCollege().getImgPath());
			 dto.setProfileImg(feeds.get(i).getUser().getProfilePictureUrl());
			 
			 PostDto postDto = new PostDto();
			 
			 populatePostDto(postDto, feeds.get(i).getPost());
			 
			 dto.setPost(postDto);
			 
			 lstNationalFeedDto.add(dto);
			 
			 
			 /*postDto.setPostId(feeds.get(i).getPost().getId());
			 postDto.setPostImg(feeds.get(i).getPost().getPostImgPath());
			 postDto.setCaption(feeds.get(i).getPost().getCaption());
			 postDto.setPostingDate(calculateElapsedTime(feeds.get(i).getPost().getPostDate()));
			 postDto.setLikes(feeds.get(i).getPost().getLikes());*/
			 
			 
		 }
		 
		 return lstNationalFeedDto;
	  }
	  
}
