package com.cg.user.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.mockito.BDDMockito.willDoNothing;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.cg.user.exception.DataAlreadyExistsException;
import com.cg.user.exception.EmailAlreadyExistsException;
import com.cg.user.exception.IdNotFoundException;
import com.cg.user.model.BatchDetailModel;
import com.cg.user.model.LoginDetail;
import com.cg.user.model.UserModel;
import com.cg.user.repository.BatchDetailRepository;
import com.cg.user.repository.LoginDetailRepository;
import com.cg.user.repository.UserRepository;


@ExtendWith(MockitoExtension.class)
class UserServiceTests {

	@Mock
	private UserRepository userRepo;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@Mock
	private LoginDetailRepository loginRepo;
	
	@Mock
	private BatchDetailRepository batchRepo;
	
	@InjectMocks
	private UserServiceImpl userServ;
	
	
	private UserModel user;
	private BatchDetailModel batch;
	private LoginDetail login;
	
	@BeforeEach
	public void init(){
		user = new UserModel(1,"Mitesh Mayavanshi","mitesh@gmail.com",9586107216L,"A4","Mitesh123","2022-03-17","JFS React");
	}
	
    // JUnit test for createUser method
    @DisplayName("JUnit test for createUser method")
    @Test
    public void givenUserId_whenSaveUser_thenReturnUserObject() throws DataAlreadyExistsException, EmailAlreadyExistsException{
     
    	// given 
    	batch = new BatchDetailModel("JFS React","iTransform","PV Sasirekha","pv.sasirekha@gmail.com","Sk Shilpa","sk.shilpa@gmail.com"); 
    	given(batchRepo.findById(user.getBatchName())).willReturn(Optional.of(batch));
    	given(userRepo.save(user)).willReturn(user);
    	given(userRepo.existsById(user.getEmpID())).willReturn(false);
        given(userRepo.existsByEmail(user.getEmail())).willReturn(false);
        
        // when 
        UserModel savedUser = userServ.createUser(user);

        // then
        assertThat(savedUser).isNotNull();
    }
	

    // JUnit test for createUser method which throws email exists exception
    @DisplayName("JUnit test for createUser method which  throws EmailAlreadyExistsException exception")
    @Test
    public void givenUserId_whenSaveUser_thenThrowsEmailExistException(){
     
    	// given 
        given(userRepo.existsByEmail(user.getEmail())).willReturn(true);
        
        // when 
        org.junit.jupiter.api.Assertions.assertThrows(EmailAlreadyExistsException.class, () -> {
            userServ.createUser(user);
        });

        // then
        verify(userRepo, never()).save(any(UserModel.class));
    }
    
    // JUnit test for createUser method which throws data exsist exception
    @DisplayName("JUnit test for createUser method which throws DataAlreadyExistsException exception")
    @Test
    public void givenUserId_whenSaveUser_thenThrowsDataExistException(){
     
    	// given 
        given(userRepo.existsById(user.getEmpID())).willReturn(true);
        
        // when 
        org.junit.jupiter.api.Assertions.assertThrows(DataAlreadyExistsException.class, () -> {
            userServ.createUser(user);
        });

        // then
        verify(userRepo, never()).save(any(UserModel.class));
    }
    

    // JUnit test for getAllUserData method
    @DisplayName("JUnit test for getAllUserData method")
    @Test
    public void givenUserList_whenGetAllUserData_thenReturnUsersList(){
    	
        // given 
    	UserModel user1 = new UserModel(2,"Vaibhav Kamthan","vaibhav@gmail.com",7016108807L,"A4","Vaibhav123","2022-03-17","JFS React");		
        given(userRepo.findAll()).willReturn(List.of(user,user1));

        // when 
        List<UserModel> userList = userServ.getAllUserData();

        // then
        assertThat(userList).isNotNull();
        assertThat(userList.size()).isEqualTo(2);
    }
    
    // JUnit test for getAllUserData method if list is empty
    @DisplayName("JUnit test for getAllUserData method when list is empty")
    @Test
    public void givenUserListEmpty_whenGetAllUserData_thenReturnUsersList(){
    	
        // given 
        given(userRepo.findAll()).willReturn(Collections.emptyList());

        // when 
        List<UserModel> userList = userServ.getAllUserData();

        // then
        assertThat(userList).isEmpty();
        assertThat(userList.size()).isEqualTo(0);
    }
    

