package com.cg.user.model;


import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Generated;

//class for storing the user details to the database
@Document(collection="User")
public class UserModel {

	static final long max = 9999999999L;
	static final long min = 1000000000L;
	
	@Id
	@Min(value=1, message="Employe ID must not be less than 1 ") // spring validation for min value
	@Max(value=100, message="Employe ID must not be  greater than 100") // spring validation for max value
	private int empID; // primary key for the User collection
	
	@NotEmpty(message="Please enter employee Name") // spring validation for empty fields
	@Size(min=5, message="Name must have minmum 5 characters ") // spring validation for min/max value for string type variable
	private String empName;
	
	@NotEmpty(message="Please enter employee email") // spring validation for empty fields
	@Email(message = "Please enter a valid email") //spring validation for email
	private String email;
	
	@Min(value=min, message="Employe contact number must not be less than 1000000000 ") // spring validation for min value
	@Max(value=max, message="Employe contact number must not be  greater than 9999999999") // spring validation for max value
	private long contactNumber;
	
	@NotEmpty(message="Please enter employee grade") // spring validation for empty fields
	@Size(min=1, message="Name must have minimum 1 character ") // spring validation for min/max value for string type variable
	@Size(max=2, message="Name must have maximum 2 characters ") // spring validation for min/max value for string type variable
	private String grade;
	
	@NotEmpty(message="Please enter password") // spring validation for empty fields
	@Size(min=8, message="Password must have minmum 8 characters ") // spring validation for min/max value for string type variable
	private String password;
	
	private String joiningDate;
	
	@NotEmpty(message="Please enter Batch name") // spring validation for empty fields
	@Size(min=5, message="Batch name must have minmum 5 characters ") // spring validation for min/max value for string type variable
	private String batchName; // primary key for the BatchDetail collection
	
	private String role = "ROLE_NORMAL";
	
	//status will be updated at back-end based on user joining date, value can be either Fresher(under 6 months of joining date) or Lateral(pass 6 months of joining date)
	private String status;
	
	//Fields which can be updated after based on batchName input given by user
	private String trainedBy;
	private String mentorName;
	private String mentorEmail;
	private String reportingManagerName;
	private String reportingManagerEmail;
	
	//Fields which can be updated after user enrolled in certification course
	private String certificationName;
	private String enrolledStatus;
	private String enrolledDate;
	private String deadlineDate;
	
	//Fields which can be updated after user apply for an assessment
	private int numberOfAttempts;
	private int firstAttempt;
	private int secondAttempt;
	private int thirdAttempt;
	
	//Voucher status will be updated after user passed the assessment
	private String voucherStatus = "Not Shared";
	private String certificationStatus;
	
	//default constructor
	public UserModel() {
		super();
	}

	//parameterized constructor
	public UserModel(
			@Min(value = 1, message = "Employe ID must not be less than 1 ") @Max(value = 100, message = "Employe ID must not be  greater than 100") int empID,
			@NotEmpty(message = "Please enter employee Name") @Size(min = 5, message = "Name must have minmum 5 characters ") String empName,
			@NotEmpty(message = "Please enter employee email") @Email(message = "Please enter a valid email") String email,
			@Min(value =min, message = "Employe contact number must not be less than 1000000000 ") @Max(value = max, message = "Employe contact number must not be  greater than 9999999999") long contactNumber,
			@NotEmpty(message = "Please enter employee Name") @Size(min = 1, message = "Name must have minimum 1 character ") @Size(max = 2, message = "Name must have maximum 2 characters ") String grade,
			@NotEmpty(message = "Please enter password") @Size(min = 8, message = "Password must have minmum 8 characters ") String password,
			String joiningDate,
			@NotEmpty(message = "Please enter Batch name") @Size(min = 5, message = "Batch name must have minmum 5 characters ") String batchName) {
		super();
		this.empID = empID;
		this.empName = empName;
		this.email = email;
		this.contactNumber = contactNumber;
		this.grade = grade;
		this.password = password;
		this.joiningDate = joiningDate;
		this.batchName = batchName;
	}
	
	
	//getters and setters
	public String getCertificationStatus() {
		return certificationStatus;
	}

	public void setCertificationStatus(String certificationStatus) {
		this.certificationStatus = certificationStatus;
	}

	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public String getRole() {
		return role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getCertificationName() {
		return certificationName;
	}

	public void setCertificationName(String certificationName) {
		this.certificationName = certificationName;
	}

	public String getEnrolledStatus() {
		return enrolledStatus;
	}

	public void setEnrolledStatus(String enrolledStatus) {
		this.enrolledStatus = enrolledStatus;
	}

	public String getEnrolledDate() {
		return enrolledDate;
	}

	public void setEnrolledDate(String enrolledDate) {
		this.enrolledDate = enrolledDate;
	}

	public String getDeadlineDate() {
		return deadlineDate;
	}

	public void setDeadlineDate(String deadlineDate) {
		this.deadlineDate = deadlineDate;
	}

	public int getNumberOfAttempts() {
		return numberOfAttempts;
	}

	public void setNumberOfAttempts(int numberOfAttempts) {
		this.numberOfAttempts = numberOfAttempts;
	}

	public int getFirstAttempt() {
		return firstAttempt;
	}

	public void setFirstAttempt(int firstAttempt) {
		this.firstAttempt = firstAttempt;
	}

	public int getSecondAttempt() {
		return secondAttempt;
	}

	public void setSecondAttempt(int secondAttempt) {
		this.secondAttempt = secondAttempt;
	}

	public int getThirdAttempt() {
		return thirdAttempt;
	}

	public void setThirdAttempt(int thirdAttempt) {
		this.thirdAttempt = thirdAttempt;
	}

	public String getVoucherStatus() {
		return voucherStatus;
	}

	public void setVoucherStatus(String voucherStatus) {
		this.voucherStatus = voucherStatus;
	}

	@Generated
	//toString method
	@Override
	public String toString() {
		return "UserModel [empID=" + empID + ", empName=" + empName + ", email=" + email + ", contactNumber="
				+ contactNumber + ", grade=" + grade + ", password=" + password + ", joiningDate=" + joiningDate
				+ ", batchName=" + batchName + ", role=" + role + ", status=" + status + ", trainedBy=" + trainedBy
				+ ", mentorName=" + mentorName + ", mentorEmail=" + mentorEmail + ", reportingManagerName="
				+ reportingManagerName + ", reportingManagerEmail=" + reportingManagerEmail + ", certificationName="
				+ certificationName + ", enrolledStatus=" + enrolledStatus + ", enrolledDate=" + enrolledDate
				+ ", deadlineDate=" + deadlineDate + ", numberOfAttempts=" + numberOfAttempts + ", firstAttempt="
				+ firstAttempt + ", secondAttempt=" + secondAttempt + ", thirdAttempt=" + thirdAttempt
				+ ", voucherStatus=" + voucherStatus + "]";
	}
	
	
}

