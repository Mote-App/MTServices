package com.mt.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mt.models.Aggregation;

/**
 * The <code>AggregationRepository</code> ...
 * 
 * @author clawson
 *
 */
public interface AggregationSourceRepository extends CrudRepository<Aggregation, Long> {
	@Query("select aggregationSourceId from AggregationSource where aggregationSourceName = :aggregationSourceName")
	String findAggregationSourceId(@Param("aggregationSourceName") String aggregationSourceName);	
}