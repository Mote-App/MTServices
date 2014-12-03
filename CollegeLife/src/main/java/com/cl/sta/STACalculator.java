package com.cl.sta;

public enum STACalculator {
	// Friend Feed to School Feed Threshold Ratio.
	Kf(0.2), // calls constructor with value 0.2
	
	// School Feed to National Feed Threshold Ratio.
	Ks(0.2); // calls constructor with value 0.2
		
	// Preliminary Post Coefficient.
	private final double Cf = 0.5;
	
	// Ideal Number of posts in School feed.
	private final double NsIdeal = 800;
	
	// Ideal Number of posts in National feed.
	private final double NnIdeal = 800;
	
	// T1, T2, T3, and T4 are Tuning Coefficients.
	private final double T1 = 0.5, T2 = 0.3, T3 = 0.3, T4 = 0.3;
	
	// value to be added to enum.
	private final double value;
	
	/**
	 * Constructs an STACalculator.
	 * 
	 * @param value
	 */
	STACalculator(double value) {
		this.value = value;
	}
	
	/**
	 * Gets the value added to enum.
	 * 
	 * @return the value added to enum.
	 */
	public double getValue() {
		return this.value;
	}
	
	/**
	 * Calculates the Post Ratio to determine if a Friend Feed post can be display or publish in the School Feed as well.
	 * 
	 * @param V a double value that represents the number of 'views' on a post.
	 * @param L a double value that represents the number of 'likes' on a post.
	 * @param Ns a double value that represents the number of 'posts' in School Feed.
	 * 
	 * @return a double value that represents the calculated post ratio.
	 */
	public double calculateRf(double V, double L, double Ns) {
		double Rf = 0.0;
		
		Rf = (L/V) * Cf * this.calculateCns(Ns);
		
		return Rf; 
	}
	
	/**
	 * Calculates the School Feed Capacity Coefficient.
	 * 
	 * @param Ns a double value that represents the number of 'posts' in School Feed.
	 * 
	 * @return a double value that represents the calculated school feed capacity coefficient.
	 */
	public double calculateCns(double Ns) {
		double Cns = 0.0;
		
		Cns = (NsIdeal/Ns) * T1;
		
		return Cns;
	}
	
	/**
	 * Calculates the Post Ratio to determine if a Friend Feed post that is already showing in
	 * the School Feed can be display or publish in the National Feed as well.
	 * 
	 * @param V a double value that represents the number of 'views' on a post.
	 * @param L a double value that represents the number of 'likes' on a post.
	 * @param Cr a double value that represents the number of 'people' registered from that school.
	 * @param CrIdealAvg a double value that represents the average number of 'people' registered per school.
	 * @param Cl a double value that represents the number of 'likes' per post from that school.
	 * @param ClIdealAvg a double value that represents the average number of 'likes' per post from all school.
	 * @param Cpn a double value that represents the number of 'post' from that school per unit time.
	 * @param CpnAvg a double value that represents the average number of 'post' from that school per unit time. 
	 * @param Ns a double value that represents the number of 'posts' in School Feed.
	 * 
	 * @return a double value that represents the calculated post ratio.
	 */
	public double calculateRs(double V, double L, double Cr, double CrIdealAvg, double Cl, double ClIdealAvg, double Cpn, double CpnAvg, double Ns) {
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
	 * @param Cr a double value that represents the number of 'people' registered from that school.
	 * @param CrIdealAvg a double value that represents the average number of 'people' registered per school.
	 * @param Cl a double value that represents the number of 'likes' per post from that school.
	 * @param ClIdealAvg a double value that represents the average number of 'likes' per post from all school.
	 * 
	 * @return a double value that represents the calculated school coefficient.
	 */
	public double calculateCs(double Cr, double CrIdealAvg, double Cl, double ClIdealAvg) {
		double Cs = 0.0;
		
		Cs = ((Cr/CrIdealAvg) * (Cl/ClIdealAvg)) * T2;
		
		return Cs;
	}
	
	/**
	 * Calculates the Post Flux Coefficient.
	 * 
	 * @param Cpn a double value that represents the number of 'post' from that school per unit time.
	 * @param CpnAvg a double value that represents the average number of 'post' from that school per unit time.
	 * 
	 * @return a double value that represents the calculated post flux coefficient.
	 */
	public double calculateCp(double Cpn, double CpnAvg) {
		double Cp = 0.0;
		
		Cp = (CpnAvg/Cpn) * T3;
		
		return Cp;
	}
	
	/**
	 * Calculates the National Feed Capacity Coefficient.
	 * 
	 * @param Ns a double value that represents the number of 'posts' in School Feed.
	 * 
	 * @return a double value that represents the calculated national feed capacity coefficient.
	 */
	public double calculateCnn(double Ns) {
		double Cnn = 0.0;
		
		Cnn = (NnIdeal/Ns) * T4;
		
		return Cnn;
	}
}