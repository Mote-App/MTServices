package com.mt.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the country database table.
 * 
 */
@Entity
@NamedQuery(name="Country.findAll", query="SELECT c FROM Country c")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="country_code")
	private String countryCode;

	@Column(name="country_name")
	private String countryName;

	//bi-directional many-to-one association to College
	@OneToMany(mappedBy="country")
	private List<College> colleges;

	//bi-directional many-to-many association to Language
	@ManyToMany(mappedBy="countries")
	private List<Language> languages;
	
	public Country() {
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return this.countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public List<College> getColleges() {
		return this.colleges;
	}

	public void setColleges(List<College> colleges) {
		this.colleges = colleges;
	}

	public College addCollege(College college) {
		getColleges().add(college);
		college.setCountry(this);

		return college;
	}

	public College removeCollege(College college) {
		getColleges().remove(college);
		college.setCountry(null);

		return college;
	}

	public List<Language> getLanguages() {
		return this.languages;
	}

	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}

}