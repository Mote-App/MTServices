package com.mt.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The <code>Language</code> is the persistent class for the language database table.
 * 
 * @author gibranecastillo
 *
 */
@Entity
@Table(name="language")
public class Language implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="language_code")
	private String languageCode;
	
	@Column(name="language_description")
	private String languageDescription;
	
	/**
	 * 
	 */
	public Language() { }
	
	/**
	 * 
	 * @return
	 */
	public String getLanguageCode() {
		return this.languageCode;
	}
	
	/**
	 * 
	 * @param languageCode
	 */
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getLanguageDescription() {
		return this.languageDescription;
	}
	
	/**
	 * 
	 * @param languageDescription
	 */
	public void setLanguageDescription(String languageDescription) {
		this.languageDescription = languageDescription;
	}
}