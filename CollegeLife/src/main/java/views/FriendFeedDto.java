package views;

public class FriendFeedDto {

	long 	userId;
	String 	userType;
	String 	name;
	String 	facebookName;
	String 	profileImg;
	long 	schoolId;
	String 	SchoolName;
	String 	SchoolImg;
	PostsDto 	posts;
	
	public FriendFeedDto(){
		
		posts = new PostsDto();
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
		return SchoolName;
	}

	public void setSchoolName(String schoolName) {
		SchoolName = schoolName;
	}

	public String getSchoolImg() {
		return SchoolImg;
	}

	public void setSchoolImg(String schoolImg) {
		SchoolImg = schoolImg;
	}

	public PostsDto getPosts() {
		return posts;
	}

	public void setPosts(PostsDto posts) {
		this.posts = posts;
	}
	
	
	
}
