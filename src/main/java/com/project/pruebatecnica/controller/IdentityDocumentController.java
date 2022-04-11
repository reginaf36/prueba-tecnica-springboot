package com.project.pruebatecnica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.pruebatecnica.models.AuthenticationRequest;
import com.project.pruebatecnica.models.StatusResponse;
import com.project.pruebatecnica.models.StatusResponseAuthetication;
import com.project.pruebatecnica.models.dtos.IdentityDocumentDTO;
import com.project.pruebatecnica.services.interfaces.AuthenticationService;
import com.project.pruebatecnica.services.interfaces.IdentityDocumentService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class IdentityDocumentController {
	
	@Autowired
	private AuthenticationService userAuthenticationService;
	
	@Autowired
	private IdentityDocumentService identityDocumentService;

	@RequestMapping("/greetings")
	public String greetings() {
		return "Hello World";
	}

	@PostMapping(value = "/authenticate")
	public ResponseEntity<StatusResponseAuthetication> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) 
			throws Exception {
		
		StatusResponseAuthetication response = userAuthenticationService.authenticate(authenticationRequest);
				
		return ResponseEntity.status(response.getStatus()).body(response);
	}
	
	@PostMapping(value = "/documents_ids/save")
	public ResponseEntity<StatusResponse> saveIdentityDocument(@RequestBody IdentityDocumentDTO identityDocumentDTO){
		
		var response = identityDocumentService.saveIdentityDocument(identityDocumentDTO);
		
		return ResponseEntity.status(response.getStatus()).body(response);
		
	}
	
	@GetMapping("/documents_ids/{number}")
	public ResponseEntity<StatusResponse> getIdentityDocumentByNumber(@PathVariable Long number){
		
		var response = identityDocumentService.getIdentityDocumentByNumber(number);
		
		return ResponseEntity.status(response.getStatus()).body(response);
		
	}
	
	@PutMapping("/documents_ids/{number}")
	public ResponseEntity<StatusResponse> updateIdentityDocument(@RequestBody IdentityDocumentDTO newIdentityDocumentDTO, @PathVariable("number") Long currentNumberIdentityDocument){
		
		var response = identityDocumentService.updateIdentityDocument(newIdentityDocumentDTO, currentNumberIdentityDocument);
		
		return ResponseEntity.status(response.getStatus()).body(response);
		
	}
	
	@DeleteMapping("/documents_ids/{number}")
	public ResponseEntity<StatusResponse> deleteIdentityDocument(@PathVariable("number") Long numberIdentityDocument){
		
		var response = identityDocumentService.deleteIdentityDocument(numberIdentityDocument);
		
		return ResponseEntity.status(response.getStatus()).body(response);
		
	}


}
