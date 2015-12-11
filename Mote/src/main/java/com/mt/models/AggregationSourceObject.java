package com.mt.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The <code>AggregationSourceObject</code> is the persistent class for the source_objects database table.
 * 
 * It should be public when on friend feed and when promoted to school/national it should become anonymous
 * 
 * @author gibranecastillo
 *
 */
@Entity
@Table(name="source_objects")
public class AggregationSourceObject {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="source_objects_id")
	private long sourceObjectId;
	
	@Column(name="aggregation_aggregation_id")
	private long aggregationId;
	
	@Column(name="source_objects_url")
	private String sourceObjectUrl;
	
	@Column(name="source_objects_caption")
	private String sourceObjectCaption;
	
	/**
	 * 
	 * @return
	 */
	public long getSourceObjectId() {
		return sourceObjectId;
	}
	
	public void setSourceObjectId(long sourceObjectId) {
		this.sourceObjectId = sourceObjectId;
	}
	
	public long getAggregationId() {
		return aggregationId;
	}
	
	public void setAggregationId(long aggregationId) {
		this.aggregationId = aggregationId;
	}
	
	public String getSourceObjectUrl() {
		return sourceObjectUrl;
	}
	
	public void setSourceObjectUrl(String sourceObjectUrl) {
		this.sourceObjectUrl = sourceObjectUrl;
	}
	
	public String getSourceObjectCaption() {
		return sourceObjectCaption;
	}
	
	public void setSourceObjectCaption(String sourceObjectCaption) {
		this.sourceObjectCaption = sourceObjectCaption;
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
		sb.append("\n*                                 Mote Aggregation Source Object toString()                                  ");
		sb.append("\n* AggregationSourceObject.java -> The persistent class for the source_objects database table. @Entity and @Table(name=\"source_objects\")");
		sb.append("\n* long sourceObjectId -> Mote Source Object Id [@Id, @GeneratedValue(strategy = GenerationType.AUTO) and Column(name=\"source_objects_id\")]: " + this.sourceObjectId);
		sb.append("\n* Long aggregationId -> Social Platform user's Id [@Column(name=\"aggregation_aggregation_id\")]: " + this.aggregationId);
		sb.append("\n* iString sourceObjectUrl -> Social Platform's Media (photos or videos) URL [@Column(name=\"source_objects_url\")]: " + this.sourceObjectUrl);
		sb.append("\n* String sourceObjectCaption -> Social Platform's Media (photos or videos) name [@Column(name=\"source_objects_caption\")]: " + this.sourceObjectCaption);
		sb.append("\n* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		return sb.toString();
	}
}