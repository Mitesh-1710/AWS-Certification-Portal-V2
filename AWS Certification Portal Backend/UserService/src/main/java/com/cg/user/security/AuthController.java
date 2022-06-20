package com.cg.user.security;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.user.model.LoginDetail;

import lombok.Generated;

@Generated
@RestController
@RequestMapping("/api/user")
public class AuthController {

	//injecting bean of AuthenticationManager
	@Autowired
    private AuthenticationManager authenticationManager;
	
	//injecting bean of JwtTokenProvider
	@Autowired
	private JwtTokenProvider tokenProvider;

	//url for login the user and generating the jwt token
	@PostMapping("/all/signin")
	public ResponseEntity<JwtAuthResponse> authenticateUser(@RequestBody LoginDetail login){
			
		    //authenticate the user
	        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
	                login.getEmail(), login.getPassword()));

	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        
	     // get token form tokenProvider
	        String token = tokenProvider.generateToken(authentication);
	        
	        return ResponseEntity.ok(new JwtAuthResponse(token));
	    }
	
}
