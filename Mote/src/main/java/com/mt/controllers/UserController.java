package com.mt.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import views.AggregationDto;
import views.CollegeDto;
import views.UserDto;

import com.mt.exception.MtException;
import com.mt.models.Aggregation;
import com.mt.models.College;
import com.mt.models.Locale;
import com.mt.models.User;
import com.mt.models.dao.CollegeDao;
import com.mt.models.dao.LocaleDao;
import com.mt.models.dao.UserDao;
import com.mt.models.dao.UserFriendsDao;
import com.mt.models.repository.UserFriendRepository;
import com.mt.models.repository.UserRepository;

/**
 * The <code>UserController</code> ...
 * @Controller marks the UserController.java POJO class as MVC controller
 * 
 * @author gibranecastillo
 *
 */
@Controller
public class UserController {
	// ==============
	// PRIVATE FIELDS
	// ==============
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	// Wire the UserDao that will be used inside this controller.
	@Autowired
	private UserDao _userDao;
	
	@Autowired
	private LocaleDao _localeDao;
	
	@Autowired
	private CollegeDao _collegeDao;
	
	@Autowired
	UserFriendsDao _userFriendsDao;
	
	@Autowired
	UserRepository _userRepository;
	
	@Autowired
	UserFriendRepository _userFriendRepository;
	
	@Autowired
	JavaMailSender javaMailSender;
	
	// ===============
	// PRIVATE METHODS
	// ===============
	
