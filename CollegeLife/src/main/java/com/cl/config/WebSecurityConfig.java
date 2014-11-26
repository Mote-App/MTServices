package com.cl.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	/*@Override
	protected void configure(HttpSecurity http) throws Exception {

		 http
         .authorizeRequests().anyRequest().fullyAuthenticated();
		 
		 http.httpBasic();
		 //http.csrf().disable();
		 
		//super.configure(http);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(new UserDetailService());
	}*/
	
	/*@Override
	public UserDetailsService userDetailsServiceBean() throws Exception {
		return new UserDetailServiceImpl();
	}*/
}
