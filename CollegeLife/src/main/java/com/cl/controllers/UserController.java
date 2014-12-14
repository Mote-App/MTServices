package com.cl.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import views.UserDto;

import com.cl.models.College;
import com.cl.models.User;
import com.cl.models.dao.UserDao;
import com.cl.models.repository.UserRepository;

/**
 * Class UserController
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
  
  /**
   * Create a new user with an auto-generated id and email and name as passed 
   * values.
   */
  @RequestMapping(value="/user/create", method = RequestMethod.POST, produces="application/json")
  @ResponseBody
  public String create(@RequestBody UserDto userDto) {
    try {
    	
      User user = new User();
      user.setFirstName(userDto.getFirstName());
      user.setLastName(userDto.getLastName());
      user.setIsAlumni(userDto.getIsAlumni());
      user.setGender(userDto.getGender());
      user.setUserName(userDto.getUserName());
      user.setPassword(userDto.getPassword());
      user.setProfilePictureUrl(userDto.getProfilePictureUrl());
      
      College college = new College();
      
      college.setId(userDto.getCollege().getId());
      college.setImgPath(userDto.getCollege().getImgPath());
      college.setName(userDto.getCollege().getName());
      
      user.setCollege(college);
      _userRepo.save(user);
     
    }
    catch (Exception ex) {
      return "Error creating the user: " + ex.toString();
    }
    
    return "Successfully created User";
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
      userId = String.valueOf(user.getId());
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