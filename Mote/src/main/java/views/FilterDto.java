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
	List<Long> collegeIds = new ArrayList<Long>();
	List<Long> lstTags = new ArrayList<Long>();
	
	
	
	public List<Long> getCollegeIds() {
		return collegeIds;
	}

	public void setCollegeIds(List<Long> collegeIds) {
		this.collegeIds = collegeIds;
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