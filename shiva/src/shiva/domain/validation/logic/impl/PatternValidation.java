package shiva.domain.validation.logic.impl;

import java.util.Map;
import java.util.regex.Pattern;

import shiva.domain.exception.validation.InvalidAttributeValueException;
import shiva.domain.validation.logic.ValidationClass;
import shiva.util.Utils;

/**
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 *
 */
public class PatternValidation implements ValidationClass {

	/* (non-Javadoc)
	 * @see shiva.domain.validation.logic.ValidationClass#validate(java.util.Map)
	 */
	@Override
	public void validate( Map<String, Object> parameters, Object attributeValue, String attributeName ) throws InvalidAttributeValueException {
		//
		if( parameters == null ){
			throw new InvalidAttributeValueException(
				"Internal validator error: parameters is null"
			);
		}
		
		//
		String p = (String) parameters.get( "value" );
		if( p == null ){
			throw new InvalidAttributeValueException(
				"Internal validator error: value is null"
			);
		}

		//
		if( attributeValue == null ){
			throw new InvalidAttributeValueException(
				Utils.retrieveMessage( "validator.invalid.notnull", new Object[]{ attributeName } )
			);
		}
		String str = (String) attributeValue;
		
		if( ! Pattern.matches( p, str ) ){
			throw new InvalidAttributeValueException(
				Utils.retrieveMessage( "validator.invalid.pattern", new Object[]{ attributeName, p } )
			);
		}
	}

}
