package com.mt.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import views.CollegeDto;
import views.UserDto;

import com.mt.exception.MtException;
import com.mt.models.College;
import com.mt.models.Locale;
import com.mt.models.User;
import com.mt.models.dao.CollegeDao;
import com.mt.models.dao.LocaleDao;
import com.mt.models.dao.UserDao;
import com.mt.models.dao.UserFriendsDao;
import com.mt.models.repository.UserRepository;

/**
 * The <code>UserController</code> ...
 * 
 * @author gibranecastillo
 *
 */
@Controller
public class UserController {
	// ==============
	// PRIVATE FIELDS
	// ==============
	
	// Wire the UserDao that will be used inside this controller.
	@Autowired
	private UserDao _userDao;
	
	@Autowired
	private LocaleDao _localeDao;
	
	@Autowired
	private CollegeDao _collegeDao;
	
	@Autowired
	private UserFriendsDao _userFriendsDao;
	
	@Autowired
	UserRepository _userRepo;
	
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
			
			_userRepo.save(user);
			
			return user;
		} catch(Exception e) {
			e.printStackTrace();
			
			throw new MtException("Error Creating new user.", e.getMessage() + e.getCause());
			//return "Error creating the user: " + ex.toString();
			//ex.printStackTrace();
		}
	}
	
	/**
	 * Get all users profile except requesting user
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
		_userFriendsDao.addFriend(profileId, friendId);
		
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
		_userFriendsDao.removeFriend(profileId, friendId);
		
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