package com.mt.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mt.exception.MtException;
import com.mt.models.User;
import com.mt.models.dao.UserDao;

import views.LoginDto;
import views.TokenDto;

/**
 * The <code>LoginController</code> ...
 * 
 * @author gibranecastillo
 *
 */
@Controller
public class LoginController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	UserDao _userDao;
	
	/**
	 * 
	 * @param login
	 * @return
	 * @throws MtException
	 */
	@RequestMapping(value="/login", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public TokenDto login(@RequestBody LoginDto login) throws MtException {
		try {
			String userName = login.getUserName();
			User user = _userDao.getUserbyName(userName);
			
			log.info("userName:  " + userName + " | login.getPassword():  " + login.getPassword() + " user.getProfilePassword(): " + user.getProfilePassword());
			
			if(user.getProfilePassword().equals(login.getPassword())) {
				TokenDto dto = new TokenDto("To Be Implemented", user.getProfileId(), user.getProfileCollege().getCollegeId());
				
				return dto;
			} else {
				throw new MtException("Invalid Username or Password", "User defined message");
			}
		} catch(Exception e) {
			throw new MtException("Invalid Username or Password", e.getMessage() + e.getCause());
		}
	}
}