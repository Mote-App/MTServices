package com.mt.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The <code>Post</code> is the persistent class for the post database table.
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
	
	@Column(name="post_views")
	private long views;
	
	@Column(name="post_likes")
	private long likes;
	
	@Column(name="post_school_promote")
	private boolean postSchoolPromote;
	
	@Column(name="post_national_promote")
	private boolean postNationalPromote;
	
	@OneToMany
	@JoinColumn(name="post_post_id")
	private List<PostTags> listPostTags;
	
	/**
	 * 
	 */
	public Post() {
		listPostTags = new ArrayList<PostTags>();
	}
	
	/**
	 * 
	 * @return
	 */
	public long getPostId() {
		return postId;
	}
	
	/**
	 * 
	 * @param postId
	 */
	public void setPostId(long postId) {
		this.postId = postId;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getPostTypeCode() {
		return postTypeCode;
	}
	
	/**
	 * 
	 * @param postTypeCode
	 */
	public void setPostTypeCode(String postTypeCode) {
		this.postTypeCode = postTypeCode;
	}
	
	/**
	 * 
	 * @return
	 */
	public long getProfileId() {
		return profileId;
	}
	
	/**
	 * 
	 * @param profileId
	 */
	public void setProfileId(long profileId) {
		this.profileId = profileId;
	}
	
	/*public long getPostTagId() {
		return postTagId;
	}
	
	public void setPostTagId(long postTagId) {
		this.postTagId = postTagId;
	}*/
	
	/**
	 * 
	 * @return
	 */
	public String getPostObjectPath() {
		return postObjectPath;
	}
	
	/**
	 * 
	 * @param postObjectPath
	 */
	public void setPostObjectPath(String postObjectPath) {
		this.postObjectPath = postObjectPath;
	}
	
	/**
	 * 
	 * @return
	 */
	public Calendar getPostDate() {
		return postDate;
	}
	
	/**
	 * 
	 * @param postDate
	 */
	public void setPostDate(Calendar postDate) {
		this.postDate = postDate;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getPostCaption() {
		return postCaption;
	}
	
	/**
	 * 
	 * @param postCaption
	 */
	public void setPostCaption(String postCaption) {
		this.postCaption = postCaption;
	}
	
	/**
	 * 
	 * @return
	 */
	public long getViews() {
		return views;
	}
	
	/**
	 * 
	 * @param views
	 */
	public void setViews(long views) {
		this.views = views;
	}
	
	/**
	 * 
	 * @return
	 */
	public long getLikes() {
		return likes;
	}
	
	/**
	 * 
	 * @param likes
	 */
	public void setLikes(long likes) {
		this.likes = likes;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isPostSchoolPromote() {
		return postSchoolPromote;
	}
	
	/**
	 * 
	 * @param postSchoolPromote
	 */
	public void setPostSchoolPromote(boolean postSchoolPromote) {
		this.postSchoolPromote = postSchoolPromote;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isPostNationalPromote() {
		return postNationalPromote;
	}
	
	/**
	 * 
	 * @param postNationalPromote
	 */
	public void setPostNationalPromote(boolean postNationalPromote) {
		this.postNationalPromote = postNationalPromote;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<PostTags> getListPostTags() {
		return listPostTags;
	}
	
	/**
	 * 
	 * @param listPostTags
	 */
	public void setListPostTags(List<PostTags> listPostTags) {
		this.listPostTags = listPostTags;
	}
	
	/**
	 * 
	 */
	@Override
	public boolean equals (Object o){	
		if(o == null) return false;
		
		if(!(o instanceof Post)) return false;
		
		Post other = (Post)o;
		
		if(this.getProfileId() != other.getProfileId()) return false;
		
		return true;
	}
}