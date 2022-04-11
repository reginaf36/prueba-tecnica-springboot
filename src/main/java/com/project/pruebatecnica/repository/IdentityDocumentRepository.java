package com.project.pruebatecnica.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.pruebatecnica.models.IdentityDocument;

@Repository
public interface IdentityDocumentRepository extends MongoRepository<IdentityDocument, Long> {
	
	@Query(value="{ 'number' : ?0 }", fields="{ 'number' : 1, 'expiryDate' : 1, 'emissionDate' : 1, 'documentType' : 1 }")
	List<IdentityDocument> findIdentityDocumentByNumber(Long number);
	
	public long count();	

}
