package views;

/**
 * The <code>TokenDto</code> ...
 * 
 * @author gibranecastillo
 *
 */
public class TokenDto {
	private String token;
	private long userId;
	private long collegeId;
	
	/**
	 * 
	 * @param token
	 * @param userId
	 * @param collegeId
	 */
	public TokenDto(String token, long userId, long collegeId) {
		this.token = token;
		this.userId = userId;
		this.collegeId = collegeId;
	}
	
	/**
	 * 
	 * @param token
	 */
	public void setToken(String token) {
		this.token = token;
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
	public String getToken() {
		return token;
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
	 * @return
	 */
	public long getCollegeId() {
		return collegeId;
	}
	
	/**
	 * 
	 * @param collegeId
	 */
	public void setCollegeId(long collegeId) {
		this.collegeId = collegeId;
	}	
}