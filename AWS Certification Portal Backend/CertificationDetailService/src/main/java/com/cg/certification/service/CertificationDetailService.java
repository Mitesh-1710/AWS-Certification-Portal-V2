package com.cg.certification.service;

import java.util.List;

import com.cg.certification.exception.DataAlreadyExistsException;
import com.cg.certification.exception.NameNotFoundException;
import com.cg.certification.model.CertificationDetailModel;

public interface CertificationDetailService {

	public CertificationDetailModel createCertification(CertificationDetailModel certification)throws DataAlreadyExistsException;
	public CertificationDetailModel updateCertification(CertificationDetailModel certification)throws NameNotFoundException;
	public List<CertificationDetailModel> getAllCertificationData();
	public CertificationDetailModel getCertificationDataByName(String certificationName)throws NameNotFoundException;
	public void deleteCertificationDataByName(String certificationName)throws NameNotFoundException;

}
