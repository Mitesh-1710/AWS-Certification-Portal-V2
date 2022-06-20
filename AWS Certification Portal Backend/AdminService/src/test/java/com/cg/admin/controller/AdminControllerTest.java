package com.cg.admin.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.junit.jupiter.api.*;

import com.cg.admin.model.AdminModel;
import com.cg.admin.service.AdminService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@ExtendWith(MockitoExtension.class)
class AdminControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private AdminService adminSrv;
	private AdminModel admin;
	private List<AdminModel> adminList;
	
	@InjectMocks
	private AdminController adminCtrl;
	
	@BeforeEach
	public void init()
	{
		admin = new AdminModel(104,"edward@gmail.com","Mitesh123");
		mockMvc = MockMvcBuilders.standaloneSetup(adminCtrl).build();
	}
	
	@Test
	void testCreateAdmin() throws JsonProcessingException, Exception {
		 when(adminSrv.createAdmin(any())).thenReturn(admin);
		 mockMvc.perform(post("/api/private/createAdmin")
				 .contentType(MediaType.APPLICATION_JSON) 
				 .content (asJSONString(admin))) 
				 .andExpect(status().isCreated());
		 verify(adminSrv, times (1)).createAdmin(any());
	}
	

	@Test
	void testGetAllAdminData() throws JsonProcessingException, Exception {
		when(adminSrv.getAllAdminData()).thenReturn(adminList); 
		mockMvc.perform(MockMvcRequestBuilders.get("/api/private/displayAllAdmins") 
				.contentType(MediaType.APPLICATION_JSON) 
				.content (asJSONString(admin))) 
				.andDo(MockMvcResultHandlers.print()); 
		verify(adminSrv, times (1)).getAllAdminData();
	}
	
	@Test
	void testGetAllAdminDataByID() throws JsonProcessingException, Exception {
		when(adminSrv.getAdminDataByID(104)).thenReturn(admin); 
		mockMvc.perform(MockMvcRequestBuilders.get("/api//private/getAdmin/104") 
				.contentType(MediaType.APPLICATION_JSON) 
				.content (asJSONString(admin))) 
				.andDo(MockMvcResultHandlers.print()); 
		verify(adminSrv, times (1)).getAdminDataByID(104);
	}
	
	public static String asJSONString(final Object obj) throws JsonProcessingException
	{
		
			return new ObjectMapper().writeValueAsString(obj);
		
	}

}
