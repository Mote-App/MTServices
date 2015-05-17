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
 * Class User
 * <br/>
 * Represents an User for this web application.
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
  
 
  
  // ==============
  // PUBLIC METHODS
  // ==============
  
  	public User() { }

  	public User(Long profileId) { 
  		
  		this.profileId = profileId;
  	}

	public Long getProfileId() {
		return profileId;
	}



	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}



	public String getProfileFirstName() {
		return profileFirstName;
	}



	public void setProfileFirstName(String profileFirstName) {
		this.profileFirstName = profileFirstName;
	}



	public String getProfileLastName() {
		return profileLastName;
	}



	public void setProfileLastName(String profileLastName) {
		this.profileLastName = profileLastName;
	}



	public String getProfileUserName() {
		return profileUserName;
	}



	public void setProfileUserName(String profileUserName) {
		this.profileUserName = profileUserName;
	}



	public String getProfilePassword() {
		return profilePassword;
	}



	public void setProfilePassword(String profilePassword) {
		this.profilePassword = profilePassword;
	}



	public String getProfileEmail() {
		return profileEmail;
	}



	public void setProfileEmail(String profileEmail) {
		this.profileEmail = profileEmail;
	}



	public String getProfilePictureUrl() {
		return profilePictureUrl;
	}



	public void setProfilePictureUrl(String profilePictureUrl) {
		this.profilePictureUrl = profilePictureUrl;
	}



	public College getProfileCollege() {
		return profileCollege;
	}



	public void setProfileCollege(College profileCollege) {
		this.profileCollege = profileCollege;
	}


  
  
} // class User