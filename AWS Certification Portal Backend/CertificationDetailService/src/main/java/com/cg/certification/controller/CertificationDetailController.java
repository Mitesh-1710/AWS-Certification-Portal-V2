package com.cg.certification.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.certification.exception.DataAlreadyExistsException;
import com.cg.certification.exception.NameNotFoundException;
import com.cg.certification.model.CertificationDetailModel;
import com.cg.certification.service.CertificationDetailService;

@RestController
//global request mapping url
@RequestMapping("/api/certification")
public class CertificationDetailController {

	//injecting bean of ExamService
	@Autowired
	private CertificationDetailService certiServ;
	
	
	
	//creating and storing Certification information to the database
	@PostMapping("/private/createCertification")
	public ResponseEntity<CertificationDetailModel> createCertification(@Valid @RequestBody CertificationDetailModel certification)throws DataAlreadyExistsException
	{
		return new ResponseEntity<>(certiServ.createCertification(certification), HttpStatus.CREATED);
		
	}
	
	//updating the Certification information in the database
    @PutMapping("/private/updateCertification/{certificationName}")
    public ResponseEntity < CertificationDetailModel > updateCertification(@Valid @RequestBody CertificationDetailModel certification ,@PathVariable String certificationName) throws NameNotFoundException {
    	certification.setCertificationName(certificationName);
    	return new ResponseEntity<>(this.certiServ.updateCertification(certification), HttpStatus.CREATED);
    }
	
	//display the information of all the Certification details available in the database
	@GetMapping("/public/displayAllCertification")
    public ResponseEntity < List < CertificationDetailModel >> getAllCertificationData() {
        return ResponseEntity.ok().body(certiServ.getAllCertificationData());
    }


	//display the specific Certification information based on batch name
    @GetMapping("/public/getCertificationByName/{certificationName}")
    public ResponseEntity < CertificationDetailModel > getCertificationDataByName(@PathVariable String certificationName) throws NameNotFoundException {
        return ResponseEntity.ok().body(certiServ.getCertificationDataByName(certificationName));
    }
	

	//delete the specific Certification details from the database
    @DeleteMapping("/private/deleteCertificationByName/{certificationName}")
    public ResponseEntity<String> deleteCertificationDataByName(@PathVariable String certificationName) throws NameNotFoundException {
        this.certiServ.deleteCertificationDataByName(certificationName);
        return new ResponseEntity<>("Certification data deleted successfully !!", HttpStatus.OK);
    }

	
	
}
