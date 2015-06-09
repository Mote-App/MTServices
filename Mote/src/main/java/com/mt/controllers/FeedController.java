package com.mt.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
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

import com.mt.models.NationalFeed;
import com.mt.models.Post;
import com.mt.models.PostCustomTags;
import com.mt.models.PostTags;
import com.mt.models.PostProfileLike;
import com.mt.models.SchoolFeed;
import com.mt.models.Tag;
import com.mt.models.User;
import com.mt.models.dao.NationalFeedDao;
import com.mt.models.dao.PostDao;
import com.mt.models.dao.SchoolFeedDao;
import com.mt.models.dao.TagDao;
import com.mt.models.dao.UserDao;
import com.mt.models.dao.UserFriendsDao;
import com.mt.models.repository.PostRepository;
import com.mt.models.repository.PostUserRepository;

/**
 * 
 * @author gibranecastillo
 *
 */
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
	
	@Autowired 
	PostRepository _postRepo;
	
	@Autowired 
	PostUserRepository _postUserRepository;
	
	/**
	 * Friend feeds, for generating VO.
	 * 
	 * This method is required to return Posts of the logged in User and his friends (which may range from 1 - 1000).
	 * Each user maximum 3 post should be pulled from history of his postings. The 3 posts is categorized into 
	 * 
	 * Current, Most Recent, and Popular. The logical steps could be
	 * 1. Prepare the list of logged in userId's friends using the UserFriends entity.
	 * 2. For each user get the current, most popular and most recent post,
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
			
			//A tricky process 
			List<Long> likedPostIds = _postUserRepository.findByUserIdForFriends(profileId);
			
			friendFeeds = new ArrayList<FriendFeedDto>();
			
			List<Long> friends  = _userFriendsDao.getFriends(profileId);
			
			// To include logged user id along with their friends and fetch all their post in descending order by post date
			friends.add(profileId); 
			
			// Get the userId order by posting date DESC means latest first
			List<Long> postUserIds = _postDao.getUserPosts(friends);
			
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
				//TODO:  Remove .getId() if getCollegeId() works, add getCollegeLanguageCode() and getCollegeCountryCode()
				//friendFeed.setUserType(user.getIsAlumni());
				
				PostsDto postsDto = new PostsDto();
				
				// Find the current and most recent post
				List<Post> posts = _postDao.getMostRecentPost(user.getProfileId());
				
				// Get the current post
				PostDto currentPost = new PostDto();
				
				if(posts != null && posts.size() > 0) {
					Post post = posts.get(0);
					populatePostDto(currentPost, post, likedPostIds);
					postsDto.setCurrentPost(currentPost);
				}
				
				// The count should be 2 to get the most recent post, else current post is treated as most recent post
				PostDto mostRecentPost = new PostDto();
				
				if(posts != null && posts.size() == 2) {
					Post post = posts.get(1);
					populatePostDto(mostRecentPost, post, likedPostIds);
				} else {
					copyPostDto(mostRecentPost, currentPost);	
				}
				
				postsDto.setMostRecentPost(mostRecentPost);
				
				// Find most popular post
				PostDto mostPopularPost = new PostDto();
				Post post = _postDao.getMostPopularPost(user.getProfileId());
				
				if(post != null) {
					populatePostDto(mostPopularPost, post, likedPostIds);
				} else {
					copyPostDto(mostPopularPost, currentPost);
				}
				
				postsDto.setPopularPost(mostPopularPost);
				friendFeed.setPosts(postsDto);
				friendFeeds.add(friendFeed);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
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
	 * @param destination
	 * @param source
	 * @param likedPostIds
	 */
	private void populatePostDto(PostDto destination, Post source, List<Long> likedPostIds) {
		destination.setPostId(source.getPostId());
		destination.setPostImg(source.getPostObjectPath());
		destination.setCaption(source.getPostCaption());
		destination.setLikes(source.getLikes());
		destination.setProgressInd(35);
		
		/*
		 * Set the flag to check if user has already 
		 * clicked the Like button. So that he is not allowed to make a second attempt
		 */
		isApplicableForLiking(destination, likedPostIds);
		
		// Calculate the elapsed time in Hours, Minutes or Days from posting date to current date
		destination.setPostingDate(calculateElapsedTime(source.getPostDate()));
		
		// Populate tags
		List<Long> lstTags = new ArrayList<Long>();
		
		for(int i=0; i < source.getListPostTags().size(); i++) {
			PostTags postTag = source.getListPostTags().get(i);
			Tag tag = _tagDao.getTag(postTag.getTagId());
			
			lstTags.add(tag.getTagId());
			
			//destination.setTagCategory(tag.getTagType());
		}
		
		destination.setTags(lstTags);
		
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
	 * This is required so that, on client user cannot do multiple likes.
	 * @param post
	 * @param likedPostIds
	 */
	private void isApplicableForLiking(PostDto post, List<Long> likedPostIds) {
		if(likedPostIds != null && likedPostIds.size() > 0) {
			if(likedPostIds.contains(post.getPostId())) {
				post.setLikeDone(true);
			} else {
				post.setLikeDone(false);
			}
		} else {
			post.setLikeDone(false);
		}
	}
	
	private String calculateElapsedTime(Calendar dt) {
		
		//Difference between Joda and Calendar is Joda starts with 1-12 and Calendar starts with 0-11 for month value
		//and 
		DateTime start = new DateTime(dt.get(Calendar.YEAR), dt.get(Calendar.MONTH) + 1, dt.get(Calendar.DAY_OF_MONTH), dt.get(Calendar.HOUR_OF_DAY), dt.get(Calendar.MINUTE));
		DateTime end = new DateTime();
		
		//int years = Years.yearsBetween(start, end).getYears();
		int days = Days.daysBetween(start.withTimeAtStartOfDay(), end.withTimeAtStartOfDay()).getDays();
		int hours = Hours.hoursBetween(start, end).getHours();
		int mins = Minutes.minutesBetween(start, end).getMinutes();
		
		String elspasedTime = "";
		
		if( days > 0) {
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
	public List<SchoolFeedDto> getSchoolFeeds(Long collegeId, long userId) {
		List<SchoolFeedDto> lstSchoolFeedDto = new ArrayList<SchoolFeedDto>();
		List<SchoolFeed> feeds = _schoolFeedDao.getSchoolFeeds(collegeId);
		List<Long> likedPostIds = _postUserRepository.findByUserIdForSchools(userId);
		
		for(int i = 0; i < feeds.size(); i++) {
			SchoolFeedDto dto = new SchoolFeedDto();
			
			dto.setUserId(feeds.get(i).getUser().getProfileId());
			//dto.setUserType(feeds.get(i).getUser().getIsAlumni());
			dto.setName(feeds.get(i).getUser().getProfileUserName());
			dto.setSchoolId(feeds.get(i).getCollege().getCollegeId());
			dto.setSchoolImg(feeds.get(i).getCollege().getCollegeImgPath());
			dto.setSchoolName(feeds.get(i).getCollege().getCollegeName());
			//TODO:  Remove .getId() if getCollegeId() works, add getCollegeLanguageCode() and getCollegeCountryCode()
			dto.setProfileImg(feeds.get(i).getUser().getProfilePictureUrl());
			
			PostDto postDto = new PostDto();
			
			populatePostDto(postDto, feeds.get(i).getPost(), likedPostIds);
			
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
	 * @param filter
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/school_feed_filter", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public List<SchoolFeedDto> schoolFeedFilter(@RequestBody FilterDto filter, long userId ) {
		List<SchoolFeedDto> lstSchoolFeedDto = new ArrayList<SchoolFeedDto>();
		List<Long> likedPostIds = _postUserRepository.findByUserIdForSchools(userId);
		
		// Filter posts specific to College and tags
		if(filter.getCollegeId() > 0 && filter.getLstTags().size() > 0) {
			List<SchoolFeed> feeds = _schoolFeedDao.getSchoolFeedsByCollegeAndTags(filter.getCollegeId() , filter.getLstTags());
			
			for(int i = 0; i < feeds.size(); i++) {
				SchoolFeedDto dto = new SchoolFeedDto();
				
				dto.setUserId(feeds.get(i).getUser().getProfileId());
				//dto.setUserType(feeds.get(i).getUser().getIsAlumni());
				dto.setName(feeds.get(i).getUser().getProfileUserName());
				dto.setSchoolId(feeds.get(i).getCollege().getCollegeId());
				dto.setSchoolImg(feeds.get(i).getCollege().getCollegeImgPath());
				dto.setSchoolName(feeds.get(i).getCollege().getCollegeName());
				//TODO:  Remove .getId() if getCollegeId() works, add getCollegeLanguageCode() and getCollegeCountryCode()
				dto.setProfileImg(feeds.get(i).getUser().getProfilePictureUrl());
				
				PostDto postDto = new PostDto();
				
				populatePostDto(postDto, feeds.get(i).getPost(), likedPostIds);
				
				dto.setPost(postDto);
				
				lstSchoolFeedDto.add(dto);
				
				/*postDto.setPostId(feeds.get(i).getPost().getId());
				postDto.setPostImg(feeds.get(i).getPost().getPostImgPath());
				postDto.setCaption(feeds.get(i).getPost().getCaption());
				postDto.setPostingDate(calculateElapsedTime(feeds.get(i).getPost().getPostDate()));
				postDto.setLikes(feeds.get(i).getPost().getLikes());*/
				
			}
		} else if (filter.getCollegeId() > 0) {
			// Filter posts specific to a college only
			lstSchoolFeedDto =  getSchoolFeeds(filter.getCollegeId(), userId);
		} else if (filter.getLstTags().size() > 0) {
			// Filter posts specific to tags only
			List<SchoolFeed> feeds = _schoolFeedDao.getSchoolFeedsByTags(filter.getLstTags());
			lstSchoolFeedDto = new ArrayList<SchoolFeedDto>();
			
			for(int i = 0; i < feeds.size(); i++) {
				SchoolFeedDto dto = new SchoolFeedDto();
				
				dto.setUserId(feeds.get(i).getUser().getProfileId());
				//dto.setUserType(feeds.get(i).getUser().getIsAlumni());
				dto.setName(feeds.get(i).getUser().getProfileUserName());
				dto.setSchoolId(feeds.get(i).getCollege().getCollegeId());
				dto.setSchoolImg(feeds.get(i).getCollege().getCollegeImgPath());
				dto.setSchoolName(feeds.get(i).getCollege().getCollegeName());
				//TODO:  Remove .getId() if getCollegeId() works, add getCollegeLanguageCode() and getCollegeCountryCode()
				dto.setProfileImg(feeds.get(i).getUser().getProfilePictureUrl());
				
				PostDto postDto = new PostDto();
				
				populatePostDto(postDto, feeds.get(i).getPost(), likedPostIds);
				
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
	public List<NationalFeedDto> getNationalFeeds(Long collegeId, long userId) {
		List<NationalFeedDto> lstNationalFeedDto = new ArrayList<NationalFeedDto>();
		List<Long> likedPostIds = _postUserRepository.findByUserIdForNational(userId);
		List<NationalFeed> feeds = _nationaFeedDao.getNationalFeeds(collegeId);
		
		for(int i = 0; i < feeds.size(); i++) {
			NationalFeedDto dto = new NationalFeedDto();
			
			dto.setUserId(feeds.get(i).getUser().getProfileId());
			//dto.setUserType(feeds.get(i).getUser().getIsAlumni());
			dto.setName(feeds.get(i).getUser().getProfileUserName());
			dto.setSchoolId(feeds.get(i).getCollege().getCollegeId());
			dto.setSchoolImg(feeds.get(i).getCollege().getCollegeImgPath());
			dto.setSchoolName(feeds.get(i).getCollege().getCollegeName());
			//TODO:  Remove .getId() if getCollegeId() works, add getCollegeLanguageCode() and getCollegeCountryCode()
			dto.setProfileImg(feeds.get(i).getUser().getProfilePictureUrl());
			
			PostDto postDto = new PostDto();
			
			populatePostDto(postDto, feeds.get(i).getPost(), likedPostIds);
			
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
		Post post =  _postDao.getPost(likeDto.getPostId());
		long like = post.getLikes();
		
		post.setLikes(++like);
		
		post = _postRepo.save(post);
		
		PostProfileLike postUser = new PostProfileLike();
		postUser.setPostId(post.getPostId());
		postUser.setProfileId(post.getProfileId());
		postUser.setLevel(likeDto.getLevel());
		
		_postUserRepository.save(postUser);
		
		likeDto.setLikeCount(like);
		
		return likeDto;
	}
}