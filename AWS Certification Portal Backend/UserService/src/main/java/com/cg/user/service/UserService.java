package com.cg.user.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.cg.user.exception.DataAlreadyExistsException;
import com.cg.user.exception.EmailAlreadyExistsException;
import com.cg.user.exception.IdNotFoundException;
import com.cg.user.model.LoginDetail;
import com.cg.user.model.UserModel;

public interface UserService {

	public UserModel createUser(UserModel user)throws DataAlreadyExistsException, EmailAlreadyExistsException;
	public UserModel updateUser(UserModel user)throws IdNotFoundException;
	public UserModel updateUserCertification(UserModel user)throws IdNotFoundException;
	public UserModel updateUserAssessment(UserModel user)throws IdNotFoundException;
//	public UserModel updateUserVoucherStatus(UserModel user)throws IdNotFoundException;
	public List<UserModel> getAllUserData();
	public UserModel getUserDataByID(int id)throws IdNotFoundException;
	public UserModel getUserDataByEmail(String email)throws IdNotFoundException;
	public void deleteUserDataByID(int id)throws IdNotFoundException;
	public ByteArrayInputStream load();
}
