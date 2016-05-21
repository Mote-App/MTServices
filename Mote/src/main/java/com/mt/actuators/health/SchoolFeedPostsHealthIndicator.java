package com.mt.actuators.health;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import com.mt.models.Post;
import com.mt.models.dao.PostDao;

/**
 * The <code>SchoolFeedPostsHealthIndicator</code> class is a custom health check indicator.
 * 
 * @author gibranecastillo
 *
 */
@Component
public class SchoolFeedPostsHealthIndicator implements HealthIndicator {
	@Autowired
	private PostDao postDao;
	
	/**
	 * Returns the status of up as long as there is at least one friend feed post in the database; otherwise,
	 * it would return a status of down.  For either status the HealthIndicator also returns a count 
	 * attribute indicating the total number of friend feed posts in the database.
	 */
	@Override
	public Health health() {
		List<Post> posts = postDao.getSchoolFeedPosts();
		
		if(posts == null || posts.size() == 0) {
			return Health.down().withDetail("count", 0).build();
		}
		
		return Health.down().withDetail("count", posts.size()).build();
	}
}