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
public class MaxValidation implements ValidationClass {

	/* (non-Javadoc)
	 * @see shiva.domain.validation.logic.ValidationClass#validate(java.util.Map, java.lang.Object, java.lang.String)
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
		Object objMax = parameters.get( "max" );
		if( objMax == null || !(objMax instanceof Integer) ){
			throw new InvalidAttributeValueException(
				"Internal validator error: objMax is null or not an Integer"
			);
		}
		int max = (Integer) objMax;
		
		//
		if(attributeValue == null){
			throw new InvalidAttributeValueException(
				Utils.retrieveMessage( "validator.invalid.notnull", new Object[]{ attributeName } )
			);
		}
		
		Integer intValue = (Integer) attributeValue;
		
		if( intValue > max ){
			throw new InvalidAttributeValueException(
				Utils.retrieveMessage( "validator.invalid.max", new Object[]{ attributeName, max } )
			);
		}
	}

}