	/**
	 * Create a new user with an auto-generated id and email and name as passed values.
	 * 
	 * @param userDto
	 * @throws MtException
	 */
	@RequestMapping(value="/user/create", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public User create(@RequestBody UserDto userDto) throws MtException {
		User user = new User();
		
		try {
			user.setProfileFirstName(userDto.getFirstName());
			user.setProfileLastName(userDto.getLastName());
			user.setProfileEmail(userDto.getEmail());
			log.info(" Username : " + userDto.getUserName());
			user.setProfileUserName(userDto.getUserName());
			
			//Set the password 1234 on temporary , needs to replace with dynamic algorithm to produce unique random password.
			user.setProfilePassword("1234");
			user.setProfilePictureUrl(userDto.getProfilePictureUrl());
			
			/*
			 * For performance reason creating College model using CollegeDto
			 * instead of fetching from database using collegeId.
			 */
			College college  = new College();
			
			college.setCollegeId(userDto.getCollege().getCollegeId());
			college.setCollegeImgPath(userDto.getCollege().getCollegeImgPath());
			college.setCollegeName(userDto.getCollege().getCollegeName());
			
			user.setProfileCollege(college);
			
			
			/*try{
	              //Generate password and send the email to created user.
	              SimpleMailMessage mailMessage = new SimpleMailMessage();
	              mailMessage.setTo(userDto.getEmail());
	              mailMessage.setSubject("Mote Verification");
	              
	              String emailMessage = "Hi " + userDto.getFirstName() + "\n Thank You for signing up for Mote App, here is your temporary password 1234 "  ;
	              mailMessage.setText(emailMessage);
	              
	              javaMailSender.send(mailMessage);
              } catch(Exception e){
    	          e.printStackTrace();
    	          throw new ClException("Invalid Email. ", e.getMessage() + e.getCause());
              }*/
			/*
			 * Hard coded locale to en_US
			 */
			Locale locale = _localeDao.getLocale(1L);
			
			user.setLocale(locale);
			
			_userRepository.save(user);
			
			return user;
		} catch(Exception e) {
			
			log.error("Error Creating new user.", e);
			throw new MtException("Error Creating new user.", e.getMessage());
		}
	}
	
	/**
	 * Get all users profile except requesting user
	 * 
	 * 1. HTTP request http://54.149.27.205:8080/users/profile arrives at DispatcherServlet (traffic cop), which handles
	 *    routing request to appropriated controller class using HandlerMapping to determine appropriate controller class.
	 * 2. DispatcherServlet consults HandlerMapping (in this cases annotations "users/profile", GET, JSON) to determine
	 *    appropriate Controller to process the HTTP request http://54.149.27.205:8080/users/profile
	 * 3. Controller class executes and delegates business calls to objects in the model (in this case UserFriendsDao.java)
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
	 * <> value="users/profile" defines how URLs are parameterized and marks class as controller for URL pattern.
	 * <> method = RequestMethod.GET defines which HTTP verb(s) is accepted  HTTP verb describes action requested on resource GET: fetch
	 * <> produces="application/json") defines which type of data to return to AJAX clients, in this case return JSON.
	 *    It means that this controller method only processes HTTP request with JSON header set.  HTTP request header has a
	 *    field indicating type of data request requires, in this case JSON. Spring then generates appropriate data format for
	 *    HTTP response based on configured method handler.
	 * 
	 * @ResponseBody defines HTTP response will contain JSON-formatted object.  Annotation allows controller method return
	 * value forms content of response; object(s) returned from controller will be converted to JSON or XML, in this case JSON
	 * 
	 * Spring uses open-source Jackson JSON mapper, converter to convert from Java object to JSON and vice versa.  It maps
	 * Java object properties to JSON string.
	 * 
	 * Method can have any name.
	 * 
	 * @param profileId
	 * @return
	 */
	@RequestMapping(value="users/profile", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public List<UserDto> getUsersProfile(Long profileId) {

		List<User> profiles = _userFriendsDao.getUsersProfile(profileId);
		
		List<UserDto> profilesDto = new ArrayList<UserDto>();
		
		for(int i = 0; i < profiles.size(); i ++) {
			UserDto userDto = new UserDto();
			User profile = profiles.get(i);
			
			userDto.setProfileId(profile.getProfileId());
			userDto.setFirstName(profile.getProfileFirstName());
			userDto.setLastName(profile .getProfileLastName());
			userDto.setProfilePictureUrl(profile.getProfilePictureUrl());
			
			Long id = _userFriendRepository.findUserFriend(profileId, profile.getProfileId());
			
			if( id != null && id > 0 ){
				userDto.setIsFriend((byte)1);
			}
			
			CollegeDto collegeDto = new CollegeDto();
			
			userDto.setCollege(collegeDto);
			collegeDto.setCollegeName(profile.getProfileCollege().getCollegeName());
			
			profilesDto.add(userDto);
		}
		
		return profilesDto;
	}
	
	/**
	 * Get user friends
	 * 
	 * @param profileId
	 * @return
	 */
	@RequestMapping(value="user/friends", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public List<UserDto> getUserFriends(Long profileId) {
		List<User> friends = _userFriendsDao.getFriendsProfile(profileId);
		List<UserDto> profiles = new ArrayList<UserDto>();
		
		for(int i = 0; i < friends.size(); i ++) {
			UserDto userDto = new UserDto();
			User friend = friends.get(i);
			
			userDto.setProfileId(friend.getProfileId());
			userDto.setFirstName(friend.getProfileFirstName());
			userDto.setLastName(friend .getProfileLastName());
			userDto.setProfilePictureUrl(friend.getProfilePictureUrl());
			
			CollegeDto collegeDto = new CollegeDto();
			
			userDto.setCollege(collegeDto);
			collegeDto.setCollegeName(friend.getProfileCollege().getCollegeName());
			
			userDto.setIsFriend((byte)1);
			
			profiles.add(userDto);
		}
		
		return profiles;
	}
	
	/**
	 * Add new friend
	 * 
	 * @param profileId
	 * @param friendId
	 * @return
	 */
	@RequestMapping(value="user/add/friend", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public byte addFriend(Long profileId, Long friendId) {
		
		Long friend = _userFriendRepository.findUserFriend(profileId, friendId);
		
		if( friend != null &&  friend > 0 ){
			//Already friend show no need to reset and avoid adding duplicate record.
			return 1;
		}else{
			//New friend to be added 
			_userFriendsDao.addFriend(profileId, friendId);
		}
		
		return 1;
	}
	
	/**
	 * Delete user friend
	 * 
	 * @param profileId
	 * @param friendId
	 * @return
	 */
	@RequestMapping(value="user/remove/friend", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public byte removeFriend(Long profileId, Long friendId) {
		
		Long id = _userFriendRepository.findUserFriend(profileId, friendId);
		
		if( id != null && id > 0){
			_userFriendRepository.delete(id);
		}
		
		//_userFriendsDao.removeFriend(profileId, friendId);
		
		return 1;
	}
	
	/**
	 * Delete the user with the passed id.
	 * 
	 * @param id
	 */
	@RequestMapping(value="user/delete")
	@ResponseBody
	public String delete(long id) {
		try {
			User user = new User(id);
			_userDao.delete(user);
		} catch(Exception ex) {
			return "Error deleting the user: " + ex.toString();
		}
		
		return "User succesfully deleted!";
	}
	
	/**
	 * Retrieve the id for the user with the passed email address.
	 * 
	 * @param email
	 */
	@RequestMapping(value="/get-by-email")
	@ResponseBody
	public String getByEmail(String email) {
		String userId;
		
		try {
			User user = _userDao.getByEmail(email);
			userId = String.valueOf(user.getProfileId());
		} catch(Exception ex) {
			return "User not found: " + ex.toString();
		}
		
		return "The user id is: " + userId;
	}
	
	/**
	 * Update the email and the name for the user identified by the passed id.
	 * 
	 * @param id
	 * @param email
	 * @param name
	 */
	@RequestMapping(value="/update")
	@ResponseBody
	public String updateName(long id, String email, String name) {
		try {
			User user = _userDao.getById(id);
			//user.setEmail(email);
			//user.setName(name);
			_userDao.update(user);
		} catch(Exception ex) {
			return "Error updating the user: " + ex.toString();
		}
		
		return "User succesfully updated!";
	} 
	
	/**
	 * 
	 * @param email
	 * @return
	 */
	@RequestMapping(value="/getuser",headers="accept=application/json")
	@ResponseBody
	public List<User> getUser(String email) {
		return _userDao.getAll(); 
	}
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/getuserById",headers="accept=application/json")
	@ResponseBody
	public User getUserById(Long userId) {
		return _userDao.getById(userId); 
	}
}