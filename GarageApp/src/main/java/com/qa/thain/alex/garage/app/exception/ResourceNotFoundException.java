package com.qa.thain.alex.garage.app.exception;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.qa.thain.alex.garage.app.model.Order;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	private String resourceName;
	private static String fieldName;
	private Object fieldValue;
	private String resourceName2;
	private String fieldValue2;
	private static String fieldName2;

	public ResourceNotFoundException(String resourceName,Object fieldValue, @Valid int vehicleId) {
		super(String.format("% not found with %s : '%s'", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;		
	}
	
	public ResourceNotFoundException(String resourceName2, String fieldValue2, @Valid Order orderRequest) {
		super(String.format("% not found with %s : '%s'", resourceName2, fieldName2, fieldValue2));
		this.resourceName2 = resourceName2;
		this.fieldValue2 = fieldValue2;
		this.fieldName2 = fieldName2;		
	}

	public String getResourceName() {
		return resourceName;
	}
	
	public String getFieldName() {
		return fieldName;			
	}
	
	public Object getFieldValue() {
		return fieldValue;
	}
}