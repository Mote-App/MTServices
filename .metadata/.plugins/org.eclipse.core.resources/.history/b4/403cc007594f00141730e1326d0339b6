package com.cl.feeds.pojos;

import rain.Recording;

public abstract class Friend extends User implements Comparable {
	private static final long serialVersionUID = 1L;
	
	private long loggedUserId;
	private long userId;
	private String userType;
	private String name;
	private String facebookName;
	private String profileImg;
	private long schoolId;
	private String schoolName;
	private String schoolImg;
	
	/**
	 * Explicit basic default constructor with no arguments.
	 */
	public Friend() {
		// do nothing
	}
	
	/**
	 *  Returns the friend post.  Subclasses must override this method to return the post type instance [popular or current or most recent].
	 */
	public abstract Post getPost();
	//public abstract void setPost(Post post);
	
	public long getLoggedUserId() {
		return loggedUserId;
	}
	
	public void setLoggedUserId(long loggedUserId) {
		this.loggedUserId = loggedUserId;
	}
	
	public long getUserId() {
		return userId;
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public String getUserType() {
		return userType;
	}
	
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getFacebookName() {
		return facebookName;
	}
	
	public void setFacebookName(String facebookName) {
		this.facebookName = facebookName;
	}
	
	public String getProfileImg() {
		return profileImg;
	}
	
	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
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
	
	
	/**
	 *  Allow us to sort the recordings by title
	 */
	public int compareTo(Object object) {
		Friend friend = (Friend) object;
		String targetTitle = friend.getTitle();

		return title.compareTo(targetTitle);
	}
}