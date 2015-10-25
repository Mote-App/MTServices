package com.mt.controllers.instagram;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.social.instagram.api.Instagram;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.mt.exception.MtException;
import com.mt.models.Aggregation;
import com.mt.models.College;
import com.mt.models.Locale;
import com.mt.models.User;
import com.mt.models.repository.AggregationRepository;
import com.mt.models.repository.PostRepository;
import com.mt.vo.instagram.InstagramResponse;

import views.AggregationDto;


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
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private static final String INSTAGRAM = "Instagram";
	
	@Autowired 
	AggregationRepository _aggregationRepo;

	
	/**
	 * 
	 * @param userId
	 * @param igId
	 * @param igToken
	 * @return
	 * @throws MtException 
	 */
	@RequestMapping(value="/instagram_add", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public String home(String userId, String igId, String igToken) throws MtException {
		
		Aggregation aggregation = new Aggregation();
		
		try {
			
			//aggregation.setProfileId(userId);
			aggregation.setAggregationName(INSTAGRAM);
			
			_aggregationRepo.save(aggregation);
			
			return aggregation.toString();
		} catch(Exception e) {
		
			log.error("Error Creating Aggregation Record.", e);
			throw new MtException("Error Creating Aggregation Record.", e.getMessage());
	}
	}
	
	@RequestMapping(value="/instagram_friends", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public String getFriends(String userId, String igId, String igToken) {
		
		RestTemplate restTemplate = new RestTemplate();

		InstagramResponse response = restTemplate.getForObject(
		        "https://api.instagram.com/v1/users/" + igId + "/followed-by?access_token=" + igToken,
		        InstagramResponse.class);

		System.out.println(response);
		
		return response.toString();
	}
}