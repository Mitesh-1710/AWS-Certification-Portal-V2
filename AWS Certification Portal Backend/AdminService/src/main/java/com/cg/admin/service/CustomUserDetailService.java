package com.cg.admin.service;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cg.admin.model.CustomUserDetails;
import com.cg.admin.model.LoginDetail;
import com.cg.admin.repository.LoginDetailsRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

    //injecting bean of LoginDetailsRepository 
	@Autowired
	private LoginDetailsRepository loginRepo;
	
    //authenticating the user if the user data is available in the database or not
	@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		LoginDetail login = loginRepo.findById(email)
               .orElseThrow(() ->
                       new UsernameNotFoundException("User not found with email:" + email));
        return new CustomUserDetails(login);
    }

	
}
