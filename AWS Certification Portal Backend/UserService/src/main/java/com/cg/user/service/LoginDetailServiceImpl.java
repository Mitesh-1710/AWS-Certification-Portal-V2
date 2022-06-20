package com.cg.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.user.exception.IdNotFoundException;
import com.cg.user.model.LoginDetail;
import com.cg.user.repository.LoginDetailRepository;

import lombok.Generated;

@Generated
@Service
public class LoginDetailServiceImpl implements LoginDetailService {

	//injecting bean of LoginDetailsRepository
	@Autowired
	private LoginDetailRepository loginRepo;
	
	@Override
	public LoginDetail getCredDataById(String email) throws IdNotFoundException {
		Optional<LoginDetail> loginDB = this.loginRepo.findById(email);
		

        if (loginDB.isPresent()) {
        	
        	return loginDB.get();
        } 
        else
        {
        	throw new IdNotFoundException();
        }
	}

}
