package com.mt.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mt.models.Post;

/**
 * The <code>PostRepository</code> ...
 * 
 * @author gibranecastillo
 *
 */
public interface PostRepository extends JpaRepository<Post, Long> {	
	
}