package com.cg.batch.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.batch.exception.DataAlreadyExistsException;
import com.cg.batch.exception.NameNotFoundException;
import com.cg.batch.model.BatchDetailModel;
import com.cg.batch.repository.BatchDetailRepository;

@Service
public class BatchDetailServiceImpl implements BatchDetailService {

	//injecting bean of BatchDetailRepository
	@Autowired
	private BatchDetailRepository batchRepo;
	
	@Override
	public BatchDetailModel createBatch(BatchDetailModel batch) throws DataAlreadyExistsException {
		if(batchRepo.existsById(batch.getBatchName()))
		{
			throw new DataAlreadyExistsException();
		}
		return batchRepo.save(batch); // save batch data to database
	}

	@Override
	public BatchDetailModel updateBatch(BatchDetailModel batch) throws NameNotFoundException {
		
		Optional<BatchDetailModel> batchDB = this.batchRepo.findById(batch.getBatchName());
		
		if(batchDB.isPresent())
		{
			BatchDetailModel updateDB = batchDB.get();
			updateDB.setBatchName(batch.getBatchName());
			updateDB.setTrainedBy(batch.getTrainedBy());
			updateDB.setMentorName(batch.getMentorName());
			updateDB.setMentorEmail(batch.getMentorEmail());
			updateDB.setReportingManagerName(batch.getReportingManagerName());
			updateDB.setReportingManagerEmail(batch.getReportingManagerEmail());

			return batchRepo.save(updateDB); // save batch data to database
		}
		else
		{
			throw new NameNotFoundException();
		}

	}

	@Override
	public List<BatchDetailModel> getAllBatchData() {
		
		return this.batchRepo.findAll();
	
	}

	@Override
	public BatchDetailModel getBatchDataByName(String batchName) throws NameNotFoundException {
		
		Optional<BatchDetailModel> batchDB = this.batchRepo.findById(batchName);

        if (batchDB.isPresent()) {
        	
            return batchDB.get();
        } 
        else
        {
        	throw new NameNotFoundException();
        }
	}

	@Override
	public void deleteBatchDataByName(String batchName) throws NameNotFoundException {
		
		Optional<BatchDetailModel> batchDB = this.batchRepo.findById(batchName);

        if (batchDB.isPresent()) {
        	
        	this.batchRepo.delete(batchDB.get());
        } 
        else
        {
        	throw new NameNotFoundException();
        }
				
	}

}
