package com.cg.admin.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cg.admin.model.LoginDetail;

//login details database repository
public interface LoginDetailsRepository extends MongoRepository<LoginDetail,String>{

	//user defined database repository method for retrieving data by email id.
	Optional<LoginDetail> findByEmail(String email);
	
}
