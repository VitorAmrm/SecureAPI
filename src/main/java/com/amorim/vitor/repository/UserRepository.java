package com.amorim.vitor.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.amorim.vitor.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{
	
	Optional<User> findByUsername (String username); 

}
