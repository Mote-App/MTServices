package views;

public class UserDto {

	private String 		firstName;
	private String 		lastName;
	private String 		userName;
	private String 		email;
	private String 		password;
	private String 		profilePictureUrl;
	private CollegeDto 	college;
	private byte 		isFriend = 0;
	  
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getProfilePictureUrl() {
		return profilePictureUrl;
	}
	public void setProfilePictureUrl(String profilePictureUrl) {
		this.profilePictureUrl = profilePictureUrl;
	}
	public CollegeDto getCollege() {
		return college;
	}
	public void setCollege(CollegeDto college) {
		this.college = college;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public byte getIsFriend() {
		return isFriend;
	}
	public void setIsFriend(byte isFriend) {
		this.isFriend = isFriend;
	}
	
	
	  
	  
}
