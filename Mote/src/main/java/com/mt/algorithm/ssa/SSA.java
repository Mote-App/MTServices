package com.mt.algorithm.ssa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mt.models.SSAParams;

/**
 * 
 * @author gibranecastillo
 *
 */
public class SSA {
	private SSACalculator ssaCalculator;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public SSA(SSAParams ssaParams) {
		ssaCalculator = new SSACalculator(ssaParams);
	}
	
	/**
	 * Calculates the Post Ratio to determine if a Friend Feed post can be display or publish in the School Feed as well.
	 * 
	 * @param V a long value that represents the number of 'views' on a post.
	 * @param L a long value that represents the number of 'likes' on a post.
	 * @param Ns a long value that represents the number of 'posts' in School Feed.
	 * 
	 * @return a PostRatio object that holds the calculated post ratio value and true or false if Rf > Kf.
	 */
	public SSAPostRatio calculateRf(long V, long L, long Ns) {
		SSAPostRatio rfPostRatio = new SSAPostRatio();
		
		double Rf = ssaCalculator.calculateRf(V, L, Ns);
		double Kf = ssaCalculator.getKf();
		
		boolean isRfGreaterThanKf = false;
		
		log.info("Rf (" + Rf + ") > Kf (" + Kf + "), if yes promote post from friend to school feed");
		
		if(Rf > Kf) {
			isRfGreaterThanKf = true;
		}
		
		rfPostRatio.setRatio(Rf);
		rfPostRatio.setGreaterThan(isRfGreaterThanKf);
		
		return rfPostRatio;
	}
	
	/**
	 * Calculates the Post Ratio to determine if a Friend Feed post that is already showing in
	 * the School Feed can be display or publish in the National Feed as well.
	 * 
	 * @param V a long value that represents the number of 'views' on a post.
	 * @param L a long value that represents the number of 'likes' on a post.
	 * @param Cr a long value that represents the number of 'people' registered from that school.
	 * @param CrIdealAvg a double value that represents the average number of 'people' registered per school.
	 * @param Cl a long value that represents the number of 'likes' per post from that school.
	 * @param ClIdealAvg a double value that represents the average number of 'likes' per post from all school.
	 * @param Cpn a long value that represents the number of 'post' from that school.
	 * @param CpnAvg a double value that represents the average number of 'post' from that school. 
	 * @param Ns a long value that represents the number of 'posts' in School Feed.
	 * 
	 * @return a PostRatio object that holds the calculated post ratio value and true or false if Rs > Ks.
	 */
	public SSAPostRatio calculateRs(long V, long L, long Cr, double CrIdealAvg, long Cl, double ClIdealAvg, long Cpn, double CpnAvg, long Ns) {
		SSAPostRatio rsPostRatio = new SSAPostRatio();
		
		double Rs = ssaCalculator.calculateRs(V, L, Cr, CrIdealAvg, Cl, ClIdealAvg, Cpn, CpnAvg, Ns);
		double Ks = ssaCalculator.getKs();
		
		boolean isRsGreaterThanKs = false;
		
		log.info("Rs (" + Rs + ") > Ks (" + Ks + "), if yes promote post from school to national feed");
		
		if(Rs > Ks) {
			isRsGreaterThanKs = true;
		}
		
		rsPostRatio.setRatio(Rs);
		rsPostRatio.setGreaterThan(isRsGreaterThanKs);
		
		return rsPostRatio;
	}
}