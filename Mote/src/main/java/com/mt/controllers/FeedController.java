package com.mt.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import views.FilterDto;
import views.FriendFeedDto;
import views.LikeDto;
import views.NationalFeedDto;
import views.PostDto;
import views.PostsDto;
import views.SchoolFeedDto;
import views.ViewDto;

import com.mt.models.Post;
import com.mt.models.PostProfileView;
import com.mt.models.PostTags;
import com.mt.models.PostProfileLike;
import com.mt.models.Tag;
import com.mt.models.User;
import com.mt.models.dao.PostDao;
import com.mt.models.dao.SchoolFeedDao;
import com.mt.models.dao.TagDao;
import com.mt.models.dao.UserDao;
import com.mt.models.dao.UserFriendsDao;
import com.mt.models.repository.PostRepository;
import com.mt.models.repository.PostUserLikeRepository;
import com.mt.models.repository.PostUserViewRepository;

/**
 * The <code>FeedController</code> ...
 * @Controller marks the FeedController.java POJO class as MVC controller
 * 
 * @author gibranecastillo
 *
 */
@Controller
public class FeedController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
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
	PostRepository _postRepo;
	
	@Autowired 
	PostUserLikeRepository _postUserLikeRepository;
	
	@Autowired 
	PostUserViewRepository _postUserViewRepository;
	
	/**
	 * Friend feeds, for generating VO.
	 * 
	 * This method is required to return Posts of the logged in User and his friends (which may range from 1 - 1000).
	 * Each user maximum 3 post should be pulled from history of his postings. The 3 posts is categorized into 
	 * 
	 * Current, Most Recent, and Popular. 
	 * The logical steps are:
	 * 1. Prepare the list of friends of logged in userId's.
	 * 2. For each user get the current, most popular and most recent post.
	 * 3. Fetch the user information for each user and fill the FriendFeeds object to return the JSON.
	 * 
	 * The complexity is in terms of performance i.e.
	 * 1. If user has 1000 friends (In FaceBook a user may have 5000+ friends) then an array of 1000 userId needs to be prepared
	 *    and used as input for IN query. Here all user may not have posting , and still we need to prepare array of 1000 users.
	 * 2. It does not seem to be possible to implement using Single SQL, it is multi-step , which is time and memory consuming.
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/friend_feeds", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public List<FriendFeedDto> getFriendFeeds(Long profileId) {
		List<FriendFeedDto> friendFeeds = null;
		
		try {
			
			friendFeeds = new ArrayList<FriendFeedDto>();

			//Build the list of friends
			List<Long> friends  = _userFriendsDao.getFriends(profileId);
			
			// To include logged user id along with their friends and fetch all their post in descending order by post date
			friends.add(profileId); 
			
			// Get the userId order by posting date DESC means latest first
			List<Long> postUserIds = _postDao.getUserPosts(friends);
			
			/*
			 * Changes for Facebook to display 10 vertical images
			 */
			for(int i = 0; i < postUserIds.size(); i++) {
				
				Long postUserId = postUserIds.get(i);
				User user = _userDao.getUser(postUserId);
				
				FriendFeedDto friendFeed = new FriendFeedDto();
				friendFeed.setUserId(postUserId);
				friendFeed.setName(user.getProfileFirstName() + " " + user.getProfileLastName());
				//friendFeed.setFacebookName(user.getFa);
				friendFeed.setProfileImg(user.getProfilePictureUrl());
				friendFeed.setSchoolId(user.getProfileCollege().getCollegeId());
				friendFeed.setSchoolImg(user.getProfileCollege().getCollegeImgPath());
				friendFeed.setSchoolName(user.getProfileCollege().getCollegeName());
				
				// Find the current and most recent post
				List<Post> posts = _postDao.getMostRecentPost(user.getProfileId());
				
				for (int j=0; j < posts.size(); j++) {
					
					PostsDto postsDto = new PostsDto();
					
					// Get the current post
					PostDto currentPost = new PostDto();
					
					populatePostDto(currentPost, posts.get(j), "F");
					postsDto.setCurrentPost(currentPost);
					
					friendFeed.setPosts(postsDto);
					friendFeeds.add(friendFeed);
				}
				
			}
			
			/* Original Code 
			 * for(int i = 0; i < postUserIds.size(); i++) {
				Long postUserId = postUserIds.get(i);
				User user = _userDao.getUser(postUserId);
				
				FriendFeedDto friendFeed = new FriendFeedDto();
				friendFeed.setUserId(postUserId);
				friendFeed.setName(user.getProfileFirstName() + " " + user.getProfileLastName());
				//friendFeed.setFacebookName(user.getFa);
				friendFeed.setProfileImg(user.getProfilePictureUrl());
				friendFeed.setSchoolId(user.getProfileCollege().getCollegeId());
				friendFeed.setSchoolImg(user.getProfileCollege().getCollegeImgPath());
				friendFeed.setSchoolName(user.getProfileCollege().getCollegeName());
				//TODO:  Remove .getId() if getCollegeId() works, add getCollegeLanguageCode() and getCollegeCountryCode()
				//friendFeed.setUserType(user.getIsAlumni());
				
				PostsDto postsDto = new PostsDto();
				
				// Find the current and most recent post
				List<Post> posts = _postDao.getMostRecentPost(user.getProfileId());
				
				// Get the current post
				PostDto currentPost = new PostDto();
				
				if(posts != null && posts.size() > 0) {
					Post post = posts.get(0);
					populatePostDto(currentPost, post, "F");
					postsDto.setCurrentPost(currentPost);
				}
				
				// The count should be 2 to get the most recent post, else current post is treated as most recent post
				PostDto mostRecentPost = new PostDto();
				
				if(posts != null && posts.size() == 2) {
					Post post = posts.get(1);
					populatePostDto(mostRecentPost, post, "F");
				} else {
					copyPostDto(mostRecentPost, currentPost);	
				}
				
				postsDto.setMostRecentPost(mostRecentPost);
				
				// Find most popular post
				PostDto mostPopularPost = new PostDto();
				
				Post post = _postDao.getMostPopularPost(user.getProfileId());
				
				if(post != null) {
					populatePostDto(mostPopularPost, post, "F");
				} else {
					copyPostDto(mostPopularPost, currentPost);
				}
				
				postsDto.setPopularPost(mostPopularPost);
				friendFeed.setPosts(postsDto);
				friendFeeds.add(friendFeed);
			}*/
			
			
		} catch(Exception ex) {
			log.error("Freind Feed Controller Error", ex);
		}
		
		return friendFeeds;
	}
	
	/**
	 * 
	 * @param destination
	 * @param source
	 */
	private void copyPostDto(PostDto destination, PostDto source) {
		destination.setPostId(source.getPostId());
		destination.setPostImg(source.getPostImg());
		destination.setCaption(source.getCaption());
		destination.setLikes(source.getLikes());
		destination.setPostingDate(source.getPostingDate());
		destination.setProgressInd(source.getProgressInd());
		destination.setTags(source.getTags());
		//destination.setCustomTags(source.getCustomTags());
	}
	
	/**
	 * 
	 * @param postDto
	 * @param source
	 * @param likedPostIds
	 */
	private void populatePostDto(PostDto postDto, Post source, String postType) {
		
		if( source.getProfile().getProfileId() == null){
			return;
		}
		
		//Decision to decide if Post is from Mote App or other Media like Facebook, Instagram etc.
		if( source.getAggregationSourceObject()== null || source.getAggregationSourceObject().getSourceObjectId()== 0 ){
			postDto.setMediaPost(false);
		}else{
			postDto.setMediaPost(true);
		}
		
		postDto.setPostId(source.getPostId());
		postDto.setPostImg(source.getPostObjectPath());
		postDto.setCaption(source.getPostCaption());
		//postDto.setLikes(source.getLikes());
		postDto.setProgressInd(35);
		
		/*
		 * Set the flag to check if user has already 
		 * clicked the Like button. So that he is not allowed to make a second attempt
		 */
		isApplicableForLiking(postDto, source, postType);
		
		/*
		 * Set the flag to check if user has already 
		 * viewed the post. So that he is not allowed to make a second attempt
		 */
		isApplicableForViewUpdate(postDto, source, postType);
		
		
		// Calculate the elapsed time in Hours, Minutes or Days from posting date to current date
		postDto.setPostingDate(calculateElapsedTime(source.getPostDate()));
		
		// Populate tags
		List<Long> lstTags = new ArrayList<Long>();
		
		for(int i = 0; i < source.getListPostTags().size(); i++) {
			PostTags postTag = source.getListPostTags().get(i);
			Tag tag = _tagDao.getTag(postTag.getTagId());
			
			lstTags.add(tag.getTagId());
			
			//destination.setTagCategory(tag.getTagType());
		}
		
		postDto.setTags(lstTags);
		
		/*
		 * Discarded
		 * // Populate custom tags
		List<String> lstCustomTags = new ArrayList<String>();
		
		for(int i = 0; i < source.getListPostCustomTags().size(); i++) {
			PostCustomTags customPostTag = source.getListPostCustomTags().get(i);
			lstCustomTags.add(customPostTag.getTagName());
		}
		
		destination.setCustomTags(lstCustomTags);*/
	}
	
	/**
	 * This logic to avoid multiple likes.
	 * @param post
	 * @param likedPostIds
	 */
	private void isApplicableForLiking(PostDto postDto, Post source, String postType) {
		
		Long likedPostId = null;
		
		if( _postUserLikeRepository == null){
			
			log.error("Unexcpected instance error : _postUserLikeRepository");
		}
		
		likedPostId = _postUserLikeRepository.findPostLikeForLevel(source.getProfile().getProfileId(), source.getPostId(), postType);			

		if(likedPostId != null && likedPostId > 0){
			postDto.setLikeDone(true);
		}else{
			postDto.setLikeDone(false);
		}
		
		//Update the count of likes for post according to post type.
		int likesCount = _postUserLikeRepository.countPostLikeForLevel(source.getPostId(), postType);		
		postDto.setLikes(likesCount);
	}
	
	
	/**
	 * This logic to avoid multiple views.
	 * @param post
	 * @param likedPostIds
	 */
	private void isApplicableForViewUpdate(PostDto postDto, Post source, String postType) {
		
		Long viewPostId  = null;
		
		viewPostId  = _postUserViewRepository.findPostViewForLevel(source.getProfile().getProfileId(), source.getPostId(), postType);			
		
		if(viewPostId  != null && viewPostId  > 0){
			postDto.setViewDone(true);
		}else{
			postDto.setViewDone(false);
		}
		
		//Update the count of likes for post according to post type.
		//int likesCount = _postUserViewRepository.countPostLikeForLevel(source.getPostId(), postType);		
		//postDto.setLikes(likesCount);
	}
	
	/**
	 * 
	 * @param dt
	 * @return
	 */
	private String calculateElapsedTime(Calendar dt) {
		//Difference between Joda and Calendar is Joda starts with 1-12 and Calendar starts with 0-11 for month value and 
		DateTime start = new DateTime(dt.get(Calendar.YEAR), dt.get(Calendar.MONTH) + 1, dt.get(Calendar.DAY_OF_MONTH), dt.get(Calendar.HOUR_OF_DAY), dt.get(Calendar.MINUTE));
		DateTime end = new DateTime();
		
		//int years = Years.yearsBetween(start, end).getYears();
		int days = Days.daysBetween(start.withTimeAtStartOfDay(), end.withTimeAtStartOfDay()).getDays();
		int hours = Hours.hoursBetween(start, end).getHours();
		int mins = Minutes.minutesBetween(start, end).getMinutes();
		
		String elspasedTime = "";
		
		if(days > 0) {
			elspasedTime = days + " Days";
		} else if(hours > 0) {
			elspasedTime = hours + " Hours";
		} else if(mins > 0) {
			elspasedTime = mins + " Mins";
		}
		
		return elspasedTime;
	}
	
	/**
	 * 
	 * @param collegeId
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/school_feeds", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public List<SchoolFeedDto> getSchoolFeeds(Long collegeId, Long profileId) {
		
		List<SchoolFeedDto> lstSchoolFeedDto = new ArrayList<SchoolFeedDto>();
		
		List<Long> friends  = _userFriendsDao.getFriends(profileId);
		// To include logged user id along with their friends and fetch all their post in descending order by post date
		friends.add(profileId); 
		
		// Get the userId order by posting date DESC means latest first
		List<Post> posts = _postDao.getUserSchoolPosts(friends, collegeId);
					
		for(int i = 0; i < posts.size(); i++) {
			
			User user = _userDao.getUser(posts.get(i).getProfile().getProfileId());
			
			SchoolFeedDto dto = new SchoolFeedDto();
			
			dto.setUserId(user.getProfileId());
			//dto.setUserType(feeds.get(i).getUser().getIsAlumni());
			dto.setName(user.getProfileUserName());
			dto.setSchoolId(user.getProfileCollege().getCollegeId());
			dto.setSchoolImg(user.getProfileCollege().getCollegeImgPath());
			dto.setSchoolName(user.getProfileCollege().getCollegeName());
			//TODO:  Remove .getId() if getCollegeId() works, add getCollegeLanguageCode() and getCollegeCountryCode()
			dto.setProfileImg(user.getProfilePictureUrl());
			
			PostDto postDto = new PostDto();
			
			populatePostDto(postDto, posts.get(i), "S");
			
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
	
	/**
	 * School Feed Filter
	 * 
	 * 1. HTTP request http://54.149.27.205:8080/school_feed_filter arrives at DispatcherServlet (traffic cop), which handles
	 *    routing request to appropriated controller class using HandlerMapping to determine appropriate controller class.
	 * 2. DispatcherServlet consults HandlerMapping (in this cases annotations "users/profile", GET, JSON) to determine
	 *    appropriate Controller to process the HTTP request http://54.149.27.205:8080/school_feed_filter
	 * 3. Controller class executes and delegates business calls to objects in the model (in this case SchoolFeedDao.java and UserDao.java)
	 * 4. Controller method returns ModelAndView object
	 *    <> ModelMap.java is an object in the Session that is made available by the Spring Framework in view.  It only needs
	 *       to appear in the controller method's parameter list if controller method prepares data for view.  You set the
	 *       data to be available for view by using the ModelMap.java method addAttribute("<key>", <valueObject>);
	 *    <> The String value return by the controller method is mapped to a view page; in other words, return String value
	 *       representing the view to use.
	 *    In conclusion, the ModelAndView constructed by Spring internally contains data for view (model) and information
	 *    on which view to display.
	 * 5. DispatcherServlet uses ViewResolver and information in ModelAndView to select appropriate view
	 * 6. DispatcherServlet dispatches request processing to selected view to then send an HTTP response to the client?
	 * 
	 * @RequestMapping allows URL template to be defined
	 * <> value="/school_feed_filter" defines how URLs are parameterized and marks class as controller for URL pattern.
	 * <> method = RequestMethod.GET defines which HTTP verb(s) is accepted  HTTP verb describes action requested on resource GET: fetch
	 * <> produces="application/json") defines which type of data to return to AJAX clients, in this case return JSON.
	 *    It means that this controller method only processes HTTP request with JSON header set.  HTTP request header has a
	 *    field indicating type of data request requires, in this case JSON. Spring then generates appropriate data format for
	 *    HTTP response based on configured method handler.
	 * 
	 * @ResponseBody defines HTTP response will contain JSON-formatted object.  Annotation allows controller method return
	 * value forms content of response; object(s) returned from controller will be converted to JSON or XML, in this case JSON
	 * 
	 * @RequestBody defines HTTP request had a JSON-formatted object that was converted to a Java object, in this case FilterDto.java
	 * 
	 * Spring uses open-source Jackson JSON mapper, converter to convert from Java object to JSON and vice versa.  It maps
	 * Java object properties to JSON string.
	 * 
	 * Method can have any name.
	 * 
	 * @param filter
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/school_feed_filter", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public List<SchoolFeedDto> schoolFeedFilter(@RequestBody FilterDto filter, Long profileId ) {
		
		List<SchoolFeedDto> lstSchoolFeedDto = new ArrayList<SchoolFeedDto>();
		
		// Filter posts specific to College and tags
		if(filter.getCollegeIds().size() > 0 && filter.getLstTags().size() > 0) {
			
			List<PostTags> feeds = _schoolFeedDao.getSchoolFeedsByCollegeAndTags(filter.getCollegeIds() , filter.getLstTags());
			
			for(int i = 0; i < feeds.size(); i++) {
			
				User user = _userDao.getUser(feeds.get(i).getPost().getProfile().getProfileId());
				
				SchoolFeedDto dto = new SchoolFeedDto();
				
				dto.setUserId(user.getProfileId());
				//dto.setUserType(feeds.get(i).getUser().getIsAlumni());
				dto.setName(user.getProfileUserName());
				dto.setSchoolId(user.getProfileCollege().getCollegeId());
				dto.setSchoolImg(user.getProfileCollege().getCollegeImgPath());
				dto.setSchoolName(user.getProfileCollege().getCollegeName());
				//TODO:  Remove .getId() if getCollegeId() works, add getCollegeLanguageCode() and getCollegeCountryCode()
				dto.setProfileImg(user.getProfilePictureUrl());
				
				PostDto postDto = new PostDto();
				
				populatePostDto(postDto, feeds.get(i).getPost(), "S");
				
				dto.setPost(postDto);
				
				lstSchoolFeedDto.add(dto);
				
				/*postDto.setPostId(feeds.get(i).getPost().getId());
				postDto.setPostImg(feeds.get(i).getPost().getPostImgPath());
				postDto.setCaption(feeds.get(i).getPost().getCaption());
				postDto.setPostingDate(calculateElapsedTime(feeds.get(i).getPost().getPostDate()));
				postDto.setLikes(feeds.get(i).getPost().getLikes());*/

			}
		} else if(filter.getCollegeIds().size() > 0) {
			// Filter posts specific to a list of colleges only
			for(int i = 0; i < filter.getCollegeIds().size(); i++){
				
				lstSchoolFeedDto.addAll(getSchoolFeeds(filter.getCollegeIds().get(i), profileId));
			}
			
		} else if(filter.getLstTags().size() > 0) {
			// Filter posts specific to tags only
			List<PostTags> feeds = _schoolFeedDao.getSchoolFeedsByTags(filter.getLstTags());
			lstSchoolFeedDto = new ArrayList<SchoolFeedDto>();
			
			for(int i = 0; i < feeds.size(); i++) {
			
				User user = _userDao.getUser(feeds.get(i).getPost().getProfile().getProfileId());
				
				SchoolFeedDto dto = new SchoolFeedDto();
				
				dto.setUserId(user.getProfileId());
				//dto.setUserType(feeds.get(i).getUser().getIsAlumni());
				dto.setName(user.getProfileUserName());
				dto.setSchoolId(user.getProfileCollege().getCollegeId());
				dto.setSchoolImg(user.getProfileCollege().getCollegeImgPath());
				dto.setSchoolName(user.getProfileCollege().getCollegeName());
				//TODO:  Remove .getId() if getCollegeId() works, add getCollegeLanguageCode() and getCollegeCountryCode()
				dto.setProfileImg(user.getProfilePictureUrl());
				
				PostDto postDto = new PostDto();
				
				populatePostDto(postDto, feeds.get(i).getPost(), "S");
				
				dto.setPost(postDto);
				
				lstSchoolFeedDto.add(dto);
				
				/*postDto.setPostId(feeds.get(i).getPost().getId());
				postDto.setPostImg(feeds.get(i).getPost().getPostImgPath());
				postDto.setCaption(feeds.get(i).getPost().getCaption());
				postDto.setPostingDate(calculateElapsedTime(feeds.get(i).getPost().getPostDate()));
				postDto.setLikes(feeds.get(i).getPost().getLikes());*/
			}
		}
		
		return lstSchoolFeedDto;
	}
	
	/**
	 * 
	 * @param collegeId
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/national_feeds", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public List<NationalFeedDto> getNationalFeeds(Long collegeId, long profileId) {
		
		List<NationalFeedDto> lstNationalFeedDto = new ArrayList<NationalFeedDto>();
		
		List<Long> friends  = _userFriendsDao.getFriends(profileId);
		// To include logged user id along with their friends and fetch all their post in descending order by post date
		friends.add(profileId); 
		
		// Get the userId order by posting date DESC means latest first
		List<Post> posts = _postDao.getUserNationalPosts(friends, collegeId);
		
		for(int i = 0; i < posts.size(); i++) {
			
			NationalFeedDto dto = new NationalFeedDto();
			
			User user = _userDao.getUser(posts.get(i).getProfile().getProfileId());
			
			dto.setUserId(user.getProfileId());
			//dto.setUserType(feeds.get(i).getUser().getIsAlumni());
			dto.setName(user.getProfileUserName());
			dto.setSchoolId(user.getProfileCollege().getCollegeId());
			dto.setSchoolImg(user.getProfileCollege().getCollegeImgPath());
			dto.setSchoolName(user.getProfileCollege().getCollegeName());
			//TODO:  Remove .getId() if getCollegeId() works, add getCollegeLanguageCode() and getCollegeCountryCode()
			dto.setProfileImg(user.getProfilePictureUrl());
			
			PostDto postDto = new PostDto();
			
			populatePostDto(postDto, posts.get(i), "N");
			
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
	
	/**
	 * 
	 * @param likeDto
	 * @return
	 */
	@RequestMapping(value="/likes", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public LikeDto updateLike(@RequestBody LikeDto likeDto) {
		
		Long likedPostId = null;
		
		log.info("Service Likes, parameters : " + likeDto.toString());
		
		likedPostId = _postUserLikeRepository.findPostLikeForLevel(likeDto.getProfileId(), likeDto.getPostId(), likeDto.getLevel());
		
		if( likedPostId != null && likedPostId > 0){
			//Like already done for the post by user id at particular feed level
			
			int likeCount = _postUserLikeRepository.countPostLikeForLevel(likeDto.getPostId(), likeDto.getLevel());
			likeDto.setLikeCount(likeCount);
			
			log.info("User already liked the post");
			
		}else{
			//User just liked the post so save and return the new total like count based on level
			
			log.info("User first time liked the post, so saving ");
			
			PostProfileLike postUser = new PostProfileLike();
			postUser.setPostId(likeDto.getPostId());
			postUser.setProfileId(likeDto.getProfileId());
			postUser.setLevel(likeDto.getLevel());
			
			_postUserLikeRepository.save(postUser);
			
			int likeCount = _postUserLikeRepository.countPostLikeForLevel(likeDto.getPostId(), likeDto.getLevel());
			likeDto.setLikeCount(likeCount);
			
		}
		
		return likeDto;
	}
	
	/**
	 * 
	 * @param viewDto
	 * @return
	 */
	@RequestMapping(value="/views", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public ViewDto updateView(@RequestBody ViewDto viewDto) {

		log.info("Service views, parameters : " + viewDto.toString());
		
		PostProfileView postUser = new PostProfileView();
		postUser.setPostId(viewDto.getPostId());
		postUser.setProfileId(viewDto.getProfileId());
		postUser.setLevel(viewDto.getLevel());
		
		_postUserViewRepository.save(postUser);
		
		return viewDto;
	}
}