package com.mt.algorithm.ssa;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mt.models.SSAParams;

public class TestSSAScheduledTask{
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	SSAParams params;
	
	@Before
	public void setup(){
		params = new SSAParams();
		
		params.setKf(0.20);
		params.setKs(0.20);
		params.setCf(0.50);
		params.setNsIdeal(800);
		params.setNnIdeal(800);
		params.setT1(0.50);
		params.setT2(0.30);
		params.setT3(0.30);
		params.setT4(0.30);
	}
	
	@Test
	public void testSSA(){
		Long friendFeedViewCount = 2L;
		Long friendFeedLikeCount = 1L;
		Long schoolPostCount = 1L;
		
		SSA ssa;
		ssa = new SSA(params);
		
		SSAPostRatio rfPostRatio = ssa.calculateRf(friendFeedViewCount, friendFeedLikeCount, schoolPostCount);
		
		log.info("Is Rf > Kf : " + rfPostRatio.isGreaterThan());
		
	}
	
}
