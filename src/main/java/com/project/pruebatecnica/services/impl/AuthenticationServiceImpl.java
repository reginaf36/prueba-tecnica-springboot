package com.project.pruebatecnica.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.project.pruebatecnica.enums.StatusEnum;
import com.project.pruebatecnica.models.AuthenticationRequest;
import com.project.pruebatecnica.models.StatusResponseAuthetication;
import com.project.pruebatecnica.services.MyUserDetailsService;
import com.project.pruebatecnica.services.interfaces.AuthenticationService;
import com.project.pruebatecnica.util.JwtUtil;

@Service("AuthenticationServiceImpl")
public class AuthenticationServiceImpl implements AuthenticationService {
	
	@Autowired
	private AuthenticationManager autheticationManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;

	@Override
	public StatusResponseAuthetication authenticate(AuthenticationRequest authenticationRequest) throws Exception {
		
		var statusResponseAuthetication = new StatusResponseAuthetication(StatusEnum.SUCCESS.getCode(), StatusEnum.SUCCESS.getDescription(), HttpStatus.OK, null);
		
		try {			
				
			autheticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
			
			final var userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

			statusResponseAuthetication.setJwt(jwtTokenUtil.generateToken(userDetails));			
			
		} catch (BadCredentialsException e) {
			
			statusResponseAuthetication.setCode(StatusEnum.AUTHETICATION_ERROR.getCode()); 
			statusResponseAuthetication.setDescripcion(StatusEnum.AUTHETICATION_ERROR.getDescription());
			statusResponseAuthetication.setStatus(HttpStatus.BAD_REQUEST);					
			
		} catch (Exception e) {

			statusResponseAuthetication.setCode(StatusEnum.FATAL_ERROR.getCode()); 
			statusResponseAuthetication.setDescripcion(StatusEnum.FATAL_ERROR.getDescription());
			statusResponseAuthetication.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);			

		}
		
		return statusResponseAuthetication;
		
	}

}
