package com.mt.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mt.models.AggregationSourceObject;

public interface AggregationSourceObjectRepository extends CrudRepository<AggregationSourceObject,Long>{

	@Query("select u.sourceObjectId from AggregationSourceObject u where u.aggregationId = ? and u.sourceObjectUrl = ? ")
	Long findExistingAggregationSourceObject(long aggregationId, String sourceObjectUrl);
}
