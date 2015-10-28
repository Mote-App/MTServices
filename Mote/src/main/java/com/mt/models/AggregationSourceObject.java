package com.mt.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
	
	
}
