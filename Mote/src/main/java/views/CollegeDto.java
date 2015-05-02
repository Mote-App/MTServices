package views;

/**
 * 
 * @author gibranecastillo
 *
 */
public class CollegeDto {
	private long collegeId;
	private String collegeImgPath;
	private String collegeName;
	private String collegeLanguageCode;
	private String collegeCountryCode;
	
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
	
	/**
	 * 
	 * @return
	 */
	public String getCollegeImgPath() {
		return collegeImgPath;
	}
	
	/**
	 * 
	 * @param collegeImgPath
	 */
	public void setCollegeImgPath(String collegeImgPath) {
		this.collegeImgPath = collegeImgPath;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCollegeName() {
		return collegeName;
	}
	
	/**
	 * 
	 * @param collegeName
	 */
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCollegeLanguageCode() {
		return collegeLanguageCode;
	}
	
	/**
	 * 
	 * @param collegeLanguageCode
	 */
	public void setCollegeLanguageCode(String collegeLanguageCode) {
		this.collegeLanguageCode = collegeLanguageCode;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCollegeCountryCode() {
		return collegeCountryCode;
	}
	
	/**
	 * 
	 * @param collegeCountryCode
	 */
	public void setCollegeCountryCode(String collegeCountryCode) {
		this.collegeCountryCode = collegeCountryCode;
	}
}