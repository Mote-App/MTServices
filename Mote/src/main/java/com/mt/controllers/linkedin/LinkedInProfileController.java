package com.mt.controllers.linkedin;

import java.security.Principal;

import javax.inject.Inject;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The <code>LinkedInProfileController</code> is ...
 * 
 * @Controller marks the LinkedInProfileController.java POJO class as MVC controller
 * 
 * @author gibranecastillo
 *
 */
@Controller
public class LinkedInProfileController {
	@Inject
	private ConnectionRepository connectionRepository;
	
	@RequestMapping(value="/linkedin", method=RequestMethod.GET)
	public String home(Principal currentUser, ModelMap model) {
		Connection<LinkedIn> connection = connectionRepository.findPrimaryConnection(LinkedIn.class);
		
		if(connection == null) {
			return "redirect:/connect/linkedin";
		}
		
		model.addAttribute("profile", connection.getApi().profileOperations().getUserProfileFull());
		
		return "linkedin/profile";
	}
}