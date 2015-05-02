package com.mt.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author gibranecastillo
 *
 */
@Entity
@Table(name="college")
public class College {
	/*@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="college_id")
	private long collegeId;
	
	@Column(name="college_img_path")
	private String collegeImgPath;
	
	@Column(name="college_name")
	private String collegeName;
	
	/*
	 * Foreign Key fk_college_language1
	 * Referenced Table 'motedb'.'language'
	 * field college_language_code in college table references the language_code field in the language table
	 */
	@Column(name="college_language_code")
	private String collegeLanguageCode;
	
	/*
	 * Foreign Key fk_college_country1
	 * Referenced Table 'motedb'.'country'
	 * field college_country_code in college table references the country_code field in the country table
	 */
	@Column(name="college_country_code")
	private String collegeCountryCode;
	
	/**
	 * Constructs a College object.  Require by spring/hibernate.
	 */
	public College() { }
	
	/**
	 * 
	 * @param id
	 */
	/*public College(long id) { 
		this.id = id;
	}*/
	
	/**
	 * 
	 * @param collegeId
	 */
	public College(long collegeId) {
		this.collegeId = collegeId;
	}
	
	/*public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}*/
	
	/**
	 * 
	 * @return
	 */
	public long getCollegeId() {
		return collegeId;
	}
	
	/**
	 * 
	 * @param collegeId
	 */
	public void setCollegeId(long collegeId) {
		this.collegeId = collegeId;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCollegeImgPath() {
		return collegeImgPath;
	}
	
	/**
	 * 
	 * @param collegeImgPath
	 */
	public void setCollegeImgPath(String collegeImgPath) {
		this.collegeImgPath = collegeImgPath;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCollegeName() {
		return collegeName;
	}
	
	/**
	 * 
	 * @param collegeName
	 */
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCollegeLanguageCode() {
		return collegeLanguageCode;
	}
	
	/**
	 * 
	 * @param collegeLanguageCode
	 */
	public void setCollegeLanguageCode(String collegeLanguageCode) {
		this.collegeLanguageCode = collegeLanguageCode;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCollegeCountryCode() {
		return collegeCountryCode;
	}
	
	/**
	 * 
	 * @param collegeCountryCode
	 */
	public void setCollegeCountryCode(String collegeCountryCode) {
		this.collegeCountryCode = collegeCountryCode;
	}
}