package views;

/**
 * The <code>LoginDto</code> ...
 * 
 * @author gibranecastillo
 *
 */
public class LoginDto {
	private int    collegeId;
	private String userName;
	private String password;
	
	/**
	 * 
	 * @return
	 */
	public int getCollegeId() {
		return collegeId;
	}
	
	/**
	 * 
	 * @param collegeId
	 */
	public void setCollegeId(int collegeId) {
		this.collegeId = collegeId;
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
}