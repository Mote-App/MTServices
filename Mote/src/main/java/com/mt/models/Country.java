package com.mt.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The <code>Country</code> is the persistent class for the country database table.
 * 
 * @author gibranecastillo
 *
 */
@Entity
@Table(name="country")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="country_code")
	private String countryCode;
	
	@Column(name="country_name")
	private String countryName;
	
	/**
	 * 
	 */
	public Country() { }
	
	/**
	 * 
	 * @return
	 */
	public String getCountryCode() {
		return this.countryCode;
	}
	
	/**
	 * 
	 * @param countryCode
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCountryName() {
		return this.countryName;
	}
	
	/**
	 * 
	 * @param countryName
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
}