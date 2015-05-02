package com.mt.models.tmp;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the college database table.
 * 
 */
@Entity
@NamedQuery(name="College.findAll", query="SELECT c FROM College c")
public class College implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="college_id")
	private int collegeId;

	@Column(name="college_img_path")
	private String collegeImgPath;

	@Column(name="college_name")
	private String collegeName;

	//bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name="college_country_code")
	private Country country;

	//bi-directional many-to-one association to Language
	@ManyToOne
	@JoinColumn(name="college_language_code")
	private Language language;

	//bi-directional many-to-one association to Profile
	@OneToMany(mappedBy="college")
	private List<Profile> profiles;

	public College() {
	}

	public int getCollegeId() {
		return this.collegeId;
	}

	public void setCollegeId(int collegeId) {
		this.collegeId = collegeId;
	}

	public String getCollegeImgPath() {
		return this.collegeImgPath;
	}

	public void setCollegeImgPath(String collegeImgPath) {
		this.collegeImgPath = collegeImgPath;
	}

	public String getCollegeName() {
		return this.collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Language getLanguage() {
		return this.language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public List<Profile> getProfiles() {
		return this.profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}

	public Profile addProfile(Profile profile) {
		getProfiles().add(profile);
		profile.setCollege(this);

		return profile;
	}

	public Profile removeProfile(Profile profile) {
		getProfiles().remove(profile);
		profile.setCollege(null);

		return profile;
	}

}