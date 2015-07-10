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
 * The <code>User</code> is the persistent class for the profile database table.
 * <br/>
 * Represents an User for this web application.
 * 
 * @author gibranecastillo
 *
 */
@Entity
@Table(name = "profile")
public class User {
	// ==============
	// PRIVATE FIELDS
	// ==============
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="profile_id")
	private Long profileId;
	
	@Column(name = "profile_first_name")
	private String profileFirstName;
	
	@Column(name = "profile_last_name")
	private String profileLastName;
	
	@Column(name = "profile_user_name")
	private String profileUserName;
	
	@Column(name = "profile_password")
	private String profilePassword;
	
	@Column(name = "profile_email")
	private String profileEmail;
	
	@Column(name = "profile_picture_url")
	private String profilePictureUrl;
	
	@OneToOne
	@JoinColumn(name="profile_college_id")
	private College profileCollege;
	
	@OneToOne
	@JoinColumn(name="locale_locale_id")
	private Locale locale;
	
	// ==============
	// PUBLIC METHODS
	// ==============
	
	/**
	 * 
	 */
	public User() { }
	
	/**
	 * 
	 * @param profileId
	 */
	public User(Long profileId) { 
		this.profileId = profileId;
	}
	
	/**
	 * 
	 * @return
	 */
	public Long getProfileId() {
		return profileId;
	}
	
	/**
	 * 
	 * @param profileId
	 */
	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getProfileFirstName() {
		return profileFirstName;
	}
	
	/**
	 * 
	 * @param profileFirstName
	 */
	public void setProfileFirstName(String profileFirstName) {
		this.profileFirstName = profileFirstName;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getProfileLastName() {
		return profileLastName;
	}
	
	/**
	 * 
	 * @param profileLastName
	 */
	public void setProfileLastName(String profileLastName) {
		this.profileLastName = profileLastName;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getProfileUserName() {
		return profileUserName;
	}
	
	/**
	 * 
	 * @param profileUserName
	 */
	public void setProfileUserName(String profileUserName) {
		this.profileUserName = profileUserName;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getProfilePassword() {
		return profilePassword;
	}
	
	/**
	 * 
	 * @param profilePassword
	 */
	public void setProfilePassword(String profilePassword) {
		this.profilePassword = profilePassword;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getProfileEmail() {
		return profileEmail;
	}
	
	/**
	 * 
	 * @param profileEmail
	 */
	public void setProfileEmail(String profileEmail) {
		this.profileEmail = profileEmail;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getProfilePictureUrl() {
		return profilePictureUrl;
	}
	
	/**
	 * 
	 * @param profilePictureUrl
	 */
	public void setProfilePictureUrl(String profilePictureUrl) {
		this.profilePictureUrl = profilePictureUrl;
	}
	
	/**
	 * 
	 * @return
	 */
	public College getProfileCollege() {
		return profileCollege;
	}
	
	/**
	 * 
	 * @param profileCollege
	 */
	public void setProfileCollege(College profileCollege) {
		this.profileCollege = profileCollege;
	}
	
	/**
	 * 
	 * @return
	 */
	public Locale getLocale() {
		return locale;
	}
	
	/**
	 * 
	 * @param locale
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
}