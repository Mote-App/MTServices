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
	
	@Column(name="post_object_path")
	private String postObjectPath;
	
	@Column(name="post_date")
	private Calendar postDate;
	
	@Column(name="post_caption")
	private String postCaption;
	
	@Column(name="post_school_promote")
	private int postSchoolPromote;
	
	@Column(name="post_national_promote")
	private int postNationalPromote;
	
	@OneToMany
	@JoinColumn(name="post_post_id")
	private List<PostTags> listPostTags;
	
	@OneToOne
	@JoinColumn(name="source_objects_source_objects_id")
	private AggregationSourceObject aggregationSourceObject;
	
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
	public int getPostSchoolPromote() {
		return postSchoolPromote;
	}
	
	/**
	 * 
	 * @param postSchoolPromote
	 */
	public void setPostSchoolPromote(int postSchoolPromote) {
		this.postSchoolPromote = postSchoolPromote;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getPostNationalPromote() {
		return postNationalPromote;
	}
	
	/**
	 * 
	 * @param postNationalPromote
	 */
	public void setPostNationalPromote(int postNationalPromote) {
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
	
	public AggregationSourceObject getAggregationSourceObject() {
		return aggregationSourceObject;
	}

	public void setAggregationSourceObject(
			AggregationSourceObject aggregationSourceObject) {
		this.aggregationSourceObject = aggregationSourceObject;
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
	
	/**
	 * Allows you to get some meaningful representation of the Aggregation.java object.
	 * This is the "spill-your-guts method," because it simply spits out the object's
	 * state; in other words, the current values of the important instance variables.
	 *  
	 * @return a "text representation" of the StringBuilder object that invoked the method call as a String.
	 */
	@Override
	public String toString() {
		/*
		 * The StringBuilder class (added in Java 1.5) has exactly the same API as
		 * the StringBuffer class, except StringBuilder is not thread safe (methods are not synchronized);
		 * therefore, StringBuilder will run faster.
		 */
		StringBuilder sb = new StringBuilder();
		sb.append("\n* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		sb.append("\n*                                          Mote Post toString()                                              ");
		sb.append("\n* Post.java -> The persistent class for the post database table. @Entity and @Table(name=\"post\")");
		sb.append("\n* long postId -> [@Id, @GeneratedValue(strategy = GenerationType.AUTO) and Column(name=\"post_id\")]: " + this.postId);
		sb.append("\n* String postTypeCode -> Mote uid or anonymour or public [@Column(name=\"post_type_code\")]: " + this.postTypeCode);
		sb.append("\n* User profile -> Mote's User object [@OneToOne and @Join@Column(name=\"post_profile_id\")]: " + this.profile);
		sb.append("\n* String postObjectPath -> Mote's media (photos and videos) URL in CDN [@Column(name=\"post_object_path\")]: " + this.postObjectPath);
		sb.append("\n* Calendar postDate -> [@Column(name=\"post_date\")]: " + this.postDate);
		sb.append("\n* String postCaption -> [@Column(name=\"post_caption\")]: " + this.postCaption);
		sb.append("\n* int postSchoolPromote -> ** deprecated ** [@Column(name=\"post_school_promote\")]: " + this.postSchoolPromote);
		sb.append("\n* int postNationalPromote -> ** deprecated ** [@Column(name=\"post_national_promote\")]: " + this.postNationalPromote);
		sb.append("\n* List<PostTags> listPostTags -> [@OneToMany and @JoinColumn(name=\"post_post_id\")]: " + this.listPostTags);
		sb.append("\n* AggregationSourceObject aggregationSourceObject -> [@OneToOne and @Join@Column(name=\"source_objects_source_objects_id\")]: " + this.aggregationSourceObject);
		sb.append("\n* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		return sb.toString();
	}
}