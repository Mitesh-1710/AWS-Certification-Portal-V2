package com.cg.user.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.user.exception.DataAlreadyExistsException;
import com.cg.user.exception.EmailAlreadyExistsException;
import com.cg.user.exception.IdNotFoundException;
import com.cg.user.model.LoginDetail;
import com.cg.user.model.UserModel;
import com.cg.user.service.LoginDetailService;
import com.cg.user.service.UserService;

import lombok.Generated;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
//global request mapping url
@RequestMapping("/api/user")
public class UserController {

    //injecting bean of UserService
	@Autowired
	private UserService userServ;
	
	//injecting bean of UserService
	@Autowired
	private LoginDetailService loginServ;
	
	//signing up the new user and storing the information to database
	@PostMapping("/all/signup")
	public ResponseEntity<UserModel> createUser(@Valid @RequestBody UserModel user)throws DataAlreadyExistsException,EmailAlreadyExistsException
	{
		return new ResponseEntity<>(userServ.createUser(user), HttpStatus.CREATED);
		
	}
	
    
    // /public are accessible to everyone
    //updating the user information in the database
    @PutMapping("/public/updateUser/{id}")
    public ResponseEntity < UserModel > updateUser(@Valid @RequestBody UserModel user ,@PathVariable int id) throws IdNotFoundException {
    	user.setEmpID(id);
    	return new ResponseEntity<>(this.userServ.updateUser(user), HttpStatus.CREATED);
    }
	
    //updating the user Certification information in the database
    @PutMapping("/public/updateUserCertification/{id}")
    public ResponseEntity < UserModel > updateUserCertification(@Valid @RequestBody UserModel user ,@PathVariable int id) throws IdNotFoundException {
    	user.setEmpID(id);
    	return new ResponseEntity<>(this.userServ.updateUserCertification(user), HttpStatus.CREATED);
    }
    
    //updating the user Assessment information in the database
    @PutMapping("/public/updateUserAssessment/{id}")
    public ResponseEntity < UserModel > updateUserAssessment(@Valid @RequestBody UserModel user ,@PathVariable int id) throws IdNotFoundException {
    	user.setEmpID(id);
    	return new ResponseEntity<>(this.userServ.updateUserAssessment(user), HttpStatus.CREATED);
    }
    
      //updating the user Assessment information in the database
//    @PutMapping("/public/updateUserVoucherStatus/{id}")
//    public ResponseEntity < UserModel > updateUserVoucherStatus(@Valid @RequestBody UserModel user ,@PathVariable int id) throws IdNotFoundException {
//    	user.setEmpID(id);
//    	return new ResponseEntity<>(this.userServ.updateUserVoucherStatus(user), HttpStatus.CREATED);
//    }
    
	// /private are accessible to admin only   
    //display the information of all the users available in the database
	@GetMapping("/private/displayAllUsers")
    public ResponseEntity < List < UserModel >> getAllUserData() {
        return ResponseEntity.ok().body(userServ.getAllUserData());
    }

    //display the specific user information based on userID
    @GetMapping("/public/getUser/{id}")
    public ResponseEntity < UserModel > getUserDataByID(@PathVariable int id) throws IdNotFoundException {
        return ResponseEntity.ok().body(userServ.getUserDataByID(id));
    }
	
    //display the specific user information based on user Email
    @GetMapping("/public/getUserByEmail/{email}")
    public ResponseEntity < UserModel > getUserDataByEmail(@PathVariable String email) throws IdNotFoundException {
        return ResponseEntity.ok().body(userServ.getUserDataByEmail(email));
    }
    
    @Generated
    //display the specific user creds based on user Email (testing)
    @GetMapping("/cred/getCredDataById/{email}")
    public ResponseEntity < LoginDetail > getCredDataById(@PathVariable String email) throws IdNotFoundException {
        return ResponseEntity.ok().body(loginServ.getCredDataById(email));
    }
	
	

    //delete the user from the database
    @DeleteMapping("/private/deleteUser/{id}")
    public ResponseEntity<String>  deleteUserDataByID(@PathVariable int id) throws IdNotFoundException {
        this.userServ.deleteUserDataByID(id);
        return new ResponseEntity<>("User data deleted successfully !!", HttpStatus.OK);
    }
    
    //Download imported data excel file
    @GetMapping("/private/download")
    public ResponseEntity<Resource> getFile() {
      String filename = "EmployeesData.xlsx";
      InputStreamResource file = new InputStreamResource(userServ.load());
      return ResponseEntity.ok()
          .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
          .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
          .body(file);
    }
}
