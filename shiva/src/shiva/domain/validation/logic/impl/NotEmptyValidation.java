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
public class NotEmptyValidation implements ValidationClass {

	/* (non-Javadoc)
	 * @see shiva.domain.validation.logic.ValidationClass#validate(java.util.Map, java.lang.Object)
	 */
	@Override
	public void validate( Map<String, Object> parameters, Object attributeValue, String attributeName ) throws InvalidAttributeValueException {
		
		//
		if(attributeValue == null){
			throw new InvalidAttributeValueException(
				Utils.retrieveMessage( "validator.invalid.notnull", new Object[]{ attributeName } )
			);
		}
		
		String strValue = (String) attributeValue;
		strValue = strValue.replaceAll( "\\s", "" );
		
		if( strValue.length() == 0 ){
			throw new InvalidAttributeValueException(
				Utils.retrieveMessage( "validator.invalid.notempty", new Object[]{ attributeName } )
			);
		}
	}

}
