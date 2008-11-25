package shiva.domain.validation.logic.impl;

import java.util.HashMap;
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
public class EmailValidation implements ValidationClass {
	
	private static final String EMAIL_REGEX = "^[a-z0-9_\\-]+(\\.[_a-z0-9\\-]+)*@([_a-z0-9\\-]+\\.)+([a-z]{2}|aero|arpa|biz|com|coop|edu|gov|info|int|jobs|mil|museum|name|nato|net|org|pro|travel)$";

	/* (non-Javadoc)
	 * @see shiva.domain.validation.logic.ValidationClass#validate(java.util.Map, java.lang.Object, java.lang.String)
	 */
	@Override
	public void validate( Map<String, Object> parameters, Object attributeValue, String attributeName ) throws InvalidAttributeValueException {
		if( attributeValue == null ){
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
		
		Map<String, Object> p = new HashMap<String, Object>();
		p.put( "value", EMAIL_REGEX );
		
		try{
			new PatternValidation().validate( p, strValue, attributeName );
		}catch (InvalidAttributeValueException e) {
			throw new InvalidAttributeValueException(
				Utils.retrieveMessage( "validator.invalid.email", new Object[]{ attributeName } )
			);
		}
	}

}
