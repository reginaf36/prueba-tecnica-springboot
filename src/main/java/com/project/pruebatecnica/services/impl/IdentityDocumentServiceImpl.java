package com.project.pruebatecnica.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;
import com.project.pruebatecnica.enums.StatusEnum;
import com.project.pruebatecnica.models.DocumentType;
import com.project.pruebatecnica.models.IdentityDocument;
import com.project.pruebatecnica.models.StatusResponse;
import com.project.pruebatecnica.models.dtos.DocumentTypeDTO;
import com.project.pruebatecnica.models.dtos.IdentityDocumentDTO;
import com.project.pruebatecnica.repository.IdentityDocumentRepository;
import com.project.pruebatecnica.services.SequenceGeneratorService;
import com.project.pruebatecnica.services.interfaces.IdentityDocumentService;

import lombok.extern.slf4j.Slf4j;

@Service("IdentityDocumentServiceImpl")
public class IdentityDocumentServiceImpl implements IdentityDocumentService{
	
	@Autowired
	IdentityDocumentRepository repository;
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;

	@Override
	public StatusResponse saveIdentityDocument(IdentityDocumentDTO identityDocumentDTO) throws MongoException {
		
		var statusResponse = new StatusResponse(StatusEnum.SUCCESS.getCode(), StatusEnum.SUCCESS.getDescription(), HttpStatus.OK, null); 
			
		try {			
				
			if(getIdentityDocumentByNumberRepository(identityDocumentDTO.getNumber()) != null) {
				
				statusResponse.setCode(StatusEnum.EXIST_IDENTITY_DOCUMENT.getCode()); 
				statusResponse.setDescripcion(StatusEnum.EXIST_IDENTITY_DOCUMENT.getDescription());
				statusResponse.setStatus(HttpStatus.BAD_REQUEST);
				
				return statusResponse;
				
			}
			
			var documentType = new DocumentType(
					identityDocumentDTO.getDocumentType().getCode(),
					identityDocumentDTO.getDocumentType().getName());

			var identityDocument = new IdentityDocument(
			        sequenceGeneratorService.generateSequence(IdentityDocument.SEQUENCE_NAME), 
			        identityDocumentDTO.getNumber(),
					identityDocumentDTO.getExpiryDate(),
					identityDocumentDTO.getEmissionDate(),
					documentType);
		
			repository.save(identityDocument);		
			
			
		} catch (Exception exception) {
			
			System.out.println(exception.getMessage() + " -- "+ exception.getCause() + " -- " + exception.getStackTrace().toString());
			
			statusResponse.setCode(StatusEnum.FATAL_ERROR.getCode()); 
			statusResponse.setDescripcion(StatusEnum.FATAL_ERROR.getDescription());
			statusResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}	
		
		return statusResponse;
		
	}
	
	public IdentityDocument getIdentityDocumentByNumberRepository(long number) {
		
		try {
		
			var identityDocument = repository.findIdentityDocumentByNumber(number);
			
			return identityDocument.isEmpty() ? null : identityDocument.get(0);
		
		} catch (Exception exception) {
			
			System.out.println(exception.getMessage() + " -- "+ exception.getCause() + " -- " + exception.getStackTrace().toString());
		
			return null;
		}			
	}

	@Override
	public StatusResponse getIdentityDocumentByNumber(Long number) throws MongoException {

		var statusResponse = new StatusResponse(StatusEnum.SUCCESS.getCode(), StatusEnum.SUCCESS.getDescription(), HttpStatus.OK, null);
		
		var identityDocument = getIdentityDocumentByNumberRepository(number);
		
		if( identityDocument == null) {					
			
			statusResponse.setCode(StatusEnum.NOT_FOUND_IDENTITY_DOCUMENT.getCode()); 
			statusResponse.setDescripcion(StatusEnum.NOT_FOUND_IDENTITY_DOCUMENT.getDescription());
			statusResponse.setStatus(HttpStatus.BAD_REQUEST);
			
			return statusResponse;
			
		} else {
			
			var documentTypeDTO = new DocumentTypeDTO(
					identityDocument.getDocumentType().getCode(),
					identityDocument.getDocumentType().getName());

			var identityDocumentDTO = new IdentityDocumentDTO(
			        identityDocument.getNumber(),
					identityDocument.getExpiryDate(),
					identityDocument.getEmissionDate(),
					documentTypeDTO);
			
			statusResponse.setIdentityDocumentDTO(identityDocumentDTO);
			
			return statusResponse;
		
		}
	}

	@Override
	public StatusResponse updateIdentityDocument(IdentityDocumentDTO newIdentityDocumentDTO, Long currentNumberIdentityDocument) throws MongoException {
		
		var statusResponse = new StatusResponse(StatusEnum.SUCCESS_UPDATED.getCode(), StatusEnum.SUCCESS_UPDATED.getDescription(), HttpStatus.OK, null); 
		
		try {	
		
			var identityDocument = getIdentityDocumentByNumberRepository(currentNumberIdentityDocument);			
			
			if( identityDocument == null) {					
				
				statusResponse.setCode(StatusEnum.NOT_FOUND_IDENTITY_DOCUMENT.getCode()); 
				statusResponse.setDescripcion(StatusEnum.NOT_FOUND_IDENTITY_DOCUMENT.getDescription());
				statusResponse.setStatus(HttpStatus.BAD_REQUEST);
				
				
			} else {
				
				var newDocumentType = new DocumentType(
						newIdentityDocumentDTO.getDocumentType().getCode(),
						newIdentityDocumentDTO.getDocumentType().getName());
	
				var newIdentityDocument = new IdentityDocument(
						identityDocument.getId(), 
						newIdentityDocumentDTO.getNumber(),
						newIdentityDocumentDTO.getExpiryDate(),
						newIdentityDocumentDTO.getEmissionDate(),
						newDocumentType);
			
				repository.save(newIdentityDocument);		
				
			}
			
		} catch (Exception exception) {
			
			System.out.println(exception.getMessage() + " -- "+ exception.getCause() + " -- " + exception.getStackTrace().toString());
			
			statusResponse.setCode(StatusEnum.FATAL_ERROR.getCode()); 
			statusResponse.setDescripcion(StatusEnum.FATAL_ERROR.getDescription());
			statusResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}	
		
		return statusResponse;
		
	}

	@Override
	public StatusResponse deleteIdentityDocument(Long numberIdentityDocument) throws MongoException {

		var statusResponse = new StatusResponse(StatusEnum.SUCCESS.getCode(), StatusEnum.SUCCESS.getDescription(), HttpStatus.OK, null);
		
		var identityDocument = getIdentityDocumentByNumberRepository(numberIdentityDocument);
		
		if( identityDocument == null) {					
			
			statusResponse.setCode(StatusEnum.NOT_FOUND_IDENTITY_DOCUMENT.getCode()); 
			statusResponse.setDescripcion(StatusEnum.NOT_FOUND_IDENTITY_DOCUMENT.getDescription());
			statusResponse.setStatus(HttpStatus.BAD_REQUEST);
			
			return statusResponse;
			
		} else {
			
			repository.delete(identityDocument);
			
			return statusResponse;
			
		}
	}
		
	


}
