package com.project.pruebatecnica.services.interfaces;

import com.project.pruebatecnica.models.AuthenticationRequest;
import com.project.pruebatecnica.models.StatusResponseAuthetication;

public interface AuthenticationService {
	
	StatusResponseAuthetication authenticate(AuthenticationRequest authenticationRequest) throws Exception;

}
