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
	 * Allows you to get some meaningful representation of the User.java object.
	 * This is the "spill-your-guts method," because it simply spits out the object's
	 * state; in other words, the current values of the important instance variables.
	 *  
	 * @return a "text representation" of the StringBuilder object that invoked the method call as a String.
	 */
	@Override
	public String toString() {
		/*
		 * The StringBuilder class (added in Java 1.5) has exactly the same API as
		 * the StringBuffer class, except StringBuilder is not thread safe (methods are not synchronized);
		 * therefore, StringBuilder will run faster.
		 */
		StringBuilder sb = new StringBuilder();
		sb.append("\n* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		sb.append("\n*                                           Mote User toString()                                             ");
		sb.append("\n* User.java -> The persistent class for the profile database table. @Entity and @Table(name=\"profile\")");
		sb.append("\n* Long profileId -> [@Id, @GeneratedValue(strategy = GenerationType.AUTO) and @Column(name=\"profile_id\")]: " + this.profileId);
		sb.append("\n* String profileFirstName -> [@Column(name = \"profile_first_name\")]: " + this.profileFirstName);
		sb.append("\n* String profileLastName -> [@Column(name = \"profile_last_name\")]: " + this.profileLastName);
		sb.append("\n* String profileUserName -> [@Column(name = \"profile_user_name\")]: " + this.profileUserName);
		sb.append("\n* String profilePassword -> [@Column(name = \"profile_password\")]: " + this.profilePassword);
		sb.append("\n* String profileEmail -> [@Column(name = \"profile_email\")]: " + this.profileEmail);
		sb.append("\n* String profilePictureUrl -> [@Column(name = \"profile_picture_url\")]: " + this.profilePictureUrl);
		sb.append("\n* College profileCollege -> [@OneToOne and @Join@Column(name = \"profile_college_id\")]: " + this.profileCollege);
		sb.append("\n* Locale locale -> [@OneToOne and @Join@Column(name = \"locale_locale_id\")]: " + this.locale);
		sb.append("\n* List<Aggregation> aggregationList -> [@OneToMany and @Join@Column(name = \"profile_id\")]: " + this.aggregationList);
		sb.append("\n* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		return sb.toString();
	}
}