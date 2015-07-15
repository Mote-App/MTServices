package com.mt.algorithm.ssa;

import org.springframework.beans.factory.annotation.Autowired;

import com.mt.models.SSAParams;
import com.mt.models.dao.SSAParamsDao;

/**
 * The <code>SSACalculator</code> class is the Social Stairway Algorithm Calculator.
 * 
 * @author gibranecastillo
 *
 */
public class SSACalculator {
	@Autowired
	private SSAParamsDao _ssaParamsDao = new SSAParamsDao();
	
	// Friend Feed to School Feed Threshold Ratio.
	private double Kf;
	
	// School Feed to National Feed Threshold Ratio.
	private double Ks;
	
	// Preliminary Post Coefficient.
	private double Cf;
	
	// Ideal Number of posts in School feed.
	private long NsIdeal;
	
	// Ideal Number of posts in National feed.
	private long NnIdeal;
	
	// T1, T2, T3, and T4 are Tuning Coefficients.
	private double T1, T2, T3, T4;
	
	/**
	 * Constructs an SSACalculator instance.
	 * 
	 * @param value
	 */
	public SSACalculator() {
		SSAParams ssaParams = _ssaParamsDao.getSSAParams();
		Kf = ssaParams.getKf();
		Ks = ssaParams.getKs();
		Cf = ssaParams.getCf();
		NsIdeal = ssaParams.getNsIdeal();
		NnIdeal = ssaParams.getNnIdeal();
		T1 = ssaParams.getT1();
		T2 = ssaParams.getT2();
		T3 = ssaParams.getT3();
		T4 = ssaParams.getT4();
	}
	
	/**
	 * @return the Kf
	 */
	public double getKf() {
		return Kf;
	}
	
	/**
	 * @return the Ks
	 */
	public double getKs() {
		return Ks;
	}
	
	/**
	 * Calculates the Post Ratio to determine if a Friend Feed post can be display or publish in the School Feed as well.
	 * 
	 * @param V a long value that represents the number of 'views' on a post.
	 * @param L a long value that represents the number of 'likes' on a post.
	 * @param Ns a long value that represents the number of 'posts' in School Feed.
	 * 
	 * @return a double value that represents the calculated post ratio.
	 */
	public double calculateRf(long V, long L, long Ns) {
		double Rf = 0.0;
		
		Rf = (L/V) * Cf * this.calculateCns(Ns);
		
		return Rf; 
	}
	
	/**
	 * Calculates the School Feed Capacity Coefficient.
	 * 
	 * @param Ns a long value that represents the number of 'posts' in School Feed.
	 * 
	 * @return a double value that represents the calculated school feed capacity coefficient.
	 */
	public double calculateCns(long Ns) {
		double Cns = 0.0;
		
		Cns = (NsIdeal/Ns) * T1;
		
		return Cns;
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
	 * @return a double value that represents the calculated post ratio.
	 */
	public double calculateRs(long V, long L, long Cr, double CrIdealAvg, long Cl, double ClIdealAvg, long Cpn, double CpnAvg, long Ns) {
		double Rs = 0.0;
		double Cs = this.calculateCs(Cr, CrIdealAvg, Cl, ClIdealAvg);
		double Cp = this.calculateCp(Cpn, CpnAvg);
		double Cnn = this.calculateCnn(Ns);
		
		Rs = (L/V) * Cs * Cp * Cnn;
		
		return Rs;
	}
	
	/**
	 * Calculates the School Coefficient.
	 * 
	 * @param Cr a long value that represents the number of 'people' registered from that school.
	 * @param CrIdealAvg a double value that represents the average number of 'people' registered per school.
	 * @param Cl a long value that represents the number of 'likes' per post from that school.
	 * @param ClIdealAvg a double value that represents the average number of 'likes' per post from all school.
	 * 
	 * @return a double value that represents the calculated school coefficient.
	 */
	public double calculateCs(long Cr, double CrIdealAvg, long Cl, double ClIdealAvg) {
		double Cs = 0.0;
		
		Cs = ((Cr/CrIdealAvg) * (Cl/ClIdealAvg)) * T2;
		
		return Cs;
	}
	
	/**
	 * Calculates the Post Flux Coefficient.
	 * 
	 * @param Cpn a long value that represents the number of 'post' from that school.
	 * @param CpnAvg a double value that represents the average number of 'post' from that school.
	 * 
	 * @return a double value that represents the calculated post flux coefficient.
	 */
	public double calculateCp(long Cpn, double CpnAvg) {
		double Cp = 0.0;
		
		Cp = (CpnAvg/Cpn) * T3;
		
		return Cp;
	}
	
	/**
	 * Calculates the National Feed Capacity Coefficient.
	 * 
	 * @param Ns a long value that represents the number of 'posts' in School Feed.
	 * 
	 * @return a double value that represents the calculated national feed capacity coefficient.
	 */
	public double calculateCnn(long Ns) {
		double Cnn = 0.0;
		
		Cnn = (NnIdeal/Ns) * T4;
		
		return Cnn;
	}
}