package com.mt.algorithm.ssa;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * The <code>SSAScheduledTask</code> is the Social Stairway Algorithm Scheduler.
 * 
 * @author gibranecastillo
 *
 */
@ComponentScan
@EnableScheduling
public class SSAScheduledTask {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * The Scheduled annotation defines when to execute the SSA task or job.
	 * Using fixedRate, which specifies the interval between method invocations
	 * measure from the start time of each invocation.
	 * 
	 * 300000 Milliseconds equal to 5 Minutes
	 */
	@Scheduled(fixedRate=300000)
	public synchronized void executeSSATask() {
		log.info("Starting Algorithm : " + new Date());
		
		SSAJob ssaJob = new SSAJob();
		
		ssaJob.initiate();
	}
}