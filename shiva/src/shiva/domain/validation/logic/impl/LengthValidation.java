package shiva.domain.validation.logic.impl;

import java.util.Map;

import shiva.domain.exception.validation.InvalidAttributeValueException;
import shiva.domain.validation.logic.ValidationClass;
import shiva.util.Utils;

/**
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
public class LengthValidation implements ValidationClass {

	/* (non-Javadoc)
	 * @see shiva.domain.validation.logic.ValidationClass#validate(java.util.Map, java.lang.Object)
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
		Object objMin = parameters.get( "min" );
		if( objMin == null || !(objMin instanceof Integer) ){
			throw new InvalidAttributeValueException(
				"Internal validator error: objMin is null or not an Integer"
			);
		}
		int min = (Integer) objMin;
		
		//
		Object objMax = parameters.get( "max" );
		if( objMin == null || !(objMin instanceof Integer) ){
			throw new InvalidAttributeValueException(
				"Internal validator error: objMax is null or not an Integer"
			);
		}
		int max = (Integer) objMax;
		
		//
		if( attributeValue == null ){
			throw new InvalidAttributeValueException(
				Utils.retrieveMessage( "validator.invalid.notnull", new Object[]{ attributeName } )
			);
		}
		
		String strValue = (String) attributeValue;
		strValue = strValue.trim();
		
		if( strValue.length() < min ){
			throw new InvalidAttributeValueException(
				Utils.retrieveMessage( "validator.invalid.length", new Object[]{ attributeName, min, max } )
			);
		}
		
		if( strValue.length() > max ){
			throw new InvalidAttributeValueException(
				Utils.retrieveMessage( "validator.invalid.length", new Object[]{ attributeName, min, max } )
			);
		}
	}

}
