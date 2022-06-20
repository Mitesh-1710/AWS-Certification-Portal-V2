package com.cg.batch.service;

import java.util.List;

import com.cg.batch.exception.DataAlreadyExistsException;
import com.cg.batch.exception.NameNotFoundException;
import com.cg.batch.model.BatchDetailModel;

public interface BatchDetailService {

	public BatchDetailModel createBatch(BatchDetailModel batch)throws DataAlreadyExistsException;
	public BatchDetailModel updateBatch(BatchDetailModel batch)throws NameNotFoundException;
	public List<BatchDetailModel> getAllBatchData();
	public BatchDetailModel getBatchDataByName(String batchName)throws NameNotFoundException;
	public void deleteBatchDataByName(String batchName)throws NameNotFoundException;
	
}
