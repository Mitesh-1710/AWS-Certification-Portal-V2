package com.cg.user.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cg.user.model.UserModel;

//user database repository
public interface UserRepository extends MongoRepository<UserModel,Integer> {

	//user defined database repository method for checking if email id exists or not
	boolean existsByEmail(String email);

	//user defined database repository method for retrieving data by email id.
	Optional<UserModel> findByEmail(String email);

}

