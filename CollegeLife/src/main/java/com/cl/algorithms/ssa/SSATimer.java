package com.cl.algorithms.ssa;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.cl.models.dao.PostDao;

/**
 * 
 * @author gibranecastillo
 *
 */
@EnableScheduling
public class SSATimer {
	PostDao _postDao;
	
	@Scheduled(fixedRate=60000)
	public synchronized void staExecuter() {
		double V, L, Cr, CrIdealAvg, Cl, ClIdealAvg, Cpn, CpnAvg, Ns;
	}
	
	//public void getFriend
	
	/*
	 * The Social Stairway Algorithm needs the following work:
	 * 1.  Kevin/Ryan want the coefficient and threshold values to be dynamic.
	 * We need to create an SQL table that will hold the values, which can be change adhoc by a MySQL administrator
	 * [perhaps in the future we can create a web client to do that].
	 * We may want to skip this until after the Demo and focus on completing the algorithm and just use the ones that
	 * are hard coded right now or modify them if necessary but go with hardcoded values for now
	 * 
	 * 2. Implement Timer to execute the Social Stairway Algorithm.
	 * 
	 * 3. Need logic to calculate certain likes and post values.
	 * Sanjay and Gibran sort of talked about that and decided to all a new field to one of the existing tables so that
	 * if the post was already promoted to say National feeds, then we don't need to run the algorithm...
	 * 
	 * 4. Integrate Social Stairway Algorithm with Front-End.  The Front-End and the Back-End is already integrated and working, but the Social Stairway Algorithm is not integrated, we would have to come up with some URIs that Front-End can call to get what it needs ....
	 * 
	 */
}