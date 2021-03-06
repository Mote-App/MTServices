package com.mt.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The <code>SSAParams</code> is the persistent class for the ssa_coefficient_parameters database table.
 * 
 * @author gibranecastillo
 *
 */
@Entity
@Table(name="ssa_coefficient_parameters")
public class SSAParams {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private long id;
	
	// Friend Feed to School Feed Threshold Ratio.
	@Column(name="Kf")
	private double Kf;
	
	// School Feed to National Feed Threshold Ratio.
	@Column(name="Ks")
	private double Ks;
	
	// Preliminary Post Coefficient.
	@Column(name="Cf")
	private double Cf;
	
	// Ideal Number of posts in School feed.
	@Column(name="ns_Ideal")
	private int NsIdeal;
	
	// Ideal Number of posts in National feed.
	@Column(name="nn_Ideal")
	private int NnIdeal;
	
	// T1 Tuning Coefficient.
	@Column(name="T1")
	private double T1;
	
	// T2 Tuning Coefficient.
	@Column(name="T2")
	private double T2;
	
	// T3 Tuning Coefficient.
	@Column(name="T3")
	private double T3;
	
	// T4 Tuning Coefficient.
	@Column(name="T4")
	private double T4;
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * @return the kf
	 */
	public double getKf() {
		return Kf;
	}
	
	/**
	 * @param kf the kf to set
	 */
	public void setKf(double kf) {
		Kf = kf;
	}
	
	/**
	 * @return the ks
	 */
	public double getKs() {
		return Ks;
	}
	
	/**
	 * @param ks the ks to set
	 */
	public void setKs(double ks) {
		Ks = ks;
	}
	
	/**
	 * @return the cf
	 */
	public double getCf() {
		return Cf;
	}
	
	/**
	 * @param cf the cf to set
	 */
	public void setCf(double cf) {
		Cf = cf;
	}
	
	/**
	 * @return the nsIdeal
	 */
	public int getNsIdeal() {
		return NsIdeal;
	}
	
	/**
	 * @param nsIdeal the nsIdeal to set
	 */
	public void setNsIdeal(int nsIdeal) {
		NsIdeal = nsIdeal;
	}
	
	/**
	 * @return the nnIdeal
	 */
	public int getNnIdeal() {
		return NnIdeal;
	}
	
	/**
	 * @param nnIdeal the nnIdeal to set
	 */
	public void setNnIdeal(int nnIdeal) {
		NnIdeal = nnIdeal;
	}
	
	/**
	 * @return the t1
	 */
	public double getT1() {
		return T1;
	}
	
	/**
	 * @param t1 the t1 to set
	 */
	public void setT1(double t1) {
		T1 = t1;
	}
	
	/**
	 * @return the t2
	 */
	public double getT2() {
		return T2;
	}
	
	/**
	 * @param t2 the t2 to set
	 */
	public void setT2(double t2) {
		T2 = t2;
	}
	
	/**
	 * @return the t3
	 */
	public double getT3() {
		return T3;
	}
	
	/**
	 * @param t3 the t3 to set
	 */
	public void setT3(double t3) {
		T3 = t3;
	}
	
	/**
	 * @return the t4
	 */
	public double getT4() {
		return T4;
	}
	
	/**
	 * @param t4 the t4 to set
	 */
	public void setT4(double t4) {
		T4 = t4;
	}
}