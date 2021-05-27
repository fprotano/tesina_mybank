package it.exolab.tesina.mybank.exception;

public class MinLengthError extends FieldError {
	private int minLength;
	public MinLengthError(String field,int minLength) {
		super(field);
		this.minLength=minLength;
	
	}
	public int getMinLength() {
		return minLength;
	}
	
	
}