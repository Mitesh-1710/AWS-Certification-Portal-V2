package com.cg.certification.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cg.certification.model.CertificationDetailModel;


public interface CertificationDetailRepository extends MongoRepository<CertificationDetailModel,String>{

}
