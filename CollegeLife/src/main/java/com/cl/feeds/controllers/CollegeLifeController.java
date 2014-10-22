package com.cl.feeds.controllers;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cl.feeds.pojos.Feed;
import com.cl.feeds.pojos.FriendActivityFeed;
import com.cl.feeds.pojos.Post;

@Controller
public class CollegeLifeController {
	private static final Logger logger = LoggerFactory.getLogger(CollegeLifeController.class);
	
	/*
	 * DataSource will be wired by Spring Bean configuration with name dbDataSource
	 * TODO move this to DAO when ready.
	 */
	@Autowired
	@Qualifier("dbDataSource")
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	/**
	 *  Note the annotations on the getAjax() method
	 */
	@RequestMapping(value=CollegeLifeURIConstants.GET_AJAX, method=RequestMethod.GET, headers="accept=application/json")
	public @ResponseBody FriendActivityFeed getAjax() {
		logger.info("Start getAjax");
		
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
	
//	@RequestMapping(value=CollegeLifeURIConstants.GET_TAGS, method=RequestMethod.GET, headers="accept=application/json")
//	public @ResponseBody Tags getTags() {
//		logger.info("Start getTags");
//		
//		// TODO:  Complete
//		Tags tags = new Tags();
//		
//		return tags;
//	}
//	
//	@RequestMapping(value=CollegeLifeURIConstants.GET_ALL_SCHOOLS, method=RequestMethod.GET, headers="accept=application/json")
//	public @ResponseBody List<School> getAllSchools() {
//		logger.info("Start getAllSchools");
//		
//		// TODO:  Complete
//		List<School> schools = new ArrayList<School>();
//		Set<Integer> schoolIdKeys = schoolDao.getSchools().keySet(); // or something like it... pending!
//		
//		for(Integer i : schoolIdKeys) {
//			schools.add(schoolDao.get(i));
//		}
//		
//		return schools;
//	}
	
	@RequestMapping(value=CollegeLifeURIConstants.GET_FRIEND_FEEDS, method=RequestMethod.GET, headers="accept=application/json")
	public @ResponseBody FriendActivityFeed getFriendFeeds() {
		logger.info("Start getFriendFeeds");
		
		// Spring JDBC
		//String query = "select id, name, role from clprofile";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);
		
		logger.info("dataSource: " + this.dataSource);
		logger.info("jdbcTemplate: " + jdbcTemplate);
		
		/*
		 * TODO:  Eventually, this will call the Spring/Hibernate DAO
		 * 
		 * Note the values of the following FriendActivityFeed properties
		 * This object will be serializable in JSON format 
		 */
		FriendActivityFeed friendFeed = new FriendActivityFeed();
		friendFeed.setPostType("Friend Feed Post");
		friendFeed.setLoggedUserId(1);
		
		friendFeed.setUserId(2);
		friendFeed.setUserType("Student");
		friendFeed.setName("Marty McFly");
		friendFeed.setFacebookName("fcMarty");
		friendFeed.setProfileImg("img/profiles/2.jpg");
		friendFeed.setSchoolId(1);
		friendFeed.setSchoolName("Alabama");
		friendFeed.setSchoolImg("img/Alabama.jpg");
		
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
		
		friendFeed.setFeedList(new Feed[]{ feedOne, feedTwo, feedThree });
		
		return friendFeed;
	}
	
//	@RequestMapping(value=CollegeLifeURIConstants.GET_SCHOOL_FEEDS, method=RequestMethod.GET, headers="accept=application/json")
//	public @ResponseBody SchoolActivityFeed getSchoolFeeds() {
//		logger.info("Start getSchoolFeeds");
//		
//		/*
//		 * TODO:  Eventually, this will call the Spring/Hibernate DAO
//		 * 
//		 * Note the values of the following SchoolActivityFeed properties
//		 * This object will be serializable in JSON format 
//		 */
//		SchoolActivityFeed schoolFeed = new SchoolActivityFeed();
//		
//		
//		return schoolFeed;
//	}
//	
//	@RequestMapping(value=CollegeLifeURIConstants.GET_NATIONAL_FEEDS, method=RequestMethod.GET, headers="accept=application/json")
//	public @ResponseBody NationalActivityFeed getNationalFeeds() {
//		logger.info("Start getNationalFeeds");
//		
//		/*
//		 * TODO:  Eventually, this will call the Spring/Hibernate DAO
//		 * 
//		 * Note the values of the following NationalActivityFeed properties
//		 * This object will be serializable in JSON format 
//		 */
//		NationalActivityFeed NationalFeed = new NationalActivityFeed();
//		
//		
//		return nationalFeed;
//	}
//	
//	@RequestMapping(value=CollegeLifeURIConstants.GET_PROFILE, method=RequestMethod.GET, headers="accept=application/json")
//	public @ResponseBody Profile getProfile(@PathVariable("id") long profileId) {
//		logger.info("Start getProfile Id = " + profileId);
//		
//		// TODO:  Complete
//		Profile profile = profileDao.get(profileId);
//		
//		return profile;
//	}
//	
//	@RequestMapping(value=CollegeLifeURIConstants.CREATE_PROFILE, method=RequestMethod.POST, headers="accept=application/json")
//	public @ResponseBody Profile createProfile(@RequestBody Profile profile) {
//		logger.info("Start createProfile");
//		
//		// TODO:  Complete
//		Profile profile = profileDao.create(profile);
//		
//		return profile;
//	}
//	
//	@RequestMapping(value=CollegeLifeURIConstants.DELETE_PROFILE, method=RequestMethod.PUT, headers="accept=application/json")
//	public @ResponseBody Profile deleteProfile(@PathVariable("id") long profileId) {
//		logger.info("Start deleteProfile");
//		
//		// TODO:  Complete
//		Profile profile = profileDao.get(profileId);
//		profileDao.remove(profileId);
//		
//		return profile;
//	}
}