package shiva.session.validator;

import java.lang.reflect.Field;

import shiva.cfg.Configuration;
import shiva.domain.exception.validation.InvalidAttributeValueException;
import shiva.domain.exception.validation.ValidationException;
import shiva.domain.metadata.AttributeMapping;

/**
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
@SuppressWarnings("unchecked")
public interface EntityValidator {
	
	/**
	 * 
	 * @param objEntity
	 * @throws ValidationException
	 */
	public void validateEntityInstance(Object objEntity) throws ValidationException;

	/**
	 * 
	 * @param configuration
	 * @param objEntity
	 * @throws ValidationException
	 */
	public void validateEntityType(Configuration configuration, Object objEntity) throws ValidationException;
	
	/**
	 * 
	 * @param configuration
	 * @param clazz
	 * @throws ValidationException
	 */
	public void validateEntityType(Configuration configuration, Class clazz) throws ValidationException;
	
	/**
	 * 
	 * @param configuration
	 * @param objEntity
	 * @throws ValidationException
	 */
	public void validateEntityState(Configuration configuration, Object objEntity) throws ValidationException;
	
	/**
	 * 
	 * @param configuration
	 * @param objEntity
	 * @param attribute
	 * @param attributeMapping
	 * @throws InvalidAttributeValueException
	 */
	public void validateAttributeState(Configuration configuration, Object objEntity, Field attribute, AttributeMapping attributeMapping) throws InvalidAttributeValueException;

}
