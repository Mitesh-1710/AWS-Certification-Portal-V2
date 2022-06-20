package com.cg.user.service;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cg.user.excel.ExcelHelper;
import com.cg.user.exception.DataAlreadyExistsException;
import com.cg.user.exception.EmailAlreadyExistsException;
import com.cg.user.exception.IdNotFoundException;
import com.cg.user.model.BatchDetailModel;
import com.cg.user.model.LoginDetail;
import com.cg.user.model.UserModel;
import com.cg.user.repository.BatchDetailRepository;
import com.cg.user.repository.LoginDetailRepository;
import com.cg.user.repository.UserRepository;

import lombok.Generated;

@Service
public class UserServiceImpl implements UserService{

	//injecting bean of UserRepository
	@Autowired
	private UserRepository userRepo;

	//injecting bean of PasswordEncoder
	@Autowired
	private PasswordEncoder passwordEncoder;

	//injecting bean of LoginDetailsRepository
	@Autowired
	private LoginDetailRepository loginRepo;
		
	//injecting bean of BatchDetailRepository
	@Autowired
	private BatchDetailRepository bacthRepo;
	
	@Override
	public UserModel createUser(UserModel user) throws DataAlreadyExistsException, EmailAlreadyExistsException {
	
		if(userRepo.existsById(user.getEmpID()))
		{
			throw new DataAlreadyExistsException();
		}
		else if(userRepo.existsByEmail(user.getEmail()))
		{
			throw new EmailAlreadyExistsException();
		}
		user.setPassword(passwordEncoder.encode(user.getPassword())); //encoding the password to the database for JWT
		LoginDetail login = new LoginDetail(user.getEmail(),user.getPassword(),user.getRole()) ;
		
		
		//updating user database based on batch name selected
		Optional<BatchDetailModel> batchDB = this.bacthRepo.findById(user.getBatchName());
		BatchDetailModel useBatchDB = batchDB.get(); 
		
		
		user.setTrainedBy(useBatchDB.getTrainedBy());
		user.setMentorName(useBatchDB.getMentorName());
		user.setMentorEmail(useBatchDB.getMentorEmail());
		user.setReportingManagerName(useBatchDB.getReportingManagerName());
		user.setReportingManagerEmail(useBatchDB.getReportingManagerEmail());
	
		//Determining user status in the company (Fresher/Lateral)
		LocalDate joiningDate = LocalDate.parse(user.getJoiningDate());
		LocalDate freshersTimePeriodEnd = joiningDate.plusDays(182);
		LocalDate currentDate = LocalDate.now();
		
		if(currentDate.isAfter(freshersTimePeriodEnd))
		{
			user.setStatus("Lateral");
		}
		else
		{
			user.setStatus("Fresher");
		}
		
		loginRepo.save(login); // save login data to database
		return userRepo.save(user); // save user data to database
	}

	@Override
	public UserModel updateUser(UserModel user) throws IdNotFoundException {

		Optional<UserModel> userDB = this.userRepo.findById(user.getEmpID());
		Optional<LoginDetail> loginDB = this.loginRepo.findById(user.getEmail());
		if(userDB.isPresent())
		{
			UserModel updateDB = userDB.get();
			LoginDetail updateLoginDB = loginDB.get();
			
			updateDB.setEmpName(user.getEmpName());
			updateDB.setEmail(user.getEmail());
			updateDB.setContactNumber(user.getContactNumber());
			updateDB.setGrade(user.getGrade());
			updateDB.setPassword(passwordEncoder.encode(user.getPassword()));
			updateDB.setBatchName(user.getBatchName());
			
			//updating user status in the company (Fresher/Lateral)
			LocalDate joiningDate = LocalDate.parse(user.getJoiningDate());
			LocalDate freshersTimePeriodEnd = joiningDate.plusDays(182);
			LocalDate currentDate = LocalDate.now();
			
			if(currentDate.isAfter(freshersTimePeriodEnd))
			{
				updateDB.setStatus("Lateral");
			}
			else
			{
				updateDB.setStatus("Fresher");
			}
			
			updateLoginDB.setEmail(user.getEmail());
			updateLoginDB.setPassword(passwordEncoder.encode(user.getPassword()));
			loginRepo.save(updateLoginDB);
			
			return userRepo.save(updateDB);
		}
		else
		{
			throw new IdNotFoundException();
		}
	
		
	}

