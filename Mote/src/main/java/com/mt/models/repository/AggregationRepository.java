package com.mt.models.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mt.models.Aggregation;
import com.mt.models.User;

/**
 * The <code>AggregationRepository</code> ...
 * 
 * @author gibranecastillo
 *
 */
public interface AggregationRepository extends CrudRepository<Aggregation, Long> {
	@Query("select a.profileId from Aggregation a where a.aggregationId = :aggregationId and a.aggregationName = :aggregationName")
	User findUserAggregationFriend(@Param("aggregationId") long aggregationId, @Param("aggregationName") String aggregationName);
}