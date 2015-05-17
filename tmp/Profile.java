package com.mt.models.tmp;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the profile database table.
 * 
 */
@Entity
@NamedQuery(name="Profile.findAll", query="SELECT p FROM Profile p")
public class Profile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="profile_id")
	private int profileId;

	@Column(name="profile_email")
	private String profileEmail;

	@Column(name="profile_first_name")
	private String profileFirstName;

	@Column(name="profile_last_name")
	private String profileLastName;

	@Column(name="profile_password")
	private String profilePassword;

	@Column(name="profile_picture_url")
	private String profilePictureUrl;

	@Column(name="profile_user_name")
	private String profileUserName;

	//bi-directional many-to-one association to College
	@ManyToOne
	@JoinColumn(name="profile_college_id")
	private College college;

	//bi-directional many-to-many association to Profile
	@ManyToMany
	@JoinTable(
		name="profile_has_friend"
		, joinColumns={
			@JoinColumn(name="profile_friend_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="profile_id")
			}
		)
	private List<Profile> profiles1;

	//bi-directional many-to-many association to Profile
	@ManyToMany(mappedBy="profiles1")
	private List<Profile> profiles2;

	//bi-directional many-to-one association to ProfileHasPost
	@OneToMany(mappedBy="profile")
	private List<ProfileHasPost> profileHasPosts;

	public Profile() {
	}

	public int getProfileId() {
		return this.profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public String getProfileEmail() {
		return this.profileEmail;
	}

	public void setProfileEmail(String profileEmail) {
		this.profileEmail = profileEmail;
	}

	public String getProfileFirstName() {
		return this.profileFirstName;
	}

	public void setProfileFirstName(String profileFirstName) {
		this.profileFirstName = profileFirstName;
	}

	public String getProfileLastName() {
		return this.profileLastName;
	}

	public void setProfileLastName(String profileLastName) {
		this.profileLastName = profileLastName;
	}

	public String getProfilePassword() {
		return this.profilePassword;
	}

	public void setProfilePassword(String profilePassword) {
		this.profilePassword = profilePassword;
	}

	public String getProfilePictureUrl() {
		return this.profilePictureUrl;
	}

	public void setProfilePictureUrl(String profilePictureUrl) {
		this.profilePictureUrl = profilePictureUrl;
	}

	public String getProfileUserName() {
		return this.profileUserName;
	}

	public void setProfileUserName(String profileUserName) {
		this.profileUserName = profileUserName;
	}

	public College getCollege() {
		return this.college;
	}

	public void setCollege(College college) {
		this.college = college;
	}

	public List<Profile> getProfiles1() {
		return this.profiles1;
	}

	public void setProfiles1(List<Profile> profiles1) {
		this.profiles1 = profiles1;
	}

	public List<Profile> getProfiles2() {
		return this.profiles2;
	}

	public void setProfiles2(List<Profile> profiles2) {
		this.profiles2 = profiles2;
	}

	public List<ProfileHasPost> getProfileHasPosts() {
		return this.profileHasPosts;
	}

	public void setProfileHasPosts(List<ProfileHasPost> profileHasPosts) {
		this.profileHasPosts = profileHasPosts;
	}

	public ProfileHasPost addProfileHasPost(ProfileHasPost profileHasPost) {
		getProfileHasPosts().add(profileHasPost);
		profileHasPost.setProfile(this);

		return profileHasPost;
	}

	public ProfileHasPost removeProfileHasPost(ProfileHasPost profileHasPost) {
		getProfileHasPosts().remove(profileHasPost);
		profileHasPost.setProfile(null);

		return profileHasPost;
	}

}