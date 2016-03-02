package com.mt.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mt.models.AggregationSourceObject;

public interface AggregationSourceObjectRepository extends CrudRepository<AggregationSourceObject,Long>{
	@Query("select u.sourceObjectId from AggregationSourceObject u where u.aggregationId = :aggregationId and u.sourceObjectUrl = :sourceObjectUrl ")
	Long findExistingAggregationSourceObject(@Param("aggregationId") long aggregationId, @Param("sourceObjectUrl") String sourceObjectUrl);
}
