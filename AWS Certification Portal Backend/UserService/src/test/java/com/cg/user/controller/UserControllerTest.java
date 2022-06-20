package com.cg.user.controller;


import com.cg.user.model.UserModel;
import com.cg.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userServ;
	private UserModel user;
	private List<UserModel> userList;
    
	@InjectMocks
	private UserController userCtrl;
	
	@BeforeEach
	public void init()
	{
		user = new UserModel(1,"Mitesh Mayavanshi","mitesh@gmail.com",9586107216L,"A4","Mitesh123","2022-03-17","JFS React");
		mockMvc = MockMvcBuilders.standaloneSetup(userCtrl).build();
	}
    
    // JUnit test for createUser REST API
    @DisplayName("JUnit test for createUser REST API")
    @Test
    public void givenUserObject_whenCreateUser_thenReturnSavedUser() throws Exception{

        // given 
        given(userServ.createUser(any(UserModel.class)))
                .willReturn(user);

        // when 
        ResultActions response = mockMvc.perform(post("/api/user/all/signup")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJSONString(user)));

        // then 
        response.andDo(print()).andExpect(status().isCreated());
        

    }
    
    // JUnit test for getAllUserData REST API
    @DisplayName("JUnit test for getAllUserData REST API")
    @Test
    public void givenListOfUsers_whenGetAllUserData_thenReturnUsersList() throws Exception{
    	
        // given 
    	UserModel user1 = new UserModel(2,"Vaibhav Kamthan","vaibhav@gmail.com",7016108807L,"A4","Vaibhav123","2022-03-17","JFS React");
        List<UserModel> listOfUsers = new ArrayList<>();
        listOfUsers.add(user);
        listOfUsers.add(user1);
        given(userServ.getAllUserData()).willReturn(listOfUsers);

        // when 
        ResultActions response = mockMvc.perform(get("/api/user/private/displayAllUsers"));

        // then 
        response.andDo(print()).andExpect(status().isOk());

    }
    
    // JUnit test for getUserDataByID REST API 
    @DisplayName("JUnit test for getUserDataByID REST API")
    @Test
    public void givenUserId_whenGetUserDataByID_thenReturnUserObject() throws Exception{
    	
        // given 
    	int empID = 1;
    	given(userServ.getUserDataByID(empID)).willReturn(user);
    	 
        // when -  action or the behavior that we are going test
        ResultActions response = mockMvc.perform(get("/api/user/public/getUser/{id}", empID));

        // then - verify the output
        response.andDo(print()).andExpect(status().isOk());

    }
    
    // JUnit test for getUserDataByEmail REST API 
    @DisplayName("JUnit test for getUserDataByEmail REST API")
    @Test
    public void givenUserEmail_whenGetUserDataByEmail_thenReturnUserObject() throws Exception{
    	
        // given 
    	String email = "mitesh@gmail.com";
    	given(userServ.getUserDataByEmail(email)).willReturn(user);
    	 
        // when -  action or the behavior that we are going test
        ResultActions response = mockMvc.perform(get("/api/user/public/getUserByEmail/{email}", email));

        // then - verify the output
        response.andDo(print()).andExpect(status().isOk());

    }
    
    // JUnit test for updateUser REST API 
    @DisplayName("JUnit test for updateUser REST API")
    @Test
    public void givenUpdatedUser_whenUpdateUser_thenReturnUpdatedUserObject() throws Exception{
    	
        // given 
    	int empID = 1;
    	UserModel updatedUser = new UserModel(1,"Miteshkumar Mayavanshi","mitesh@gmail.com",9586107216L,"A4","Mitesh123","2022-03-17","JFS React");

    	given(userServ.updateUser(any(UserModel.class))).willReturn(updatedUser);
    	 
        // when -  action or the behavior that we are going test
        ResultActions response = mockMvc.perform(put("/api/user/public/updateUser/{id}",empID)
        						.contentType(MediaType.APPLICATION_JSON)
        						.content(asJSONString(user)));
        System.out.println(response);
        // then - verify the output
        response.andDo(print()).andExpect(status().isCreated());

    }
    
    // JUnit test for updateUserCertification REST API 
    @DisplayName("JUnit test for updateUserCertification REST API")
    @Test
    public void givenUpdatedUser_whenUpdateUserCertification_thenReturnUpdatedUserObject() throws Exception{
    	
        // given 
    	int empID = 1;

    	given(userServ.updateUserCertification(any(UserModel.class))).willReturn(user);
    	 
        // when -  action or the behavior that we are going test
        ResultActions response = mockMvc.perform(put("/api/user/public/updateUserCertification/{id}",empID)
        						.contentType(MediaType.APPLICATION_JSON)
        						.content(asJSONString(user)));
        System.out.println(response);
        // then - verify the output
        response.andDo(print()).andExpect(status().isCreated());

    }
    
    // JUnit test for updateUserAssessment REST API 
    @DisplayName("JUnit test for updateUserAssessment REST API")
    @Test
    public void givenUpdatedUser_whenUpdateUserAssessment_thenReturnUpdatedUserObject() throws Exception{
    	
        // given 
    	int empID = 1;

    	given(userServ.updateUserAssessment(any(UserModel.class))).willReturn(user);
    	 
        // when -  action or the behavior that we are going test
        ResultActions response = mockMvc.perform(put("/api/user/public/updateUserAssessment/{id}",empID)
        						.contentType(MediaType.APPLICATION_JSON)
        						.content(asJSONString(user)));
        System.out.println(response);
        // then - verify the output
        response.andDo(print()).andExpect(status().isCreated());

    }
    
    // JUnit test for deleteUserDataByID REST API
    @DisplayName("JUnit test for deleteUserDataByID REST API")
    @Test
    public void givenUserId_whenDeleteUserDataByID_thenReturnOk() throws Exception{
    	
        // given
        int empID = 1;
        willDoNothing().given(userServ).deleteUserDataByID(empID);

        // when
        ResultActions response = mockMvc.perform(delete("/api/user/private/deleteUser/{id}", empID));

        // then 
        response.andDo(print()).andExpect(status().isOk());
    }
    
	public static String asJSONString(final Object obj) throws JsonProcessingException
	{
		
			return new ObjectMapper().writeValueAsString(obj);
		
	}

}
