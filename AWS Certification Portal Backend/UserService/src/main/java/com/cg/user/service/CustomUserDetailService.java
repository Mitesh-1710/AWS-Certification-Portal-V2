package com.cg.user.service;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cg.user.model.CustomUserDetails;
import com.cg.user.model.LoginDetail;
import com.cg.user.repository.LoginDetailRepository;

import lombok.Generated;

@Generated
@Service
public class CustomUserDetailService implements UserDetailsService {

    //injecting bean of LoginDetailsRepository
	@Autowired
	private LoginDetailRepository loginRepo;
	
    //authenticating the user if the user data is available in the database or not
	@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		LoginDetail login = loginRepo.findByEmail(email)
               .orElseThrow(() ->
                       new UsernameNotFoundException("User not found with email:" + email));
        return new CustomUserDetails(login);
    }

	
}
