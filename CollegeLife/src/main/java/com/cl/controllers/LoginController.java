package com.cl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cl.models.User;
import com.cl.models.dao.UserDao;

import views.LoginDto;
import views.TokenDto;

@Controller
public class LoginController {

	@Autowired
	UserDao _userDao;
	
	@RequestMapping(value="/login", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public TokenDto login(@RequestBody LoginDto login){
		
		String userName = login.getUserName();
		User user = _userDao.getUserbyName(userName);
		
		if ( user.getPassword().equals(login.getPassword())){
			
			TokenDto dto = new TokenDto("To Be Implemented", user.getId());
			
			return dto;
		}else{
			
			return null;
		}
		
	}
	
}