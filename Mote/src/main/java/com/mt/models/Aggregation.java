package com.mt.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The <code>Aggregation</code> is the persistent class for the aggregation database table.
 * 
 * @author gibranecastillo
 *
 */
@Entity
@Table(name="aggregation")
public class Aggregation {
	@OneToOne
	@JoinColumn(name="profile_profile_id")
	private User profileId;
	
	@Id
	@Column(name="aggregation_id")
	private long aggregationId;
	
	@Column(name="aggregation_is_friend")
	private String aggregationIsFriend;
	
	@Column(name="aggregation_source_profile")
	private String aggregationName;
	
	public Aggregation() {
		// do nothing
	}
	
	/**
	 * @return the profileId
	 */
	public User getProfileId() {
		return profileId;
	}
	
	/**
	 * @param profileId the profileId to set
	 */
	public void setProfileId(User profileId) {
		this.profileId = profileId;
	}
	
	/**
	 * @return the aggregationId
	 */
	public long getAggregationId() {
		return aggregationId;
	}
	
	/**
	 * @param aggregationId the aggregationId to set
	 */
	public void setAggregationId(long aggregationId) {
		this.aggregationId = aggregationId;
	}
	
	/**
	 * @return the aggregationIsFriend
	 */
	public String getAggregationIsFriend() {
		return aggregationIsFriend;
	}
	
	/**
	 * @param aggregationIsFriend the aggregationIsFriend to set
	 */
	public void setAggregationIsFriend(String aggregationIsFriend) {
		this.aggregationIsFriend = aggregationIsFriend;
	}
	
	/**
	 * @return the aggregationName
	 */
	public String getAggregationName() {
		return aggregationName;
	}
	
	/**
	 * @param aggregationName the aggregationName to set
	 */
	public void setAggregationName(String aggregationName) {
		this.aggregationName = aggregationName;
	}
}