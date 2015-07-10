package views;

/**
 * The <code>CollegeDto</code> ...
 * 
 * @author gibranecastillo
 *
 */
public class CollegeDto {
	private long collegeId;
	private String collegeImgPath;
	private String collegeName;
	
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
}