package views;

public class FriendFeeds {

	String 	userId;
	String 	userType;
	String 	name;
	String 	facebookName;
	String 	profileImg;
	String 	schoolId;
	String 	SchoolName;
	String 	SchoolImg;
	Posts 	posts;
	
	public FriendFeeds(){
		
		posts = new Posts();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
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

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
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

	public Posts getPosts() {
		return posts;
	}

	public void setPosts(Posts posts) {
		this.posts = posts;
	}
	
	
	
}
