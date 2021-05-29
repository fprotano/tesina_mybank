package it.exolab.tesina.mybank.util;

import java.time.LocalDate;

import it.exolab.tesina.mybank.exception.InvalidEmail;
import it.exolab.tesina.mybank.exception.InvalidPassword;
import it.exolab.tesina.mybank.exception.MaxLengthError;
import it.exolab.tesina.mybank.exception.MinLengthError;
import it.exolab.tesina.mybank.exception.RequiredFieldError;

public class Validator {
	public void required(String field,String value) throws RequiredFieldError {
		if(Utils.isNullOrEmpty(value))
			throw new RequiredFieldError(field);
	}
	
	public void maxLength(String field , String value , int maxLength) throws MaxLengthError {
		if(value != null && value.length()>maxLength)
			throw new MaxLengthError(field,maxLength);
		
	}
	
	public void minLength(String field , String value, int minLength) throws MinLengthError {
		if(value != null && value.length()<minLength)
			throw new MinLengthError(field,minLength);	
	}
	
	
	public void validatePassword(String field, String value) throws InvalidPassword {
		if(Utils.validatePassword(value))
			throw new InvalidPassword(field);
	}
	
	public void validateEmail(String field , String value ) throws InvalidEmail {
		if(Utils.validateEmail(value))
			throw new InvalidEmail(field);
		
	}
	public void validatePrezzo(String field, Double value ) throws RequiredFieldError {
		if(Utils.isNullOrEmpty(value))
			throw new RequiredFieldError(field);
	}
	
	
	public void required(String field , LocalDate value) throws RequiredFieldError {
		if(Utils.isNullOrEmpty(value))
			throw new RequiredFieldError(field);
		
	}
	
	
	
	
}
