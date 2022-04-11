package com.project.pruebatecnica.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class AuthenticationResponse {
	
	private final String jwt;

}
