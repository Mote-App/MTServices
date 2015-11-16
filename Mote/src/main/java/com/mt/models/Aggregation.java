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
 * It should be public when on friend feed and when promoted to school/national it should become anonymous
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
	private Long aggregationId;
	
	@Column(name="aggregation_source_aggregation_source_id")
	private int aggregationSourceId;
	
	@Column(name="aggregation_is_friend")
	private String aggregationIsFriend;
	
	@Column(name="aggregation_source_profile")
	private String aggregationName;

	@Column(name="access_token")
	private String accessToken;
	
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
	public void setAggregationId(Long aggregationId) {
		this.aggregationId = aggregationId;
	}
	
	public int getAggregationSourceId() {
		return aggregationSourceId;
	}

	public void setAggregationSourceId(int aggregationSourceId) {
		this.aggregationSourceId = aggregationSourceId;
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

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
}