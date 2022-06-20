package com.cg.certification.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cg.certification.model.LoginDetail;

//login details database repository
public interface LoginDetailRepository extends MongoRepository<LoginDetail,String>{

	//user defined database repository method for retrieving data by email id
	Optional<LoginDetail> findByEmail(String email);
	
}