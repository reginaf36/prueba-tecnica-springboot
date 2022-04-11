package com.project.pruebatecnica.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("identitydocuments")
public class IdentityDocument {
	
    @Transient
    public static final String SEQUENCE_NAME = "identity_sequence";
	
	@Id
	private long id;
	
	private Long number;
	private String expiryDate;
	private LocalDateTime emissionDate;
	private DocumentType documentType;
}
