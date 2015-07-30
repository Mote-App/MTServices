package com.mt.algorithm.ssa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mt.models.SSAParams;

/**
 * The <code>SSACalculator</code> class is the Social Stairway Algorithm Calculator.
 * 
 * @author gibranecastillo
 *
 */
public class SSACalculator {
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
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * Constructs an SSACalculator instance.
	 * 
	 * @param value
	 */
	public SSACalculator(SSAParams ssaParams) {
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
		
		if(V != 0) {
			Rf = (L / V) * Cf * this.calculateCns(Ns);
			log.info("===============================================================");
			log.info("Rf = (L / V) * Cf * this.calculateCns(Ns)");
			log.info("Rf = (" + L + " / " + V + ") * " + Cf + "* Cns");
			log.info("===============================================================");
		}
		
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
		
		if(Ns != 0) {
			Cns = (NsIdeal / Ns) * T1;
			log.info("Cns = (NsIdeal / Ns) * T1");
			log.info("Cns = (" + NsIdeal + " / " + Ns + ") * " + T1);
		}
		
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
		
		if(V != 0) {
			Rs = (L / V) * Cs * Cp * Cnn;
			log.info("===============================================================");
			log.info("Rs = (L / V) * Cs * Cp * Cnn");
			log.info("Rs = (" + L + " / " + V + ") * " + Cs + " * " + Cp + " * " + Cnn);
			log.info("===============================================================");
		}
		
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
		
		if(CrIdealAvg != 0 && ClIdealAvg != 0) {
			Cs = ((Cr / CrIdealAvg) * (Cl / ClIdealAvg)) * T2;
			log.info("Cs = ((Cr / CrIdealAvg) * (Cl / ClIdealAvg)) * T2");
			log.info("Cs = ((" + Cr + " / " + CrIdealAvg + ") * ( " + Cl + " / " + ClIdealAvg + " )) * " + T2);
		}
		
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
		
		if(Cpn != 0) {
			Cp = (CpnAvg / Cpn) * T3;
			log.info("Cp = (CpnAvg / Cpn) * T3");
			log.info("Cp = (" + CpnAvg + " / " + Cpn + ") * " + T3);
		}
		
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
		
		if(Ns != 0) {
			Cnn = (NnIdeal/Ns) * T4;
			log.info("Cnn = (NnIdeal/Ns) * T4");
			log.info("Cnn = (" + NnIdeal + "/" + Ns + ") * " + T4);
		}
		
		return Cnn;
	}
}