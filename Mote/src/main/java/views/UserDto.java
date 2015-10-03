package views;

import java.util.List;

/**
 * The <code>UserDto</code> ...
 * 
 * @author gibranecastillo
 *
 */
public class UserDto {
	private long 	   	profileId;
	private List<AggregationDto> aggregationList;
	private String 	   	firstName;
	private String 	   	lastName;
	private String 	   	userName;
	private String 	   	email;
	private String 	   	password;
	private String 	   	profilePictureUrl;
	private CollegeDto 	college;
	private byte 	   	isFriend = 0;
	
	
	
	public long getProfileId() {
		return profileId;
	}

	public void setProfileId(long profileId) {
		this.profileId = profileId;
	}
	
	/**
	 * @return the aggregationList
	 */
	public List<AggregationDto> getAggregationList() {
		return aggregationList;
	}
	
	/**
	 * @param aggregationList the aggregationList to set
	 */
	public void setAggregationList(List<AggregationDto> aggregationList) {
		this.aggregationList = aggregationList;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * 
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * 
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getProfilePictureUrl() {
		return profilePictureUrl;
	}
	
	/**
	 * 
	 * @param profilePictureUrl
	 */
	public void setProfilePictureUrl(String profilePictureUrl) {
		this.profilePictureUrl = profilePictureUrl;
	}
	
	/**
	 * 
	 * @return
	 */
	public CollegeDto getCollege() {
		return college;
	}
	
	/**
	 * 
	 * @param college
	 */
	public void setCollege(CollegeDto college) {
		this.college = college;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * 
	 * @return
	 */
	public byte getIsFriend() {
		return isFriend;
	}
	
	/**
	 * 
	 * @param isFriend
	 */
	public void setIsFriend(byte isFriend) {
		this.isFriend = isFriend;
	}	  
}