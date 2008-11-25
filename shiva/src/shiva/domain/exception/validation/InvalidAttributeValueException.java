 package shiva.domain.exception.validation;

import java.lang.reflect.Field;

import shiva.domain.validation.logic.ValidationClass;

/**
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
public class InvalidAttributeValueException extends Exception{
	
	private Field attribute;
	private ValidationClass validationClass;

	/**
	 * 
	 */
	private static final long serialVersionUID = 3059306280539983406L;

	public InvalidAttributeValueException() {
		super();
	}
	
	public InvalidAttributeValueException(String message) {
		super(message);
	}
	
	public InvalidAttributeValueException(String message, Field f, ValidationClass vc) {
		super(message);
		this.attribute = f;
		this.validationClass = vc;
	}

	public InvalidAttributeValueException(Field f, ValidationClass vc) {
		super();
		this.attribute = f;
		this.validationClass = vc;
	}

	public ValidationClass getValidationClass() {
		return this.validationClass;
	}

	public Field getAttribute() {
		return this.attribute;
	}
	
}
