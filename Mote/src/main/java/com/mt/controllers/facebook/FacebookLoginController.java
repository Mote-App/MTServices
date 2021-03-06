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
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mt.models.Aggregation;
import com.mt.models.AggregationSourceObject;
import com.mt.models.Post;
import com.mt.models.dao.UserFriendsDao;
import com.mt.models.repository.AggregationRepository;
import com.mt.models.repository.AggregationSourceObjectRepository;
import com.mt.models.repository.PostRepository;
import com.mt.models.repository.UserFriendRepository;

/**
 * The <code>FacebookLoginController</code> is ...
 * 
 * @Controller marks the FacebookExpiredToken.java POJO class as MVC controller
 * 
 * @author gibranecastillo
 *
 */
@Controller
public class FacebookLoginController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private static final String FACEBOOK = "Facebook";
	private static final String YES = "Y";
	//private static final String NO = "N";
	
	@Autowired 
	AggregationRepository _aggregationRepo;
	
	@Autowired
	AggregationSourceObjectRepository _aggregationSourceObjectRepository;
	
	@Autowired
	UserFriendRepository _userFriendRepository;
	
	@Autowired
	UserFriendsDao _userFriendsDao;
	
	@Autowired
	PostRepository _postRepository;
	
	/*
	 * This REST api will redirect the user to a Facebook authorization page.
	 */
	@RequestMapping("/fb/login")
	public void login (HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info("\n*************************************************************************");
		long moteUserId = Long.parseLong(request.getParameter("userId"));
		String token = request.getParameter("accessToken");
		log.info("Mote User ID: " + moteUserId);
		log.info("OAuth 2.0 Token:  " + token);
		log.info("OAuth 2.0 Token - Authorize URL: \n");
		
		Facebook facebook = new FacebookTemplate(token);
		persistFacebookUserProfile(facebook, moteUserId, token);
		autoFriendFacebookUserProfileFriends(facebook, moteUserId);
		postFacebookUserProfileMedia(facebook, moteUserId);
		
		log.info("Redirect client back to aggregation page; return to 'redirect:file://www/templates/login_to_aggregate.html'");
		
		log.info("*************************************************************************\n");
	}
	
	/**
	 * This REST api is the method for the callback URL, where the user will be redirected after logging into Facebook.
	 * Using the authorization code parameter received from Facebook, get an access token and save it in the session.
	 * 
	 * @RequestParam(name) maps HTTP request parameter with name to the method parameter.
	 * 
	 * @param authorizationCode
	 * @param callbackParam
	 * @param request
	 * @return
	 */
	@RequestMapping("/fb/callback")
	public String callback(@RequestParam("code") String authorizationCode, @RequestParam("state") String callbackParam, HttpServletRequest request) {
		log.info("authorizationCode: " + authorizationCode);
		log.info("callbackParam: " + callbackParam);
		
		// Mote Facebook Test App, to be able to use localhost
		//FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory("1105685566108143", "0b4b69914152837f9978611d84629e66");
		// Mote Facebook Production App
		FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory("956170854392949", "5724c20e501b3d770370f04fecffbb2c");
		
		OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
		AccessGrant accessGrant = oauthOperations.exchangeForAccess(authorizationCode, "http://54.200.159.155:8080/fb/callback", null);
		String token = accessGrant.getAccessToken();
		
		long moteUserId = Long.parseLong(callbackParam);
		log.info("Mote UserID: " + moteUserId);
		
		Facebook facebook = new FacebookTemplate(token);
		persistFacebookUserProfile(facebook, moteUserId, token);
		autoFriendFacebookUserProfileFriends(facebook, moteUserId);
		postFacebookUserProfileMedia(facebook, moteUserId);
		
		log.info("Redirect client back to aggregation page; return to 'redirect:file://www/templates/login_to_aggregate.html'");
		
		return "redirect:file://www/templates/login_to_aggregate.html";
	}
	
	/**
	 * Persist Mote user's basic Facebook user profile aggregation content into motedb.
	 * 
	 * @param facebook an interface specifying a basic set of operations for interacting with Facebook.
	 * @param moteUserId a long value that represents the Mote User Id.
	 * @param accessToken a String value that represents the Facebook user's OAuth 2.0 access token.
	 */
	private void persistFacebookUserProfile(Facebook facebook, long moteUserId, String accessToken) {
		try {
			User facebookUser = facebook.userOperations().getUserProfile();
			com.mt.models.User moteUser = new com.mt.models.User();
			
			try {
				moteUser.setProfileId(moteUserId);
			} catch(Exception e) {
				log.error("Invalid User ID, exiting FB persistence: ", e);
			}
			
			Aggregation aggregation = new Aggregation();
			
			aggregation.setAggregationId(Long.parseLong(facebookUser.getId()));
			//This field should be removed from Aggregation table because its not used. Instead existing table profile_has_friend directly.
			aggregation.setAggregationIsFriend(YES);
			aggregation.setAggregationName(FACEBOOK);
			//Hardcoded aggregation platform to be 2
			aggregation.setAggregationSourceId(2);
			aggregation.setProfileId(moteUser);
			aggregation.setAccessToken(accessToken);
			
			log.info(moteUser.toString());
			
			_aggregationRepo.save(aggregation);
			
			log.info("The following basic Facebook Aggregation Content Persistance was successfull: " + aggregation.toString());
		} catch(Exception e) {
			log.error("The basic Facebook Aggregation Content Persistance was a failure" , e);
		}
	}
	
	/**
	 * Auto-friend Mote user's Facebook friends.  In other words, if Mote user's Facebook friends are also
	 * register with Mote, make them friend in Mote as well, if they are not register ignore for now.
	 * 
	 * @param facebook an interface specifying a basic set of operations for interacting with Facebook.
	 * @param moteUserId a long value that represents the Mote User Id.
	 */
	private void autoFriendFacebookUserProfileFriends(Facebook facebook, long moteUserId) {
		try {
			User facebookUser = facebook.userOperations().getUserProfile();
			PagedList<String> fbFriendIds = facebook.friendOperations().getFriendIds(facebookUser.getId());
			
			log.info("fbFriendIds.size(): " + fbFriendIds.size());
			
			for(String fbFriendId : fbFriendIds) {
				/*
				 * If friendId (Facebook User's Friend fb id) is found in motedb.aggregation table (aggregation_id),
				 * then this Facebook user has a Mote profile; therefore, auto friend if they are not friends already with Mote user.
				 */
				com.mt.models.User fbFriendMoteUser = _aggregationRepo.findUserAggregationFriend(Long.parseLong(fbFriendId), FACEBOOK);
				
				if(fbFriendMoteUser != null) {
					log.info("friendId '" + fbFriendId + "' (Facebook User's Friend fb id) is register with Mote, found in motedb.aggregation table (aggregation_id: '" + fbFriendMoteUser.getProfileId().longValue() + "')" + fbFriendMoteUser.toString());
					
					Long mtFriendId = _userFriendRepository.findUserFriend(moteUserId, fbFriendMoteUser.getProfileId().longValue());
					
					log.info("mtFriendId: " + mtFriendId);
					
					if(mtFriendId != null &&  mtFriendId > 0) {
						log.info("Already friends in Mote; therefore, to avoid adding duplicate record do nothing.");
					} else {
						log.info("Both register in Mote but they are not friends in Mote; therefore, auto-friend.");
						_userFriendsDao.addFriend(moteUserId, fbFriendMoteUser.getProfileId().longValue());
					}
				} else {
					log.info("friendId '" + fbFriendId + "' (Facebook User's Friend fb id) has not register with Mote, not found in motedb.aggregation table (aggregation_id)");
				}
			}
			
			log.info("Facebook Friend List Auto friending with Mote user completed successfully.");
		} catch(Exception e) {
			log.error("Facebook Friend List Auto friending with Mote user failed" , e);
		}
	}
	
	/**
	 * Persist and Post Mote user's Facebook user profile photos and videos aggregation content into motedb.
	 * 
	 * @param facebook an interface specifying a basic set of operations for interacting with Facebook.
	 * @param moteUserId a long value that represents the Mote User Id.
	 */
	private void postFacebookUserProfileMedia(Facebook facebook, long moteUserId) {
		try {
			User facebookUser = facebook.userOperations().getUserProfile();
			//PagedList<Photo> photos = facebook.mediaOperations().getPhotos(facebookUser.getId());
			PagedList<org.springframework.social.facebook.api.Post> fbPosts = facebook.feedOperations().getFeed();
			
			log.info("Total posts obtained : " + fbPosts.size());
			
			for(org.springframework.social.facebook.api.Post fbPost : fbPosts) {
				//Only process post type media (photo/video) Post
				log.info("Processing facebook post's type: " + fbPost.getType());
				
				if(fbPost.getType() == org.springframework.social.facebook.api.Post.PostType.PHOTO) {
					log.info("Processing facebook post's photo - fbPost.getPicture(): " + fbPost.getPicture());
					
					AggregationSourceObject sourceObject = new AggregationSourceObject();
					sourceObject.setAggregationId(Long.parseLong(facebookUser.getId()));
					sourceObject.setSourceObjectUrl(fbPost.getPicture());
					sourceObject.setSourceObjectCaption(fbPost.getCaption());
					
					//log.info(sourceObject.toString());
					Long existingSourceObjectId = _aggregationSourceObjectRepository.findExistingAggregationSourceObject(sourceObject.getAggregationId(), sourceObject.getSourceObjectUrl()); 
					
					if( existingSourceObjectId != null && existingSourceObjectId > 0 ){
						//Facebook content already exist so continue with next content
						continue;
					}
					
					_aggregationSourceObjectRepository.save(sourceObject);
					
					//For each source object create new Post entry with reference to source_id
					Post post = new Post();
					
					post.setPostTypeCode("public");
					post.setPostObjectPath(FACEBOOK);
					post.getProfile().setProfileId(moteUserId);
					post.setPostDate(Calendar.getInstance());
					post.setPostObjectPath(sourceObject.getSourceObjectUrl());
					post.setPostCaption(sourceObject.getSourceObjectCaption());
					post.setAggregationSourceObject(sourceObject);
					
					//log.info(post.toString());
					_postRepository.save(post);
				} else if(fbPost.getType() == org.springframework.social.facebook.api.Post.PostType.VIDEO) {
					log.info("******************  Processing facebook post's video  *******************");
					log.info("** fbPost.getCaption(): " + fbPost.getCaption());
					log.info("** fbPost.getDescription(): " + fbPost.getDescription());
					log.info("** fbPost.getIcon(): " + fbPost.getIcon());
					log.info("** fbPost.getId(): " + fbPost.getId());
					log.info("** fbPost.getLink(): " + fbPost.getLink());
					log.info("** fbPost.getMessage(): " + fbPost.getMessage());
					log.info("** fbPost.getName(): " + fbPost.getName());
					log.info("** fbPost.getObjectId(): " + fbPost.getObjectId());
					log.info("** fbPost.getPicture(): " + fbPost.getPicture());
					log.info("** fbPost.getShares(): " + fbPost.getShares());
					log.info("||||||||||||||||||||||||||||||||||||||||||||");
					log.info("** fbPost.getSource(): " + fbPost.getSource());
					log.info("||||||||||||||||||||||||||||||||||||||||||||");
					log.info("** fbPost.getStory(): " + fbPost.getStory());
					log.info("*************************************************************************");
					
					if(fbPost.getSource() != null) {
						AggregationSourceObject sourceObject = new AggregationSourceObject();
						
						sourceObject.setAggregationId(Long.parseLong(facebookUser.getId()));
						
						//Video video = facebook.mediaOperations().getVideo(fbPost.getObjectId());
						sourceObject.setSourceObjectUrl(fbPost.getSource());
						sourceObject.setSourceObjectCaption(fbPost.getCaption());
						
						//log.info(sourceObject.toString());
						Long existingSourceObjectId = _aggregationSourceObjectRepository.findExistingAggregationSourceObject(sourceObject.getAggregationId(), sourceObject.getSourceObjectUrl()); 
						
						if(existingSourceObjectId != null && existingSourceObjectId > 0) {
							//Facebook content already exist so continue with next content
							continue;
						}
						
						_aggregationSourceObjectRepository.save(sourceObject);
						
						//For each source object create new Post entry with reference to source_id
						Post post = new Post();
						
						post.setPostTypeCode("public");
						post.setPostObjectPath(FACEBOOK);
						post.getProfile().setProfileId(moteUserId);
						post.setPostDate(Calendar.getInstance());
						post.setPostObjectPath(sourceObject.getSourceObjectUrl());
						post.setPostCaption(sourceObject.getSourceObjectCaption());
						post.setAggregationSourceObject(sourceObject);
						
						//log.info(post.toString());
						_postRepository.save(post);
					}
				}
			}
			
			log.info("The Facebook user profile photos Persistance was successfull.");
		} catch(Exception e) {
			log.error("The Facebook user profile photos Persistance was a failure" , e);
		}
	}
}