package com.mt.actuators.health;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import com.mt.models.College;
import com.mt.models.dao.CollegeDao;

/**
 * The <code>CollegeHealthIndicator</code> class is a custom health check indicator.
 * 
 * @author gibranecastillo
 *
 */
@Component
public class CollegeHealthIndicator implements HealthIndicator {
	@Autowired
	private CollegeDao collegeDao;
	
	/**
	 * Returns the status of up as long as there is at least one college in the database; otherwise,
	 * it would return a status of down.  For either status the HealthIndicator also returns a count 
	 * attribute indicating the total number of colleges in the database.
	 */
	@Override
	public Health health() {
		List<College> colleges = collegeDao.getAll();
		
		if(colleges == null || colleges.size() == 0) {
			return Health.down().withDetail("count", 0).build();
		}
		
		return Health.up().withDetail("count", colleges.size()).build();
	}
}