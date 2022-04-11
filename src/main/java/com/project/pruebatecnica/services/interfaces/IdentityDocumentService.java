package com.project.pruebatecnica.services.interfaces;

import com.mongodb.MongoException;
import com.project.pruebatecnica.models.StatusResponse;
import com.project.pruebatecnica.models.dtos.IdentityDocumentDTO;

public interface IdentityDocumentService {
	
	StatusResponse saveIdentityDocument(IdentityDocumentDTO identityDocumentDTO) throws MongoException;
	
	StatusResponse getIdentityDocumentByNumber(Long numberIdentityDocument) throws MongoException;
	
	StatusResponse updateIdentityDocument(IdentityDocumentDTO newIdentityDocument, Long currentNumberIdentityDocument) throws MongoException;
	
	StatusResponse deleteIdentityDocument(Long numberIdentityDocument) throws MongoException;

}
