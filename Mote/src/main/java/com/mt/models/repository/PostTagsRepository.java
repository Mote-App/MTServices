package com.mt.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.mt.models.PostTags;

/**
 * The <code>PostTagsRepository</code> ...
 * 
 * @author gibranecastillo
 *
 */
public interface PostTagsRepository extends CrudRepository<PostTags, Long> {
	
}