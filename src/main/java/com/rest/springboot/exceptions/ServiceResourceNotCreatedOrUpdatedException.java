package com.rest.springboot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ServiceResourceNotCreatedOrUpdatedException extends RuntimeException
{
	private String fieldName;
    private String fieldValue;


    public ServiceResourceNotCreatedOrUpdatedException( String fieldName, String fieldValue) {
        super(String.format("resource not created/updated ", fieldName, fieldValue));
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }
}
