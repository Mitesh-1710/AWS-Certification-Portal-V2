package com.cg.admin.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cg.admin.model.AdminModel;

//admin database repository
public interface AdminServiceRepository extends MongoRepository<AdminModel,Integer> {

	//user defined database repository method for retrieving data by email id.
	Optional<AdminModel> findByEmail(String email);

	//user defined database repository method for checking if email id exists or not
	boolean existsByEmail(String email);

}
