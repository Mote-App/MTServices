package com.cl.models;


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
@Table(name = "clprofile")
public class User {

  // ==============
  // PRIVATE FIELDS
  // ==============
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @Column(name = "first_name")
  private String firstName;
  
  @Column(name = "last_name")
  private String lastName;

  /*@Column(name = "middle_name")
  private String middleName;

  @Column(name = "birth_date")
  private Calendar birthDate;
  
  @Column(name = "email_address")
  private String emailAddress;
  
  @Column(name = "gender")
  private String gender;*/
    
  @Column(name = "user_name")
  private String userName;
  
  @Column(name = "password")
  private String password;
  
  @Column(name = "email")
  private String email;
  /*@Column(name = "cover_picture_url")
  private String coverPictureUrl;
  
  @Column(name = "is_alumni")
  private String isAlumni;
  */
  /*@Column(name = "graduation_year")
  private String graduationYear;
  
  @Column(name = "about_me")
  private String aboutMe;
  
  @Column(name = "activities")
  private String activities;
  
  @Column(name = "interest")
  private String interest;
  */
  @Column(name = "profile_picture_url")
  private String profilePictureUrl;
  
  /*@Column(name = "religion")
  private String religion;
  */
  @OneToOne
  @JoinColumn(name="college_id")
  private College college;
  
 
  
  // ==============
  // PUBLIC METHODS
  // ==============
  
  	public User() { }

  	public User(long id) { 
  		this.id = id;
  	}

  	public Long getId() {
  		return id;
  	}

  	public void setId(Long value) {
  		this.id = value;
  	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/*public String getMiddleName() {
		return middleName;
	}
	
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public Calendar getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Calendar birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}*/
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	/*public String getCoverPictureUrl() {
		return coverPictureUrl;
	}
	
	public void setCoverPictureUrl(String coverPictureUrl) {
		this.coverPictureUrl = coverPictureUrl;
	}
	
	public String getIsAlumni() {
		return isAlumni;
	}
	
	public void setIsAlumni(String isAlumni) {
		this.isAlumni = isAlumni;
	}*/
	
	/*public String getGraduationYear() {
		return graduationYear;
	}
	
	public void setGraduationYear(String graduationYear) {
		this.graduationYear = graduationYear;
	}
	
	public String getAboutMe() {
		return aboutMe;
	}
	
	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}
	
	public String getActivities() {
		return activities;
	}
	
	public void setActivities(String activities) {
		this.activities = activities;
	}
	
	public String getInterest() {
		return interest;
	}
	
	public void setInterest(String interest) {
		this.interest = interest;
	}*/
	
	public String getProfilePictureUrl() {
		return profilePictureUrl;
	}
	
	public void setProfilePictureUrl(String profilePictureUrl) {
		this.profilePictureUrl = profilePictureUrl;
	}
	
	/*public String getReligion() {
		return religion;
	}
	
	public void setReligion(String religion) {
		this.religion = religion;
	}*/
	
	public College getCollege() {
		return college;
	}
	
	public void setCollege(College college) {
		this.college = college;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


  
  
} // class User