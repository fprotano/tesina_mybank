package it.exolab.tesina.mybank.exception;

public class MinValueError extends FieldError {
	
	private int min;
	
	public MinValueError(String field,int min) {
		super(field);
		this.min=min;
	
	}
	public int getMin() {
		return min;
	}
	
	
}