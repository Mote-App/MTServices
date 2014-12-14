package com.cl.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.cl.models.User;

public interface UserRepository extends CrudRepository<User, Long>{

}
