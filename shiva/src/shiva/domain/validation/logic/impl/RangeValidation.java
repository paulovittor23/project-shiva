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
public class RangeValidation implements ValidationClass {

	/* (non-Javadoc)
	 * @see shiva.domain.validation.logic.ValidationClass#validate(java.util.Map, java.lang.Object, java.lang.String)
	 */
	@Override
	public void validate( Map<String, Object> parameters, Object attributeValue, String attributeName ) throws InvalidAttributeValueException {
		//
		if(attributeValue == null){
			throw new InvalidAttributeValueException(
				Utils.retrieveMessage( "validator.invalid.notnull", new Object[]{ attributeName } )
			);
		}
		
		Integer intValue = (Integer) attributeValue;
		
		new MinValidation().validate( parameters, intValue, attributeName );
		new MaxValidation().validate( parameters, intValue, attributeName );
	}

}
