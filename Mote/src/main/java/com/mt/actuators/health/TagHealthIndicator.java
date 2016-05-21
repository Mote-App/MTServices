package com.mt.actuators.health;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import com.mt.models.Tag;
import com.mt.models.dao.TagDao;

/**
 * The <code>TagHealthIndicator</code> class is a custom health check indicator.
 * 
 * @author gibranecastillo
 *
 */
@Component
public class TagHealthIndicator implements HealthIndicator {
	@Autowired
	private TagDao tagDao;
	
	/**
	 * Returns the status of up as long as there is at least one tag in the database; otherwise,
	 * it would return a status of down.  For either status the HealthIndicator also returns a count 
	 * attribute indicating the total number of tags in the database.
	 */
	@Override
	public Health health() {
		List<Tag> tags = tagDao.getTags();
		
		if(tags == null || tags.size() == 0) {
			return Health.down().withDetail("count", 0).build();
		}
		
		return Health.up().withDetail("count", tags.size()).build();
	}
}