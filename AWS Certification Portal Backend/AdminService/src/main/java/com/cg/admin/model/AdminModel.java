package com.cg.admin.model;

import javax.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//class for storing the admin details to the database
@Document(collection="Admin")
public class AdminModel {
	
	@Id
	@Min(value=101, message="Admin ID must not be less than 101 ") // spring validation for min value
	@Max(value=200, message="Admin ID must not be  greater than 200") // spring validation for max value
	private int empID; // primary key for the Admin collection
	
	@NotEmpty(message="Please enter email") // spring validation for empty fields
	@Email(message = "Email should be valid") //spring validation for email
	private String email;
	
	@NotEmpty(message="Please enter password") // spring validation for empty fields
	@Size(min=8, message="Password must have minimum 8 characters ") // spring validation for min/max value for string type variable
	private String password;
	
	private  String role = "ROLE_ADMIN";

	//default constructor
	public AdminModel() {
		super();
	}

	//parameterized constructor
	public AdminModel(
			@Min(value = 101, message = "Admin ID must not be less than 101 ") @Max(value = 200, message = "Admin ID must not be  greater than 200") int empID,
			@NotEmpty(message = "Please enter email") @Email(message = "Email should be valid") String email,
			@NotEmpty(message = "Please enter password") @Size(min = 8, message = "Password must have minmum 8 characters ") String password
			) {
		super();
		this.empID = empID;
		this.email = email;
		this.password = password;
	}

	//getters and setters
	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	//toString method
	@Override
	public String toString() {
		return "AdminModel [empID=" + empID + ", email=" + email + ", password=" + password + ", role=" + role + "]";
	}
	
	
	
}
