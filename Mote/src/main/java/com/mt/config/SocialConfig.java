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
	public void addConnectionFactories(ConnectionFactoryConfigurer config,
			Environment env) {

/*		config.addConnectionFactory(new FacebookConnectionFactory(
				env.getProperty("spring.social.facebook.appId"), 
				env.getProperty("spring.social.facebook.appSecret")));
	*/
		}

	@Override
	public UserIdSource getUserIdSource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsersConnectionRepository getUsersConnectionRepository(
			ConnectionFactoryLocator arg0) {
		// TODO Auto-generated method stub
		return null;
	}
		
	
	
	/*@Inject
    private DataSource dataSource;

    @Inject
    private TextEncryptor textEncryptor;
	    
	@Bean
	public ConnectionFactoryLocator connectionFactoryLocator() {
		
		ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
		
		registry.addConnectionFactory(new FacebookConnectionFactory(facebook_appId,facebook_appSecret));
		
		return registry;
	}*/
	
	/*@Bean
    public UsersConnectionRepository usersConnectionRepository() {
        return new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator(), 
            textEncryptor);
    }
	
	@Bean
    @Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
	public ConnectionRepository connectionRepository(){
		
		Authentication authentication = SecurityContext.getContext().getAuthentication();
        if (authentication == null) {
            throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
        }
        return usersConnectionRepository().createConnectionRepository(authentication.getName());
	}
*/
/*	@Bean
    public ConnectController connectController() {
        return new ConnectController(connectionFactoryLocator(), 
            connectionRepository());
    }*/
	
}
