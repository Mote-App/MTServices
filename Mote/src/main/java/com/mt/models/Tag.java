package com.mt.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cltag")
public class Tag {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="tag_type")
	private String tagType;
	
	@Column(name="sub_tag")
	private String subTag;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getTagType() {
		return tagType;
	}
	
	public void setTagType(String tagType) {
		this.tagType = tagType;
	}
	
	public String getSubTag() {
		return subTag;
	}
	
	public void setSubTag(String subTag) {
		this.subTag = subTag;
	}	
}