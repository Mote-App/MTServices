package com.cl.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.cl.models.Post;

public interface PostRepository extends CrudRepository<Post, Long>{

}
