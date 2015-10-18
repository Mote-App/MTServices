package com.mt.controllers.instagram;

import java.security.Principal;

import javax.inject.Inject;

//import org.springframework.social.instagram.api.Instagram;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The <code>InstagramProfileController</code> is ...
 * 
 * @Controller marks the InstagramProfileController.java POJO class as MVC controller
 * 
 * @author gibranecastillo
 *
 */
@Controller
public class InstagramProfileController {
	//@Inject
	//private ConnectionRepository connectionRepository;
	
	/**
	 * 
	 * @param currentUser
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/instagram", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public String home(Principal currentUser, ModelMap model) {
		/*Connection<Instagram> connection = connectionRepository.findPrimaryConnection(Instagram.class);
		
		if(connection == null) {
			return "redirect:/connect/instagram";
		}
		
		model.addAttribute("profile", connection.getApi().profileOperations().getUserProfileFull());*/
		
		return "instagram/profile";
	}
	
	@RequestMapping(value="/instagram_friends", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public String getFriends(Principal currentUser, ModelMap model) {
		/*Connection<Instagram> connection = connectionRepository.findPrimaryConnection(Instagram.class);
		
		if(connection == null) {
			return "redirect:/connect/instagram";
		}
		
		model.addAttribute("profile", connection.getApi().profileOperations().getUserProfileFull());*/
		
		return "test test";
	}
}