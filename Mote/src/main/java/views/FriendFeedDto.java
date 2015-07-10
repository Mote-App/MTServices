package views;

/**
 * The <code>FriendFeedDto</code> ...
 * 
 * @author gibranecastillo
 *
 */
public class FriendFeedDto {
	long 	 userId;
	String 	 userType;
	String 	 name;
	String 	 facebookName;
	String 	 profileImg;
	long 	 schoolId;
	String 	 SchoolName;
	String 	 SchoolImg;
	PostsDto posts;
	
	/**
	 * 
	 */
	public FriendFeedDto() {
		posts = new PostsDto();
	}
	
	/**
	 * 
	 * @return
	 */
	public long getUserId() {
		return userId;
	}
	
	/**
	 * 
	 * @param userId
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getUserType() {
		return userType;
	}
	
	/**
	 * 
	 * @param userType
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getFacebookName() {
		return facebookName;
	}
	
	/**
	 * 
	 * @param facebookName
	 */
	public void setFacebookName(String facebookName) {
		this.facebookName = facebookName;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getProfileImg() {
		return profileImg;
	}
	
	/**
	 * 
	 * @param profileImg
	 */
	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}
	
	/**
	 * 
	 * @return
	 */
	public long getSchoolId() {
		return schoolId;
	}
	
	/**
	 * 
	 * @param schoolId
	 */
	public void setSchoolId(long schoolId) {
		this.schoolId = schoolId;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getSchoolName() {
		return SchoolName;
	}
	
	/**
	 * 
	 * @param schoolName
	 */
	public void setSchoolName(String schoolName) {
		SchoolName = schoolName;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getSchoolImg() {
		return SchoolImg;
	}
	
	/**
	 * 
	 * @param schoolImg
	 */
	public void setSchoolImg(String schoolImg) {
		SchoolImg = schoolImg;
	}
	
	/**
	 * 
	 * @return
	 */
	public PostsDto getPosts() {
		return posts;
	}
	
	/**
	 * 
	 * @param posts
	 */
	public void setPosts(PostsDto posts) {
		this.posts = posts;
	}	
}