    // JUnit test for getUserDataByID method
    @DisplayName("JUnit test for getUserDataByID method")
    @Test
    public void givenUserId_whenGetUserDataByID_thenReturnUserObject() throws IdNotFoundException{
    	
        // given
        given(userRepo.findById(1)).willReturn(Optional.of(user));

        // when
        UserModel savedUser = userServ.getUserDataByID(user.getEmpID());

        // then
        assertThat(savedUser).isNotNull();

    }
    

    // JUnit test for getUserDataByEmail method
    @DisplayName("JUnit test for getUserDataByEmail method")
    @Test
    public void givenUserEmail_whenGetUserDataByEmail_thenReturnUserObject() throws IdNotFoundException{
    	
        // given
        given(userRepo.findByEmail("mitesh@gmail.com")).willReturn(Optional.of(user));

        // when
        UserModel savedUser = userServ.getUserDataByEmail(user.getEmail());

        // then
        assertThat(savedUser).isNotNull();

    }
    
    // JUnit test for getUserDataByEmail method which throws Id not found exception
    @DisplayName("JUnit test for getUserDataByEmail method when email not found")
    @Test
    public void givenUserEmail_whenGetUserDataByEmail_thenThrowsIdNotFoundException() throws IdNotFoundException{
    	
    	// given then
        given(userRepo.findByEmail("mitesh1@gmail.com")).willReturn(Optional.empty());

        // when
        org.junit.jupiter.api.Assertions.assertThrows(IdNotFoundException.class, () -> {
            userServ.getUserDataByEmail("mitesh1@gmail.com");
        });

    }
    
    // JUnit test for updateUser method
    @DisplayName("JUnit test for updateUser method")
    @Test
    public void givenUserObject_whenUpdateUser_thenReturnUpdatedUser() throws IdNotFoundException{
    	
        // given 
    	login = new LoginDetail("mitesh@gmail.com",user.getPassword(),user.getRole());
    	given(loginRepo.save(login)).willReturn(login);
    	given(userRepo.findById(user.getEmpID())).willReturn(Optional.of(user));
    	given(loginRepo.findById(login.getEmail())).willReturn(Optional.of(login));
        given(userRepo.save(user)).willReturn(user);
        user.setEmpID(user.getEmpID());
        user.setEmail(user.getEmail());
        user.setEmpName("Miteshkumar Mayavanshi");
        user.setContactNumber(user.getContactNumber());
        user.setGrade(user.getGrade());
        user.setBatchName(user.getBatchName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCertificationStatus(user.getCertificationStatus());
        user.setJoiningDate(user.getJoiningDate());
        user.setStatus(user.getStatus());
        user.setTrainedBy(user.getTrainedBy());
        user.setMentorName(user.getMentorName());
        user.setMentorEmail(user.getMentorEmail());
        user.setReportingManagerName(user.getReportingManagerName());
        
        user.setReportingManagerEmail(user.getReportingManagerEmail());
        user.setEnrolledStatus(user.getEnrolledStatus());
        user.setEnrolledDate(user.getEnrolledDate());
        user.setNumberOfAttempts(user.getNumberOfAttempts());
        user.setFirstAttempt(user.getFirstAttempt());
        
        user.setSecondAttempt(user.getSecondAttempt());
        user.setThirdAttempt(user.getThirdAttempt());
        user.setVoucherStatus(user.getVoucherStatus());
        
        login.setEmail(user.getEmail());
        login.setPassword(passwordEncoder.encode(user.getPassword()));
       
        // when
        userServ.updateUser(user);

        // then 
        assertThat(user.getEmpName()).isEqualTo("Miteshkumar Mayavanshi");
        
    }
    
    // JUnit test for updateUserCertification method
    @DisplayName("JUnit test for updateUserCertification method")
    @Test
    public void givenUserObject_whenUapdateUserCertification_thenReturnUpdatedUser() throws IdNotFoundException{
    	
        // given when
    	given(userRepo.findById(user.getEmpID())).willReturn(Optional.of(user));
        given(userRepo.save(user)).willReturn(user);
        user.setCertificationName("AWS Cloud Practitioner");
		user.setEnrolledStatus("Enrolled");
		user.setDeadlineDate("21");
 
        // then
        userServ.updateUserCertification(user);
        
    }
    
 // JUnit test for deleteUserDataByID method
    @DisplayName("JUnit test for deleteUserDataByID method")
    @Test
    public void givenUserId_whenDeleteUserDataByID_thenNothing() throws IdNotFoundException{
    	
        // given 
    	given(userRepo.findById(user.getEmpID())).willReturn(Optional.of(user));

        // when -  action or the behaviour that we are going test
        userServ.deleteUserDataByID(user.getEmpID());


        // then - verify the output
        verify(userRepo, times(1)).delete(user);
    }


}
