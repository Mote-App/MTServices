package com.mt.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The <code>AggregationSource</code> is the persistent class for the aggregation_source database table.
 * 
 * Represents a source for aggregated content from social media eg. Instagram, Facebook, etc.
 * 
 * @author chrislawson
 *
 */
@Entity
@Table(name="aggregation_source")
public class AggregationSource {
	@Id
	@Column(name="aggregation_source_id")
	private String aggregationSourceId;
	
	@Column(name="aggregation_source_name")
	private String aggregationSourceName;
	
	public AggregationSource() {
		// do nothing
	}
	
	public String getAggregationSourceId() {
		return aggregationSourceId;
	}
	
	public void setAggregationSourceId(String aggregationSourceId) {
		this.aggregationSourceId = aggregationSourceId;
	}
	
	public String getAggregationSourceName() {
		return aggregationSourceName;
	}
	
	public void setAggregationSourceName(String aggregationSourceName) {
		this.aggregationSourceName = aggregationSourceName;
	}
}