	@Override
	public UserModel updateUserCertification(UserModel user) throws IdNotFoundException {
	
		Optional<UserModel> userDB = this.userRepo.findById(user.getEmpID());
		if(userDB.isPresent())
		{
			UserModel updateDB = userDB.get();
			
			updateDB.setCertificationName(user.getCertificationName());
			updateDB.setEnrolledStatus("Enrolled");
			
			LocalDate currentDate = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
			updateDB.setEnrolledDate((formatter.format(currentDate)).toString());
			
			//updating deadline date
			LocalDate enrolledDate = currentDate;
			int days = Integer.parseInt(user.getDeadlineDate());
			LocalDate endDate = enrolledDate.plusDays(days);
			
			updateDB.setDeadlineDate(endDate.toString());//converting end date to string then updating the value
			
			
			return userRepo.save(updateDB);
		}
		else
		{
			throw new IdNotFoundException();
		}
	}

	@Generated
	@Override
	public UserModel updateUserAssessment(UserModel user) throws IdNotFoundException {
		
		Optional<UserModel> userDB = this.userRepo.findById(user.getEmpID());
		if(userDB.isPresent())
		{
			UserModel updateDB = userDB.get();
			
			updateDB.setNumberOfAttempts(user.getNumberOfAttempts());
			
			if(user.getNumberOfAttempts() == 0)
			{
				updateDB.setNumberOfAttempts(user.getNumberOfAttempts()+1);
				updateDB.setFirstAttempt(user.getFirstAttempt());
				if(user.getFirstAttempt() >= 85)
				{
					updateDB.setVoucherStatus("Shared");
					updateDB.setCertificationStatus("Passed");
				}
			}
			if(user.getNumberOfAttempts() == 1)
			{
				updateDB.setSecondAttempt(user.getFirstAttempt());
				updateDB.setNumberOfAttempts(user.getNumberOfAttempts()+1);
				if(user.getFirstAttempt() >= 85)
				{
					updateDB.setVoucherStatus("Shared");
					updateDB.setCertificationStatus("Passed");
				}
			}
			if(user.getNumberOfAttempts() == 2)
			{
				updateDB.setThirdAttempt(user.getFirstAttempt());
				updateDB.setNumberOfAttempts(user.getNumberOfAttempts()+1);
				if(user.getFirstAttempt() >= 85)
				{
					updateDB.setVoucherStatus("Shared");
					updateDB.setCertificationStatus("Passed");
				}
				else
				{
					updateDB.setCertificationStatus("Failed");
				}
			}
			
			
			
			return userRepo.save(updateDB);
		}
		else
		{
			throw new IdNotFoundException();
		}
	}

//	@Override
//	public UserModel updateUserVoucherStatus(UserModel user) throws IdNotFoundException {
//		Optional<UserModel> userDB = this.userRepo.findById(user.getEmpID());
//		if(userDB.isPresent())
//		{
//			UserModel updateDB = userDB.get();
//			
//			updateDB.setVoucherStatus("Shared");
//
//			return userRepo.save(updateDB);
//		}
//		else
//		{
//			throw new IdNotFoundException();
//		}
//	}

	@Override
	public List<UserModel> getAllUserData() {
		return this.userRepo.findAll();
	}

	@Override
	public UserModel getUserDataByID(int id) throws IdNotFoundException {

		Optional<UserModel> userDB = this.userRepo.findById(id);

        if (userDB.isPresent()) {
        	
            return userDB.get();
        } 
        else
        {
        	throw new IdNotFoundException();
        }
	}

	@Override
	public UserModel getUserDataByEmail(String email) throws IdNotFoundException {

		Optional<UserModel> userDB = this.userRepo.findByEmail(email);

        if (userDB.isPresent()) {
        	
            return userDB.get();
        } 
        else
        {
        	throw new IdNotFoundException();
        }
	}

	@Override
	public void deleteUserDataByID(int id) throws IdNotFoundException {
		
		Optional<UserModel> userDB = this.userRepo.findById(id);
		

        if (userDB.isPresent()) {
        	
        	this.userRepo.delete(userDB.get());
        } 
        else
        {
        	throw new IdNotFoundException();
        }
		
	}

	
	// Populating all user data to excel
	@Generated
	@Override
	public ByteArrayInputStream load() {
		List<UserModel> users = userRepo.findAll();
		ByteArrayInputStream in = ExcelHelper.usersToExcel(users);
		return in;
	}


}
