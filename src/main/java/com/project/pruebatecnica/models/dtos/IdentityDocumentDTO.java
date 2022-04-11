package com.project.pruebatecnica.models.dtos;

import java.time.LocalDateTime;

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
public class IdentityDocumentDTO {
	
	private Long number;
	private String expiryDate;
	private LocalDateTime emissionDate;
	private DocumentTypeDTO documentType;

}
