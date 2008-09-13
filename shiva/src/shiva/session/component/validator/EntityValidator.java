package shiva.session.component.validator;

import java.lang.reflect.Field;

import shiva.cfg.Configuration;
import shiva.cfg.component.mapper.model.metadata.AttributeMapping;
import shiva.session.component.validator.model.exception.ObjectTypeNotRegisteredException;
import shiva.session.component.validator.model.exception.ValidationException;

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
