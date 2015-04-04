package com.mt.algorithm.ssa;

/**
 * 
 * @author gibranecastillo
 *
 */
public class SSAPostRatio {
	private double ratio;
	private boolean isGreaterThan;
	
	/**
	 * 
	 * @return
	 */
	public double getRatio() {
		return ratio;
	}
	
	/**
	 * 
	 * @param ratio
	 */
	public void setRatio(double ratio) {
		this.ratio = ratio;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isGreaterThan() {
		return isGreaterThan;
	}
	
	/**
	 * 
	 * @param isGreaterThan
	 */
	public void setGreaterThan(boolean isGreaterThan) {
		this.isGreaterThan = isGreaterThan;
	}
}