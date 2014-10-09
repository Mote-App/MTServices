package com.cl.feeds.pojos;

public class Post {
	private long postCount;
	private long postId;
	private String postingDate; //may change later on to a Date type
	private String postImg;
	private String[] tags;
	private long likesCount;
	private String caption;
	private long schoolId;
	private String schoolName;
	private String schoolImg;
	
	/**
	 * Explicit basic default constructor with no arguments.
	 */
	public Post() {
		// do nothing
	}
	
	public Post(long postCount) {
		this.postCount = postCount;
	}
	
	public long getPostCount() {
		return postCount;
	}
	
	public long getPostId() {
		return postId;
	}
	
	public void setPostId(long postId) {
		this.postId = postId;
	}
	
	public String getPostingDate() {
		return postingDate;
	}
	
	public void setPostingDate(String postingDate) {
		this.postingDate = postingDate;
	}
	
	public String getPostImg() {
		return postImg;
	}
	
	public void setPostImg(String postImg) {
		this.postImg = postImg;
	}
	
	public String[] getTags() {
		return tags;
	}
	
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	
	public long getLikesCount() {
		return likesCount;
	}
	
	public void setLikesCount(long likesCount) {
		this.likesCount = likesCount;
	}
	
	public String getCaption() {
		return caption;
	}
	
	public void setCaption(String caption) {
		this.caption = caption;
	}
	
	public long getSchoolId() {
		return schoolId;
	}
	
	public void setSchoolId(long schoolId) {
		this.schoolId = schoolId;
	}
	
	public String getSchoolName() {
		return schoolName;
	}
	
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	
	public String getSchoolImg() {
		return schoolImg;
	}
	
	public void setSchoolImg(String schoolImg) {
		this.schoolImg = schoolImg;
	}
}