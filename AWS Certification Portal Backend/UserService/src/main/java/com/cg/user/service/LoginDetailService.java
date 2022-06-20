package com.cg.user.service;

import com.cg.user.exception.IdNotFoundException;
import com.cg.user.model.LoginDetail;

public interface LoginDetailService {

	public LoginDetail getCredDataById(String email)throws IdNotFoundException;
	
}
