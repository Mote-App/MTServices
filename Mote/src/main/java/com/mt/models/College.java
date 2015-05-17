package com.mt.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the college database table.
 * 
 * @author gibranecastillo
 *
 */
@Entity
@Table(name="college")
public class College {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="college_id")
	private long collegeId;
	
	@Column(name="college_img_path")
	private String collegeImgPath;
	
	@Column(name="college_name")
	private String collegeName;
	
	/**
	 * Constructs a College object.  Require by spring/hibernate.
	 */
	public College() { }
	
	/**
	 * 
	 * @param collegeId
	 */
	public College(long collegeId) {
		this.collegeId = collegeId;
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