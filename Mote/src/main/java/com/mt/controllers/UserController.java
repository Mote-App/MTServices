package com.mt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import views.UserDto;

import com.mt.exception.ClException;
import com.mt.models.College;
import com.mt.models.User;
import com.mt.models.dao.UserDao;
import com.mt.models.repository.UserRepository;

/**
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
  
  // ===============
  // PRIVATE METHODS
  // ===============

  @Autowired
  UserRepository _userRepo;
  
  @Autowired
  JavaMailSender javaMailSender;
  /**
   * Create a new user with an auto-generated id and email and name as passed 
   * values.
   */
  @RequestMapping(value="/user/create", method = RequestMethod.POST, produces="application/json")
  @ResponseBody
  public User create(@RequestBody UserDto userDto) throws ClException{
    
	  User user = new User();
	  
	  try {
     
      user.setProfileFirstName(userDto.getFirstName());
      user.setProfileLastName(userDto.getLastName());
      user.setProfileEmail(userDto.getEmail());
      //user.setIsAlumni(userDto.getIsAlumni());
      //user.setGender(userDto.getGender());
      user.setProfileUserName(userDto.getUserName());
      //Set the password 1234 on temporary , needs to replace with dynamic algorithm to produce unique random password.
      user.setProfilePassword("1234");
      user.setProfilePictureUrl(userDto.getProfilePictureUrl());
      
      College college = new College();
      
      college.setCollegeId(userDto.getCollege().getId());
      college.setCollegeImgPath(userDto.getCollege().getImgPath());
      college.setCollegeName(userDto.getCollege().getName());
      
      user.setProfileCollege(college);
      
      /*try{
	      //Generate password and send the email to created user.
	      SimpleMailMessage mailMessage = new SimpleMailMessage();
	      mailMessage.setTo(userDto.getEmail());
	      mailMessage.setSubject("Mote Verification");
	
	      String emailMessage = "Hi " + userDto.getFirstName() + "\n Thank You for signing up for Mote App, here is your temporary password 1234 "  ;
	      mailMessage.setText(emailMessage);
	      
	      javaMailSender.send(mailMessage);
	      
      }catch(Exception e){
    	  e.printStackTrace();
    	  throw new ClException("Invalid Email. ", e.getMessage() + e.getCause());
      }*/
      
      _userRepo.save(user);
      
      return user;
    }
    catch (Exception e) {
    	
    	e.printStackTrace();
    	
    	throw new ClException("Error Creating new user.", e.getMessage() + e.getCause());
    	//return "Error creating the user: " + ex.toString();
    	//ex.printStackTrace();
    }
    
   
  }
  
  /**
   * Delete the user with the passed id.
   */
  @RequestMapping(value="user/delete")
  @ResponseBody
  public String delete(long id) {
    try {
      User user = new User(id);
      _userDao.delete(user);
    }
    catch (Exception ex) {
      return "Error deleting the user: " + ex.toString();
    }
    return "User succesfully deleted!";
  }
  
  /**
   * Retrieve the id for the user with the passed email address.
   */
  @RequestMapping(value="/get-by-email")
  @ResponseBody
  public String getByEmail(String email) {
    String userId;
    try {
      User user = _userDao.getByEmail(email);
      userId = String.valueOf(user.getProfileId());
    }
    catch (Exception ex) {
      return "User not found: " + ex.toString();
    }
    return "The user id is: " + userId;
  }
  
  /**
   * Update the email and the name for the user indentified by the passed id.
   */
  @RequestMapping(value="/update")
  @ResponseBody
  public String updateName(long id, String email, String name) {
    try {
      User user = _userDao.getById(id);
      //user.setEmail(email);
      //user.setName(name);
      _userDao.update(user);
    }
    catch (Exception ex) {
      return "Error updating the user: " + ex.toString();
    }
    return "User succesfully updated!";
  } 

  
  @RequestMapping(value="/getuser",headers="accept=application/json")
  @ResponseBody
  public List<User> getUser(String email){
    return _userDao.getAll(); 
    
  }
  
  @RequestMapping(value="/getuserById",headers="accept=application/json")
  @ResponseBody
  public User getUserById(Long userId){
    return _userDao.getById(userId); 
    
  }
  
  
} // class UserController

