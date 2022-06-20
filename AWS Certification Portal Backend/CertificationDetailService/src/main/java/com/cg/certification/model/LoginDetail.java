package com.cg.certification.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//Login class for storing the login details to the database
@Document(collection="LoginDetail")
public class LoginDetail {

	@Id
	private String email; //primary key
	private String password;
	private String role;

	//default constructor
	public LoginDetail() {
		super();
	}

	//parameterized constructor
	public LoginDetail(String email, String password, String role) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
	}

	//getters and setters
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
		return "LoginDetails [email=" + email + ", password=" + password + ", role=" + role + "]";
	}
	
}

