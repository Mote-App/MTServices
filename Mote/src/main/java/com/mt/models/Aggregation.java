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
	
	/**
	 * Allows you to get some meaningful representation of the Aggregation.java object.
	 * This is the "spill-your-guts method," because it simply spits out the object's
	 * state; in other words, the current values of the important instance variables.
	 *  
	 * @return a "text representation" of the StringBuilder object that invoked the method call as a String.
	 */
	@Override
	public String toString() {
		/*
		 * The StringBuilder class (added in Java 1.5) has exactly the same API as
		 * the StringBuffer class, except StringBuilder is not thread safe (methods are not synchronized);
		 * therefore, StringBuilder will run faster.
		 */
		StringBuilder sb = new StringBuilder();
		sb.append("\n* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		sb.append("\n*                                      Mote Aggregation toString()                                           ");
		sb.append("\n* Aggregation.java -> The persistent class for the aggregation database table. @Entity and @Table(name=\"aggregation\")");
		sb.append("\n* User profileId -> Mote User's object [@OneToOne and @JoinColumn(name=\"profile_profile_id\")]: " + this.profileId);
		sb.append("\n* Long aggregationId -> Social Platform user's Id [@Id and @Column(name=\"aggregation_id\")]: " + this.aggregationId);
		sb.append("\n* int aggregationSourceId -> Social Platform id in Mote [@Column(name=\"aggregation_source_aggregation_source_id\")]: " + this.aggregationSourceId);
		sb.append("\n* String aggregationIsFriend -> ** not sure yet ** [@Column(name=\"aggregation_is_friend\")]: " + this.aggregationIsFriend);
		sb.append("\n* String aggregationName -> Social Platform name [@Column(name=\"aggregation_source_profile\")]: " + this.aggregationName);
		sb.append("\n* String accessToken -> Facebook user's OAuth 2.0 access token [@Column(name=\"access_token\")]: " + this.accessToken);
		sb.append("\n* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		return sb.toString();
	}
}