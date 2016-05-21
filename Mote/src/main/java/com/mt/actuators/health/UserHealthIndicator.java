package com.mt.actuators.health;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import com.mt.models.User;
import com.mt.models.dao.UserDao;

/**
 * The <code>UserHealthIndicator</code> class is a custom health check indicator.
 * 
 * @author gibranecastillo
 *
 */
@Component
public class UserHealthIndicator implements HealthIndicator {
	@Autowired
	private UserDao userDao;
	
	/**
	 * Returns the status of up as long as there is at least one user in the database; otherwise,
	 * it would return a status of down.  For either status the HealthIndicator also returns a count 
	 * attribute indicating the total number of users in the database.
	 */
	@Override
	public Health health() {
		List<User> users = userDao.getAll();
		
		if(users == null || users.size() == 0) {
			return Health.down().withDetail("count", 0).build();
		}
		
		return Health.up().withDetail("count", users.size()).build();
	}
}