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
import javax.persistence.OneToOne;
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
	
	@OneToOne
	@JoinColumn(name="post_profile_id")
	private User profile;
	
	//@Column(name="post_tag_id")
	//private long postTagId;
	
	@Column(name="post_object_path")
	private String postObjectPath;
	
	@Column(name="post_date")
	private Calendar postDate;
	
	@Column(name="post_caption")
	private String postCaption;
	
	@Column(name="post_school_promote")
	private byte postSchoolPromote;
	
	@Column(name="post_national_promote")
	private byte postNationalPromote;
	
	@OneToMany
	@JoinColumn(name="post_post_id")
	private List<PostTags> listPostTags;
	
	/**
	 * 
	 */
	public Post() {
		listPostTags = new ArrayList<PostTags>();
		profile = new User();
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
	
	
	/*public long getPostTagId() {
		return postTagId;
	}
	
	public void setPostTagId(long postTagId) {
		this.postTagId = postTagId;
	}*/
	
	public User getProfile() {
		return profile;
	}

	public void setProfile(User profile) {
		this.profile = profile;
	}

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
	public byte isPostSchoolPromote() {
		return postSchoolPromote;
	}
	
	/**
	 * 
	 * @param postSchoolPromote
	 */
	public void setPostSchoolPromote(byte postSchoolPromote) {
		this.postSchoolPromote = postSchoolPromote;
	}
	
	/**
	 * 
	 * @return
	 */
	public byte isPostNationalPromote() {
		return postNationalPromote;
	}
	
	/**
	 * 
	 * @param postNationalPromote
	 */
	public void setPostNationalPromote(byte postNationalPromote) {
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
		
		if(this.getProfile().getProfileId() != other.getProfile().getProfileId()) return false;
		
		return true;
	}
}