package com.mt.config;

import javax.inject.Inject;

import org.apache.tomcat.util.net.jsse.openssl.Authentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

@Configuration
public class SocialConfig{
	
	/*@Inject
    private Environment environment;

	@Bean
    public ConnectionFactoryLocator connectionFactoryLocator() {
        ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
        
        registry.addConnectionFactory(new FacebookConnectionFactory(
            environment.getProperty("spring.social.facebook.appId"),
            environment.getProperty("spring.social.facebook.appSecret")));
        
        return registry;
    }
	

	@Bean
	public ConnectController connectController() {
	        ConnectController controller = new ConnectController(
	            ConnectionFactoryLocator(), ConnectionRepository());
	        controller.setApplicationUrl(environment.getProperty("spring.social.facebook.callbackurl"));
	        return controller;
	}
	
	
	@Bean
    @Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
    public ConnectionRepository connectionRepository(
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
        }
        return usersConnectionRepository().createConnectionRepository(authentication.getName());
    }*/
}