package com.mt.algorithm.ssa;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * The <code>SSAScheduledTask</code> is the Social Stairway Algorithm Scheduler.
 * 
 * ComponentScan is used on configuration classes to configure the packages to be scanned by spring.
 * Component is used to mark your class as a bean (and eligible to scanning).
 * 
 * @author gibranecastillo
 *
 */
@Component
@EnableScheduling
public class SSAScheduledTask {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SSAJob ssaJob;
	
	/**
	 * The Scheduled annotation defines when to execute the SSA task or job.
	 * Using fixedRate, which specifies the interval between method invocations
	 * measure from the start time of each invocation.
	 * 
	 * 300000 Milliseconds equal to 5 Minutes
	 */
	@Scheduled(fixedRate=300000)
	public synchronized void executeSSATask() {
		log.info("Starting Social Stairway Algorithm : " + new Date());
		
		ssaJob.initiate();
	}
}