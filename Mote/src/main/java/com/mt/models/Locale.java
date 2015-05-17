package com.mt.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="locale")
public class Locale {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="locale_id")
	Long localeId;
	
	@OneToOne
	@JoinColumn(name="country_code")
	Country country;
	
	@OneToOne
	@JoinColumn(name="language_code")
	Language language;

	public Locale(){
		
	}
	
	public Long getLocaleId() {
		return localeId;
	}

	public void setLocaleId(Long localeId) {
		this.localeId = localeId;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}
	
	
	
}
