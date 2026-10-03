package com.ecommerce.shipping.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecommerce.core.dto.ApiResponse;
import com.ecommerce.core.exception.BusinessException;

@RestControllerAdvice
public class ShippingControllerAdvice {
	
	    @ExceptionHandler(BusinessException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    public ApiResponse<String> handleBusiness(BusinessException ex) {
	        return ApiResponse.failure(ex.getMessage(), null);
	    }

	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    public ApiResponse<String> handleValidation(MethodArgumentNotValidException ex) {
	        return ApiResponse.failure("Validation failed: " + ex.getBindingResult().getFieldError().getDefaultMessage(), null);
	    }

	    @ExceptionHandler(Exception.class)
	    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	    public ApiResponse<String> handleGeneric(Exception ex) {
	        return ApiResponse.failure("Something went wrong: " + ex.getMessage(), null);
	    }
	
}
