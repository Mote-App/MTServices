package com.cl.sta;

public class SocialStairwayAlgorithm {
	private double Rf = 0.0;
	private double Kf = 0.0;
	private boolean isRfGreaterThanKf = false;
	
	private double Rs = 0.0;
	private double Ks = 0.0;
	private boolean isRsGreaterThanKs = false;
	
	// each instance of STACalculator has an enum
	private STACalculator staCalculator;
	
	/**
	 * Calculates the Post Ratio to determine if a Friend Feed post can be display or publish in the School Feed as well.
	 * 
	 * @param V a double value that represents the number of 'views' on a post.
	 * @param L a double value that represents the number of 'likes' on a post.
	 * @param Ns a double value that represents the number of 'posts' in School Feed.
	 * 
	 * @return a PostRatio object that holds the calculated post ratio value and true or false if Rf > Kf.
	 */
	public PostRatio calculateRf(double V, double L, double Ns) {
		staCalculator = STACalculator.Kf;
		PostRatio rfPostRatio = new PostRatio();
		
		Rf = staCalculator.calculateRf(V, L, Ns);
		Kf = staCalculator.getValue();
		
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
	 * @return a PostRatio object that holds the calculated post ratio value and true or false if Rs > Ks.
	 */
	public PostRatio calculateRs(double V, double L, double Cr, double CrIdealAvg, double Cl, double ClIdealAvg, double Cpn, double CpnAvg, double Ns) {
		staCalculator = STACalculator.Ks;
		PostRatio rsPostRatio = new PostRatio();
		
		Rs = staCalculator.calculateRs(V, L, Cr, CrIdealAvg, Cl, ClIdealAvg, Cpn, CpnAvg, Ns);
		Ks = staCalculator.getValue();
		
		if(Rs > Ks) {
			isRsGreaterThanKs = true;
		}
		
		rsPostRatio.setRatio(Rs);
		rsPostRatio.setGreaterThan(isRsGreaterThanKs);
		
		return rsPostRatio;
	}
}