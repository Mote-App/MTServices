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
	
	@Column(name="views")
	private long views;
	
	@Column(name="likes")
	private long likes;
	
	@Column(name="school_promote")
	private boolean isSchoolFeedPost;
	
	@Column(name="national_promote")
	private boolean isNationalFeedPost;
	
	@OneToOne
	@JoinColumn(name="college_id")
	private College college;
	
	@OneToMany
	@JoinColumn(name="post_id")
	private List<PostTags> listPostTags;
	
	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="post_id")
	private List<PostCustomTags> listPostCustomTags;
	
	public Post(){
		listPostTags = new ArrayList<PostTags>();
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
	
	public long getViews() {
		return views;
	}
	
	public void setViews(long views) {
		this.views = views;
	}
	
	public long getLikes() {
		return likes;
	}
	
	public void setLikes(long likes) {
		this.likes = likes;
	}
	
	public boolean isSchoolFeedPost() {
		return isSchoolFeedPost;
	}
	
	public void setSchoolFeedPost(boolean isSchoolFeedPost) {
		this.isSchoolFeedPost = isSchoolFeedPost;
	}
	
	public boolean isNationalFeedPost() {
		return isNationalFeedPost;
	}
	
	public void setNationalFeedPost(boolean isNationalFeedPost) {
		this.isNationalFeedPost = isNationalFeedPost;
	}
	
	public College getCollege() {
		return college;
	}
	
	public void setCollege(College college) {
		this.college = college;
	}
	
	public List<PostTags> getListPostTags() {
		return listPostTags;
	}
	
	public void setListPostTags(List<PostTags> lstPostTags) {
		this.listPostTags = lstPostTags;
	}
	
	public List<PostCustomTags> getListPostCustomTags() {
		return listPostCustomTags;
	}
	
	public void setListPostCustomTags(List<PostCustomTags> lstPostCustomTags) {
		this.listPostCustomTags = lstPostCustomTags;
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