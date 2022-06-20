package com.cg.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cg.admin.exception.DataAlreadyExistsException;
import com.cg.admin.exception.EmailAlreadyExistsException;
import com.cg.admin.exception.IdNotFoundException;
import com.cg.admin.model.AdminModel;
import com.cg.admin.model.LoginDetail;
import com.cg.admin.repository.AdminServiceRepository;
import com.cg.admin.repository.LoginDetailsRepository;


@Service
public class AdminServiceImpl implements AdminService {

	//injecting bean of AdminServiceRepository
	@Autowired
	private AdminServiceRepository adminRepo;

	//injecting bean of PasswordEncoder
	@Autowired
	private PasswordEncoder passwordEncoder;

	//injecting bean of LoginDetailsRepository
	@Autowired
	private LoginDetailsRepository loginRepo;
	
	
	@Override
	public AdminModel createAdmin(AdminModel admin) throws DataAlreadyExistsException, EmailAlreadyExistsException {
		if(adminRepo.existsById(admin.getEmpID()))
		{
			throw new DataAlreadyExistsException();
		}
		else if(adminRepo.existsByEmail(admin.getEmail()))
		{
			throw new EmailAlreadyExistsException();
		}
		admin.setPassword(passwordEncoder.encode(admin.getPassword())); //encoding the password to the database for JWT
		LoginDetail login = new LoginDetail(admin.getEmail(),admin.getPassword(),admin.getRole()) ;
		
		loginRepo.save(login);// save login data to database

		return adminRepo.save(admin); // save admin data to database
	}

	@Override
	public AdminModel updateAdmin(AdminModel admin) throws IdNotFoundException {
		Optional<AdminModel> adminDB = this.adminRepo.findById(admin.getEmpID());
		Optional<LoginDetail> loginDB = this.loginRepo.findById(admin.getEmail());
		if(adminDB.isPresent())
		{
			AdminModel updateDB = adminDB.get();
			LoginDetail updateLoginDB = loginDB.get();
			updateDB.setEmpID(admin.getEmpID());
			updateDB.setEmail(admin.getEmail());
			updateDB.setPassword(admin.getPassword());

			updateLoginDB.setEmail(admin.getEmail());
			updateLoginDB.setPassword(admin.getPassword());
			loginRepo.save(updateLoginDB);
			
			return adminRepo.save(updateDB);
		}
		else
		{
			throw new IdNotFoundException();
		}
	}

	@Override
	public List<AdminModel> getAllAdminData() {
		return this.adminRepo.findAll();
	}

	@Override
	public AdminModel getAdminDataByID(int id) throws IdNotFoundException {
		Optional<AdminModel> adminDB = this.adminRepo.findById(id);

        if (adminDB.isPresent()) {
        	
            return adminDB.get();
        } 
        else
        {
        	throw new IdNotFoundException();
        }
	}

	@Override
	public void deleteAdminDataByID(int id) throws IdNotFoundException {
		Optional<AdminModel> adminDB = this.adminRepo.findById(id);

        if (adminDB.isPresent()) {
        	
        	this.adminRepo.delete(adminDB.get());
        } 
        else
        {
        	throw new IdNotFoundException();
        }
		
	}

	@Override
	public AdminModel getAdminDataByEmail(String email) throws IdNotFoundException {
		Optional<AdminModel> adminDB = this.adminRepo.findByEmail(email);

        if (adminDB.isPresent()) {
        	
            return adminDB.get();
        } 
        else
        {
        	throw new IdNotFoundException();
        }
	}



}
