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

/**
 * 
 * @author gibranecastillo
 *
 */
@Entity
@Table(name="post")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="post_id")
	private long postId;
	
	@Column(name="post_type_code")
	private String postTypeCode;
	
	@Column(name="post_profile_id")
	private long profileId;

	//@Column(name="post_tag_id")
	//private long postTagId;
	
	
	@Column(name="post_object_path")
	private String postObjectPath;

	@Column(name="post_date")
	private Calendar postDate;

	@Column(name="post_caption")
	private String postCaption;

	@Column(name="views")
	private long views;

	@Column(name="likes")
	private long likes;

	@Column(name="post_school_promote")
	private boolean postSchoolPromote;

	@Column(name="post_national_promote")
	private boolean postNationalPromote;

	@OneToMany
	@JoinColumn(name="post_post_id")
	private List<PostTags> listPostTags;


	public Post(){
		listPostTags = new ArrayList<PostTags>();
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}
	
	public String getPostTypeCode() {
		return postTypeCode;
	}

	public void setPostTypeCode(String postTypeCode) {
		this.postTypeCode = postTypeCode;
	}

	public long getProfileId() {
		return profileId;
	}

	public void setProfileId(long profileId) {
		this.profileId = profileId;
	}

	/*public long getPostTagId() {
		return postTagId;
	}

	public void setPostTagId(long postTagId) {
		this.postTagId = postTagId;
	}*/

	public String getPostObjectPath() {
		return postObjectPath;
	}

	public void setPostObjectPath(String postObjectPath) {
		this.postObjectPath = postObjectPath;
	}

	public Calendar getPostDate() {
		return postDate;
	}

	public void setPostDate(Calendar postDate) {
		this.postDate = postDate;
	}

	public String getPostCaption() {
		return postCaption;
	}

	public void setPostCaption(String postCaption) {
		this.postCaption = postCaption;
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

	public boolean isPostSchoolPromote() {
		return postSchoolPromote;
	}

	public void setPostSchoolPromote(boolean postSchoolPromote) {
		this.postSchoolPromote = postSchoolPromote;
	}

	public boolean isPostNationalPromote() {
		return postNationalPromote;
	}

	public void setPostNationalPromote(boolean postNationalPromote) {
		this.postNationalPromote = postNationalPromote;
	}

	public List<PostTags> getListPostTags() {
		return listPostTags;
	}
	
	public void setListPostTags(List<PostTags> listPostTags) {
		this.listPostTags = listPostTags;
	}




	@Override
	public boolean equals (Object o){	
		if( o == null) return false;

		if(!(o instanceof Post )) return false;

		Post other = (Post)o;

		if( this.getProfileId() != other.getProfileId()) return false;

		return true;
	}	
}