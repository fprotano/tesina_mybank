package it.exolab.tesina.mybank.exception;

public class EntityNotFoundError extends FieldError{
	public EntityNotFoundError(String field) {
		super(field);
	}
}
