package com.cg.admin.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.admin.exception.DataAlreadyExistsException;
import com.cg.admin.exception.EmailAlreadyExistsException;
import com.cg.admin.exception.IdNotFoundException;
import com.cg.admin.model.AdminModel;
import com.cg.admin.service.AdminService;

@RestController
//global request mapping url
@RequestMapping("/api/admin")
public class AdminController {

	//injecting bean of AdminService
	@Autowired
	private AdminService adminServ;
	
		//signing up the new admin and storing the information to database
		@PostMapping("/private/createAdmin")
		public ResponseEntity<AdminModel> createAdmin(@Valid @RequestBody AdminModel admin)throws DataAlreadyExistsException,EmailAlreadyExistsException
		{
			return new ResponseEntity<>(adminServ.createAdmin(admin), HttpStatus.CREATED);
			
		}
		
		//display the information of all the admins available in the database
		@GetMapping("/private/displayAllAdmins")
	    public ResponseEntity < List < AdminModel >> getAllAdminData() {
	        return ResponseEntity.ok().body(adminServ.getAllAdminData());
	    }
		
		//display the specific admin information based on adminID
	    @GetMapping("/private/getAdmin/{id}")
	    public ResponseEntity < AdminModel > getAdminDataByID(@PathVariable int id) throws IdNotFoundException {
	        return ResponseEntity.ok().body(adminServ.getAdminDataByID(id));
	    }
		
		//updating the admin information in the database
	    @PutMapping("/private/updateAdmin/{id}")
	    public ResponseEntity <AdminModel > updateAdmin(@Valid @RequestBody AdminModel admin ,@PathVariable int id) throws IdNotFoundException {
	    	admin.setEmpID(id);
	    	return new ResponseEntity<>(this.adminServ.updateAdmin(admin), HttpStatus.CREATED);
	    }

		//display the specific admin information based on adminEmail
	    @GetMapping("/private/getAdminByEmail/{email}")
	    public ResponseEntity < AdminModel > getAdminDataByEmail(@PathVariable String email) throws IdNotFoundException {
	        return ResponseEntity.ok().body(adminServ.getAdminDataByEmail(email));
	    }
		

		//delete the admin from the database
	    @DeleteMapping("/private/deleteAdmin/{id}")
	    public ResponseEntity<String>  deleteAdminDataByID(@PathVariable int id) throws IdNotFoundException {
	        this.adminServ.deleteAdminDataByID(id);
	        return new ResponseEntity<>("Admin data deleted successfully !!", HttpStatus.OK);
	    }
	
}
