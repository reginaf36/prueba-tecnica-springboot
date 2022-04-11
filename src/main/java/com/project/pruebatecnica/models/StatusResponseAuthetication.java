package com.project.pruebatecnica.models;

import org.springframework.http.HttpStatus;

import com.project.pruebatecnica.models.dtos.IdentityDocumentDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class StatusResponseAuthetication {
	
	private String code;
	
	private String descripcion;
	
	private HttpStatus status;
	
	private String jwt;

}
