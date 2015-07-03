package com.mt.algorithm.ssa;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
public class SSAScheduledTask {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 300000 Milliseconds equal to 5 Minutes
	 */
	@Scheduled(fixedRate=300000)
	public synchronized void executeSSATask() {
		
		log.info("Starting Algorithm : " + new Date());
		
		SSAJob ssaJob = new SSAJob();
		
		ssaJob.initiate();
	}
	
}