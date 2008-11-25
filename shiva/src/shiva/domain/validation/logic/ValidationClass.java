package shiva.domain.validation.logic;

import java.util.Map;

import shiva.domain.exception.validation.InvalidAttributeValueException;

/**
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
public interface ValidationClass {
	
	/**
	 * 
	 * @param parameters
	 * @return
	 */
	void validate(Map<String, Object> parameters, Object attributeValue, String attributeName) throws InvalidAttributeValueException;
	
}

