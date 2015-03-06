package views;

import java.util.ArrayList;
import java.util.List;

public class FilterDto {

	Long collegeId;
	List<Long> lstTags = new ArrayList<Long>();
	
	
	public Long getCollegeId() {
		return collegeId;
	}
	public void setCollegeId(Long collegeId) {
		this.collegeId = collegeId;
	}
	public List<Long> getLstTags() {
		return lstTags;
	}
	public void setLstTags(List<Long> lstTags) {
		this.lstTags = lstTags;
	}
	
	
}
