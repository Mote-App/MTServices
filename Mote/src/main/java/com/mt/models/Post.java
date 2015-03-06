package com.mt.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="clpost")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name="user_id")
	private long userId;
	
	@Column(name="post_image_path")
	private String postImgPath;

	@Column(name="post_date")
	private Calendar postDate;
	
	@Column(name="caption")
	private String caption;

	@Column(name="likes")
	private long likes;

	@OneToMany
	@JoinColumn(name="post_id")
	private List<PostTags> lstPostTags;
	
	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="post_id")
	private List<PostCustomTags> lstPostCustomTags;
	
	public Post(){
		lstPostTags = new ArrayList<PostTags>();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public List<PostTags> getLstPostTags() {
		return lstPostTags;
	}

	public void setLstPostTags(List<PostTags> lstPostTags) {
		this.lstPostTags = lstPostTags;
	}

	public String getPostImgPath() {
		return postImgPath;
	}

	public void setPostImgPath(String postImgPath) {
		this.postImgPath = postImgPath;
	}

	public Calendar getPostDate() {
		return postDate;
	}

	public void setPostDate(Calendar postDate) {
		this.postDate = postDate;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public long getLikes() {
		return likes;
	}

	public void setLikes(long likes) {
		this.likes = likes;
	}
	
	public List<PostCustomTags> getLstPostCustomTags() {
		return lstPostCustomTags;
	}

	public void setLstPostCustomTags(List<PostCustomTags> lstPostCustomTags) {
		this.lstPostCustomTags = lstPostCustomTags;
	}

	@Override
	public boolean equals (Object o){
		
		if( o == null) return false;
		
		if(!(o instanceof Post )) return false;
		
		Post other = (Post)o;
		
		if( this.getUserId() != other.getUserId()) return false;
		
		return true;
	}
	
	
}
