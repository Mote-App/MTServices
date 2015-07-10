package com.mt.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The <code>Locale</code> is the persistent class for the locale database table.
 * 
 * @author gibranecastillo
 *
 */
@Entity
@Table(name="locale")
public class Locale {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="locale_id")
	Long localeId;
	
	@OneToOne
	@JoinColumn(name="country_code")
	Country country;
	
	@OneToOne
	@JoinColumn(name="language_code")
	Language language;
	
	/**
	 * 
	 */
	public Locale() { }
	
	/**
	 * 
	 * @return
	 */
	public Long getLocaleId() {
		return localeId;
	}
	
	/**
	 * 
	 * @param localeId
	 */
	public void setLocaleId(Long localeId) {
		this.localeId = localeId;
	}
	
	/**
	 * 
	 * @return
	 */
	public Country getCountry() {
		return country;
	}
	
	/**
	 * 
	 * @param country
	 */
	public void setCountry(Country country) {
		this.country = country;
	}
	
	/**
	 * 
	 * @return
	 */
	public Language getLanguage() {
		return language;
	}
	
	/**
	 * 
	 * @param language
	 */
	public void setLanguage(Language language) {
		this.language = language;
	}	
}