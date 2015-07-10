package com.mt.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.mt.models.Post;

/**
 * The <code>PostRepository</code> ...
 * 
 * @author gibranecastillo
 *
 */
public interface PostRepository extends CrudRepository<Post, Long> {
	
}