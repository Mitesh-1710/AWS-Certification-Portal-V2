package com.cg.batch.controller;

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

import com.cg.batch.exception.DataAlreadyExistsException;
import com.cg.batch.exception.NameNotFoundException;
import com.cg.batch.model.BatchDetailModel;
import com.cg.batch.service.BatchDetailService;

@RestController
//global request mapping url
@RequestMapping("/api/batch")
public class BatchDetailController {

	//injecting bean of ExamService
	@Autowired
	private BatchDetailService batchServ;
	
	
	//creating and storing batch information to the database
	@PostMapping("/private/createBatch")
	public ResponseEntity<BatchDetailModel> createBatch(@Valid @RequestBody BatchDetailModel batch)throws DataAlreadyExistsException
	{
		return new ResponseEntity<>(batchServ.createBatch(batch), HttpStatus.CREATED);
		
	}
	
	//updating the exam information in the database
    @PutMapping("/private/updateBatch/{batchName}")
    public ResponseEntity < BatchDetailModel > updateBatch(@Valid @RequestBody BatchDetailModel batch ,@PathVariable String batchName) throws NameNotFoundException {
    	batch.setBatchName(batchName);
    	return new ResponseEntity<>(this.batchServ.updateBatch(batch), HttpStatus.CREATED);
    }
	
	//display the information of all the batch details available in the database
	@GetMapping("/private/displayAllBatch")
    public ResponseEntity < List < BatchDetailModel >> getAllBatchData() {
        return ResponseEntity.ok().body(batchServ.getAllBatchData());
    }


	//display the specific exam information based on batch name
    @GetMapping("/private/getBatchByName/{batchName}")
    public ResponseEntity < BatchDetailModel > getBatchDataByName(@PathVariable String batchName) throws NameNotFoundException {
        return ResponseEntity.ok().body(batchServ.getBatchDataByName(batchName));
    }
	

	//delete the specific exam details from the database
    @DeleteMapping("/private/deleteBatchByName/{batchName}")
    public ResponseEntity<String>  deleteBatchDataByName(@PathVariable String batchName) throws NameNotFoundException {
        this.batchServ.deleteBatchDataByName(batchName);
        return new ResponseEntity<>("Batch data deleted successfully !!", HttpStatus.OK);
    }

	
}
