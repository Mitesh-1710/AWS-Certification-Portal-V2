package com.cg.user.model;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Generated;

@Generated
//class for storing the batch details to the database
@Document(collection="BatchDetail")
public class BatchDetailModel {

	@Id
	@NotEmpty(message="Please enter Batch name") // spring validation for empty fields
	@Size(min=5, message="Batch name must have minmum 5 characters ") // spring validation for min/max value for string type variable
	private String batchName; // primary key for the BatchDetail collection
	

	@NotEmpty(message="Please enter Trained by field") // spring validation for empty fields
	@Size(min=3, message="Training module must have minmum 3 characters ") // spring validation for min/max value for string type variable
	private String trainedBy;
	

	@NotEmpty(message="Please enter Mentor name") // spring validation for empty fields
	@Size(min=5, message="Mentor name must have minmum 5 characters ") // spring validation for min/max value for string type variable
	private String mentorName;
	
	@NotEmpty(message="Please enter Mentor email") // spring validation for empty fields
	@Email(message = "Please enter a valid email") //spring validation for email
	private String mentorEmail;
	

	@NotEmpty(message="Please enter Reporting manager name") // spring validation for empty fields
	@Size(min=5, message="Reporting manager name must have minmum 5 characters ") // spring validation for min/max value for string type variable
	private String reportingManagerName;
	
	@NotEmpty(message="Please enter Reporting manager email") // spring validation for empty fields
	@Email(message = "Please enter a valid email") //spring validation for email
	private String reportingManagerEmail;

	//default constructor
	public BatchDetailModel() {
		super();
	}

	//parameterized constructor
	public BatchDetailModel(
			@NotEmpty(message = "Please enter Batch name") @Size(min = 5, message = "First name must have minmum 5 characters ") String batchName,
			@NotEmpty(message = "Please enter Trained by field") @Size(min = 3, message = "Training module must have minmum 3 characters ") String trainedBy,
			@NotEmpty(message = "Please enter Mentor name") @Size(min = 5, message = "Mentor name must have minmum 5 characters ") String mentorName,
			@NotEmpty(message = "Please enter Mentor email") @Email(message = "Please enter a valid email") String mentorEmail,
			@NotEmpty(message = "Please enter Reporting manager name") @Size(min = 5, message = "Reporting manager name must have minmum 5 characters ") String reportingManagerName,
			@NotEmpty(message = "Please enter Reporting manager email") @Email(message = "Please enter a valid email") String reportingManagerEmail) {
		super();
		this.batchName = batchName;
		this.trainedBy = trainedBy;
		this.mentorName = mentorName;
		this.mentorEmail = mentorEmail;
		this.reportingManagerName = reportingManagerName;
		this.reportingManagerEmail = reportingManagerEmail;
	}

	//getters and setters
	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public String getTrainedBy() {
		return trainedBy;
	}

	public void setTrainedBy(String trainedBy) {
		this.trainedBy = trainedBy;
	}

	public String getMentorName() {
		return mentorName;
	}

	public void setMentorName(String mentorName) {
		this.mentorName = mentorName;
	}

	public String getMentorEmail() {
		return mentorEmail;
	}

	public void setMentorEmail(String mentorEmail) {
		this.mentorEmail = mentorEmail;
	}

	public String getReportingManagerName() {
		return reportingManagerName;
	}

	public void setReportingManagerName(String reportingManagerName) {
		this.reportingManagerName = reportingManagerName;
	}

	public String getReportingManagerEmail() {
		return reportingManagerEmail;
	}

	public void setReportingManagerEmail(String reportingManagerEmail) {
		this.reportingManagerEmail = reportingManagerEmail;
	}

	@Generated
	//toString method
	@Override
	public String toString() {
		return "BatchDetailModel [batchName=" + batchName + ", trainedBy=" + trainedBy + ", mentorName=" + mentorName
				+ ", mentorEmail=" + mentorEmail + ", reportingManagerName=" + reportingManagerName
				+ ", reportingManagerEmail=" + reportingManagerEmail + "]";
	}
	
	
}


