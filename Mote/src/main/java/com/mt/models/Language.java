package com.mt.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the language database table.
 * 
 */
@Entity
@NamedQuery(name="Language.findAll", query="SELECT l FROM Language l")
public class Language implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="language_code")
	private String languageCode;

	@Column(name="language_description")
	private String languageDescription;

	//bi-directional many-to-one association to College
	@OneToMany(mappedBy="language")
	private List<College> colleges;

	//bi-directional many-to-many association to Country
	@ManyToMany
	@JoinTable(
		name="locale"
		, joinColumns={
			@JoinColumn(name="locale_language_code")
			}
		, inverseJoinColumns={
			@JoinColumn(name="locale_country_code")
			}
		)
	private List<Country> countries;

	public Language() {
	}

	public String getLanguageCode() {
		return this.languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getLanguageDescription() {
		return this.languageDescription;
	}

	public void setLanguageDescription(String languageDescription) {
		this.languageDescription = languageDescription;
	}

	public List<College> getColleges() {
		return this.colleges;
	}

	public void setColleges(List<College> colleges) {
		this.colleges = colleges;
	}

	public College addCollege(College college) {
		getColleges().add(college);
		college.setLanguage(this);

		return college;
	}

	public College removeCollege(College college) {
		getColleges().remove(college);
		college.setLanguage(null);

		return college;
	}

	public List<Country> getCountries() {
		return this.countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

}