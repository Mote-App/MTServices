package com.mt.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
	
	@OneToMany
	@JoinColumn(name="profile_id")
	private List<Aggregation> aggregationList;
	
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
	
	/**
	 * @return the aggregationList
	 */
	public List<Aggregation> getAggregationList() {
		return aggregationList;
	}
	
	/**
	 * @param aggregationList the aggregationList to set
	 */
	public void setAggregationList(List<Aggregation> aggregationList) {
		this.aggregationList = aggregationList;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("\n profile_id: " + this.profileId);
		sb.append("\n profile_first_name: " + this.profileFirstName);
		sb.append("\n profile_last_name: " + this.profileLastName);
		sb.append("\n profile_user_name: " + this.profileUserName);
		sb.append("\n profile_password: " + this.profilePassword);
		sb.append("\n profile_email: " + this.profileEmail);
		sb.append("\n profile_picture_url: " + this.profilePictureUrl);
		sb.append("\n profile_college_id: " + this.profileCollege);
		sb.append("\n locale_locale_id: " + this.locale);
		
		return sb.toString();
	}
}