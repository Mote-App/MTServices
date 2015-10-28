package com.mt.controllers.facebook;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Photo;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mt.models.Aggregation;
import com.mt.models.AggregationSourceObject;
import com.mt.models.repository.AggregationRepository;
import com.mt.models.repository.AggregationSourceObjectRepository;

@Controller
public class FacebookLoginController {

	private static final String FACEBOOK = "Facebook";
	private static final String YES = "Y";
	private static final String NO = "N";
	
	@Autowired 
	AggregationRepository _aggregationRepo;
	
	@Autowired
	AggregationSourceObjectRepository _aggregationSourceObjectRepository; 
	/*
	 * This REST api will redirect the user to a Facebook authorization page.
	 */
	@RequestMapping("/fb/login")
	public void login (HttpServletResponse response) throws IOException{
		
		FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory("956170854392949", "5724c20e501b3d770370f04fecffbb2c");
		
		OAuth2Parameters params = new OAuth2Parameters();
		params.setRedirectUri("http://54.149.27.205:8080/fb/callback");
		params.setScope("public_profile, email, user_friends, user_posts, user_photos, user_videos");
		
		OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
		
		String authorizeUrl = oauthOperations.buildAuthorizeUrl(params);
		
		response.sendRedirect(authorizeUrl);
		
	}
	
	/*
	 * This REST api is the method for the callback URL, where the user will be redirected after logging into Facebook.
	 * Using the authorization code parameter received from Facebook, get an access token and save it in the session.
	 */
	
	@RequestMapping("/fb/callback")
	public String callback(@RequestParam("code") String authorizationCode, HttpServletRequest request){
		
		FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory("956170854392949", "5724c20e501b3d770370f04fecffbb2c");
		
		OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
		
		AccessGrant accessGrant = oauthOperations.exchangeForAccess(authorizationCode, "http://54.149.27.205:8080/fb/callback", null);
		
		String token = accessGrant.getAccessToken();
		
		request.getSession().setAttribute("facebookToken", token);
		
		return "redirect:/fb";
	}
	
	
	@RequestMapping("/fb")
	public String fb(HttpServletRequest request){
		
		
		String accessToken = (String)request.getSession().getAttribute("facebookToken");
		request.getSession().setAttribute("userId", request.getAttribute("userId"));
		
		Facebook facebook = new FacebookTemplate(accessToken);
		
		if(facebook.isAuthorized()){
			return "redirect:/persist_fb_data";
		}else{
			return "redirect:/fb/login";
		}
		
	}
	
	@RequestMapping(value="/persist_fb_data", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public String persistFBData(HttpServletRequest request){
		
		String accessToken = (String)request.getSession().getAttribute("facebookToken");
		//Mote userId
		String userId = (String)request.getSession().getAttribute("userId");
		
		Facebook facebook = new FacebookTemplate(accessToken, userId);
		
		return "Data Persisted";
	}
	
	private void persistFBAggregationContent(Facebook facebook, String moteUserId ){
		
		User user = facebook.userOperations().getUserProfile();
		
		com.mt.models.User moteUser = new com.mt.models.User();
		moteUser.setProfileId(Long.parseLong(moteUserId));
		
		Aggregation aggregation = new Aggregation();
		
		aggregation.setAggregationId(Long.parseLong(user.getId()));
		aggregation.setAggregationIsFriend(YES);
		aggregation.setAggregationName(FACEBOOK);
		aggregation.setAggregationSourceId(2);
		aggregation.setProfileId(moteUser);
		
		_aggregationRepo.save(aggregation);
		
		
		PagedList<Photo> photos = facebook.mediaOperations().getPhotos(user.getId());
		
		for (Photo photo : photos) {
			
			AggregationSourceObject sourceObject = new AggregationSourceObject();
			
			sourceObject.setAggregationId(Long.parseLong(user.getId()));
			sourceObject.setSourceObjectUrl(photo.getLink());
			sourceObject.setSourceObjectCaption(photo.getName());
			
			_aggregationSourceObjectRepository.save(sourceObject);
			
		}
		
	}
}
