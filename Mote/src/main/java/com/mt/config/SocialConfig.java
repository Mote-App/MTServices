package com.mt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;

@Configuration
public class SocialConfig implements SocialConfigurer {
	@Value("${spring.social.facebook.appId}")
	private String facebook_appId;
	
	@Value("${spring.social.facebook.appSecret}")
	private String facebook_appSecret;
	
	@Override
	public void addConnectionFactories(ConnectionFactoryConfigurer config, Environment env) {
		//config.addConnectionFactory(new FacebookConnectionFactory(env.getProperty("spring.social.facebook.appId"), env.getProperty("spring.social.facebook.appSecret")));
	}
	
	@Override
	public UserIdSource getUserIdSource() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}