package views;

import java.util.ArrayList;
import java.util.List;

/**
 * The <code>FilterDto</code> ...
 * 
 * @author gibranecastillo
 *
 */
public class FilterDto {
	Long collegeId;
	List<Long> lstTags = new ArrayList<Long>();
	
	/**
	 * 
	 * @return
	 */
	public Long getCollegeId() {
		return collegeId;
	}
	
	/**
	 * 
	 * @param collegeId
	 */
	public void setCollegeId(Long collegeId) {
		this.collegeId = collegeId;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Long> getLstTags() {
		return lstTags;
	}
	
	/**
	 * 
	 * @param lstTags
	 */
	public void setLstTags(List<Long> lstTags) {
		this.lstTags = lstTags;
	}	
}