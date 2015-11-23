package com.mt.controllers.facebook;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.mt.models.Aggregation;
import com.mt.models.AggregationSourceObject;
import com.mt.models.Post;
import com.mt.models.repository.AggregationRepository;
import com.mt.models.repository.AggregationSourceObjectRepository;
import com.mt.models.repository.PostRepository;
import com.mt.models.repository.UserRepository;

@Controller
public class FacebookLoginController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private static final String FACEBOOK = "Facebook";
	private static final String YES = "Y";
	private static final String NO = "N";
	
	@Autowired 
	AggregationRepository _aggregationRepo;
	
	@Autowired
	AggregationSourceObjectRepository _aggregationSourceObjectRepository;
	
	@Autowired
	PostRepository _postRepository;
	
	@Autowired
	UserRepository _userRepository;
	
	/*
	 * This REST api will redirect the user to a Facebook authorization page.
	 */
	@RequestMapping("/fb/login")
	public void login (HttpServletRequest request, HttpServletResponse response) throws IOException{
		
				
		FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory("956170854392949", "5724c20e501b3d770370f04fecffbb2c");
		
		OAuth2Parameters params = new OAuth2Parameters();
		params.setRedirectUri("http://54.149.27.205:8080/fb/callback");
		//params.setRedirectUri("http://localhost:8080/fb/callback");
		params.setScope("public_profile, email, user_friends, user_posts, user_photos, user_videos");
		//Store user Id and client address and port, to be made available in callback, otherwise it gets lost redirection.
		params.setState(request.getParameter("userId") + "," + request.getHeader("Referer"));
		
		OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
		
		String authorizeUrl = oauthOperations.buildAuthorizeUrl(params);
		
		log.info(" fb/login User ID " + request.getParameter("userId"));
		
		log.info(" fb/login URL " + authorizeUrl);
		
		log.info("Login Referer : " + request.getHeader("Referer"));
		response.sendRedirect(authorizeUrl);
		
	}
	
	/*
	 * This REST api is the method for the callback URL, where the user will be redirected after logging into Facebook.
	 * Using the authorization code parameter received from Facebook, get an access token and save it in the session.
	 */
	
	@RequestMapping("/fb/callback")
	//@ResponseStatus(value=HttpStatus.OK)
	public String callback(@RequestParam("code") String authorizationCode, @RequestParam("state") String callbackParam, HttpServletRequest request){
		
		FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory("956170854392949", "5724c20e501b3d770370f04fecffbb2c");
		
		OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
		
		AccessGrant accessGrant = oauthOperations.exchangeForAccess(authorizationCode, "http://54.149.27.205:8080/fb/callback", null);
		//AccessGrant accessGrant = oauthOperations.exchangeForAccess(authorizationCode, "http://localhost:8080/fb/callback", null);
		String token = accessGrant.getAccessToken();
		
		//request.getSession().setAttribute("facebookToken", token);
		
		String arr[] = callbackParam.split(",");
		
		String moteUserId = arr[0];
		log.info("redirect User ID " + moteUserId);
		
		Facebook facebook = new FacebookTemplate(token);
		
		persistFBAggregationContent(facebook, moteUserId,token);
		
		//return "redirect:/fb_login_success?facebookToken=" + token;
		//return "redirect:/fb_login_success?facebookToken=" + token;
		log.info("Client URL required for redirect : " + arr[1]);
		return "redirect:" + arr[1]+ "/#/app/aggregation";
		
	}
	
	private void persistFBAggregationContent(Facebook facebook, String moteUserId, String accessToken){
		
		try{
		
			User user = facebook.userOperations().getUserProfile();
			
			com.mt.models.User moteUser = new com.mt.models.User();
			
			try{
				moteUser.setProfileId(Long.parseLong(moteUserId));
			}catch(Exception e){
				log.error("Invalid User ID, exiting FB persistence: ", e);
			}
			
			Aggregation aggregation = new Aggregation();
			
			aggregation.setAggregationId(Long.parseLong(user.getId()));
			aggregation.setAggregationIsFriend(YES);
			aggregation.setAggregationName(FACEBOOK);
			aggregation.setAggregationSourceId(2);
			aggregation.setProfileId(moteUser);
			aggregation.setAccessToken(accessToken);
			
			_aggregationRepo.save(aggregation);
			
			
			PagedList<Photo> photos = facebook.mediaOperations().getPhotos(user.getId());
			
			for (Photo photo : photos) {
				
				AggregationSourceObject sourceObject = new AggregationSourceObject();
				
				sourceObject.setAggregationId(Long.parseLong(user.getId()));
				sourceObject.setSourceObjectUrl(photo.getPicture());
				sourceObject.setSourceObjectCaption(photo.getName());
				
				_aggregationSourceObjectRepository.save(sourceObject);
				
				//For each source object create new Post entry with reference to source_id
				Post post = new Post();
				
				post.setPostTypeCode("public");
				post.setPostObjectPath(FACEBOOK);
				post.getProfile().setProfileId(Long.parseLong(moteUserId));
				post.setPostDate( Calendar.getInstance());
				post.setPostObjectPath(sourceObject.getSourceObjectUrl());
				post.setAggregationSourceObject(sourceObject);
				
				_postRepository.save(post);
				
			}
			
			log.info("FB Persistance success.");
			
		}catch(Exception e){
			log.error("FB Persistance Failure :" , e);
		}
	}
}
