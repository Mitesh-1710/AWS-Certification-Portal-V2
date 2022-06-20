package com.cg.user.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cg.user.model.LoginDetail;

import lombok.Generated;

//login details database repository
@Generated
public interface LoginDetailRepository extends MongoRepository<LoginDetail,String>{

	//user defined database repository method for retrieving data by email id.
	Optional<LoginDetail> findByEmail(String email);
	
}
