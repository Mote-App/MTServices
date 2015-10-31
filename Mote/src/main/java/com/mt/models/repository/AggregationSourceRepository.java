package com.mt.models.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.mt.models.Aggregation;


/**
 * The <code>AggregationRepository</code> ...
 * 
 * @author clawson
 *
 */
public interface AggregationSourceRepository extends CrudRepository<Aggregation, Long> {
	
	@Query("select aggregationSourceId from AggregationSource where aggregationSourceName = ?")
	String findAggregationSourceId(String aggregationSourceName);
	
	
	
}