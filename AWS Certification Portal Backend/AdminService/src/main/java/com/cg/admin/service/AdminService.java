package com.cg.admin.service;

import java.util.List;

import com.cg.admin.exception.DataAlreadyExistsException;
import com.cg.admin.exception.EmailAlreadyExistsException;
import com.cg.admin.exception.IdNotFoundException;
import com.cg.admin.model.AdminModel;


public interface AdminService {

	public AdminModel createAdmin(AdminModel admin)throws DataAlreadyExistsException, EmailAlreadyExistsException;
	public AdminModel updateAdmin(AdminModel admin)throws IdNotFoundException;
	public List<AdminModel> getAllAdminData();
	public AdminModel getAdminDataByID(int id)throws IdNotFoundException;
	public void deleteAdminDataByID(int id)throws IdNotFoundException;
	public AdminModel getAdminDataByEmail(String email)throws IdNotFoundException;
}
