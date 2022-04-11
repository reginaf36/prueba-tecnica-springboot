package com.project.pruebatecnica.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum StatusEnum {
	
    SUCCESS("200", "Ejecucion con exito.", HttpStatus.OK),
    SUCCESS_UPDATED("200", "Documento de Identidad actualizado correctamente.", HttpStatus.OK),
    FATAL_ERROR("500", "Error inesperado durante la ejecucion, contacte al administrador", HttpStatus.INTERNAL_SERVER_ERROR),
    AUTHETICATION_ERROR("400", "Incorrect username o password", HttpStatus.BAD_REQUEST),
    EXIST_IDENTITY_DOCUMENT("400", "Documento de identidad existente", HttpStatus.BAD_REQUEST),
	NOT_FOUND_IDENTITY_DOCUMENT("400", "Documento de Identidad No encontrado", HttpStatus.BAD_REQUEST);
	
    private final String code;
    private final String description;
    private final HttpStatus status;

}
