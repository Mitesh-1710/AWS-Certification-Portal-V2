package com.cg.certification.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//class for storing the Certification details to the database
@Document(collection="CertificationDetail")
public class CertificationDetailModel {


	@Id
	@NotEmpty(message="Please enter Certification name") // spring validation for empty fields
	@Size(min=5, message="Certification name must have minmum 5 characters ") // spring validation for min/max value for string type variable
	private String certificationName; // primary key for the CertificationDetail collection
	
	@NotEmpty(message="Please enter Learning plan link") // spring validation for empty fields
	@Size(min=10, message="Learning plan link must have minmum 10 characters ") // spring validation for min/max value for string type variable
	private String learningPlanLink;

	@Min(value=21, message="Completion time must not be less than 21 days ") // spring validation for min value
	@Max(value=90, message="Completion time must not be greater than 90 days") // spring validation for max value
	private int completionTime;

	//default constructor
	public CertificationDetailModel() {
		super();
	}

	//parameterized constructor
	public CertificationDetailModel(
			@NotEmpty(message = "Please enter Certification name") @Size(min = 5, message = "Certification name must have minmum 5 characters ") String certificationName,
			@NotEmpty(message = "Please enter Learning plan link") @Size(min = 10, message = "Learning plan link must have minmum 10 characters ") String learningPlanLink,
			@Min(value = 21, message = "Completion time must not be less than 21 days ") @Max(value = 90, message = "Completion time must not be greater than 90 days") int completionTime) {
		super();
		this.certificationName = certificationName;
		this.learningPlanLink = learningPlanLink;
		this.completionTime = completionTime;
	}

	//getters and setters
	public String getCertificationName() {
		return certificationName;
	}

	public void setCertificationName(String certificationName) {
		this.certificationName = certificationName;
	}

	public String getLearningPlanLink() {
		return learningPlanLink;
	}

	public void setLearningPlanLink(String learningPlanLink) {
		this.learningPlanLink = learningPlanLink;
	}

	public int getCompletionTime() {
		return completionTime;
	}

	public void setCompletionTime(int completionTime) {
		this.completionTime = completionTime;
	}

	//toString method
	@Override
	public String toString() {
		return "CertificationDetailModel [certificationName=" + certificationName + ", learningPlanLink="
				+ learningPlanLink + ", completionTime=" + completionTime + "]";
	}

}
