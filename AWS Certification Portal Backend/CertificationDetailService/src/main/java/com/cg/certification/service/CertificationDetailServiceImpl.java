package com.cg.certification.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.certification.exception.DataAlreadyExistsException;
import com.cg.certification.exception.NameNotFoundException;
import com.cg.certification.model.CertificationDetailModel;
import com.cg.certification.repository.CertificationDetailRepository;

@Service
public class CertificationDetailServiceImpl implements CertificationDetailService{

	//injecting bean of CertificationDetailRepository
	@Autowired
	private CertificationDetailRepository certiRepo;
	
	@Override
	public CertificationDetailModel createCertification(CertificationDetailModel certification) throws DataAlreadyExistsException {
		if(certiRepo.existsById(certification.getCertificationName()))
		{
			throw new DataAlreadyExistsException();
		}
		return certiRepo.save(certification); // save certification data to database

	}

	@Override
	public CertificationDetailModel updateCertification(CertificationDetailModel certification) throws NameNotFoundException {
		
		Optional<CertificationDetailModel> certificationDB = this.certiRepo.findById(certification.getCertificationName());
		
		if(certificationDB.isPresent())
		{
			CertificationDetailModel updateDB = certificationDB.get();
			updateDB.setCertificationName(certification.getCertificationName());
			updateDB.setLearningPlanLink(certification.getLearningPlanLink());
			updateDB.setCompletionTime(certification.getCompletionTime());

			return certiRepo.save(updateDB); // save batch data to database
		}
		else
		{
			throw new NameNotFoundException();
		}

	}

	@Override
	public List<CertificationDetailModel> getAllCertificationData() {
	
		return this.certiRepo.findAll();
	}

	@Override
	public CertificationDetailModel getCertificationDataByName(String certificationName) throws NameNotFoundException {
	
		Optional<CertificationDetailModel> certificationDB = this.certiRepo.findById(certificationName);

        if (certificationDB.isPresent()) {
        	
            return certificationDB.get();
        } 
        else
        {
        	throw new NameNotFoundException();
        }
	}

	@Override
	public void deleteCertificationDataByName(String certificationName) throws NameNotFoundException {
		
		Optional<CertificationDetailModel> certificationDB = this.certiRepo.findById(certificationName);

        if (certificationDB.isPresent()) {
        	
        	this.certiRepo.delete(certificationDB.get());
        } 
        else
        {
        	throw new NameNotFoundException();
        }
	}

}
