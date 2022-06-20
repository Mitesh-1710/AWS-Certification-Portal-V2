package com.cg.batch.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cg.batch.model.BatchDetailModel;

//Batch Detail database repository
public interface BatchDetailRepository extends MongoRepository<BatchDetailModel,String>{

}
