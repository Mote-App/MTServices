package com.mt.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The <code>Tag</code> is the persistent class for the tag database table.
 * 
 * @author gibranecastillo
 *
 */
@Entity
@Table(name="tag")
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="tag_id")
	private long tagId;
	
	@Column(name="tag_description")
	private String tagDescription;
	
	/**
	 * 
	 * @return
	 */
	public long getTagId() {
		return tagId;
	}
	
	/**
	 * 
	 * @param tagId
	 */
	public void setTagId(long tagId) {
		this.tagId = tagId;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getTagDescription() {
		return tagDescription;
	}
	
	/**
	 * 
	 * @param tagDescription
	 */
	public void setTagDescription(String tagDescription) {
		this.tagDescription = tagDescription;
	}	
}