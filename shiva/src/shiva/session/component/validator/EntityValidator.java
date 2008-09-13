package shiva.session.component.validator;

import java.lang.reflect.Field;

import shiva.cfg.Configuration;
import shiva.domain.exception.ObjectTypeNotRegisteredException;
import shiva.domain.exception.ValidationException;
import shiva.domain.metadata.AttributeMapping;

public interface EntityValidator {

	/**
	 * 
	 * 
	 * @param objEntity
	 */
	public void validateEntity(Configuration configuration, Object objEntity) throws ObjectTypeNotRegisteredException;
	
	/**
	 * 
	 * 
	 * 
	 */
	public void validateAttribute(Field attribute, AttributeMapping attributeMapping) throws ValidationException;

}
