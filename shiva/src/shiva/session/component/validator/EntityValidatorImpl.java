package shiva.session.component.validator;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import shiva.cfg.Configuration;
import shiva.cfg.component.mapper.model.metadata.AttributeMapping;
import shiva.cfg.component.mapper.model.metadata.EntityMapping;
import shiva.session.component.validator.model.exception.ObjectTypeNotRegisteredException;
import shiva.session.component.validator.model.exception.ValidationException;

@SuppressWarnings("unchecked")
public class EntityValidatorImpl implements EntityValidator {
	
	private Logger logger = Logger.getLogger(this.getClass());

	/*
	 * (non-Javadoc)
	 * @see jldap.core.session.component.validator.EntityValidator#validateEntity(java.lang.Object)
	 */
	public void validateEntity(Configuration configuration, Object objEntity) throws ObjectTypeNotRegisteredException {

		Class objClazz = objEntity.getClass();

		Set<Class> regEntityClasses = configuration.getEntityRegistrator().getRegisteredObjects();
		boolean isRegistered = regEntityClasses.contains(objClazz);
		
		if( ! isRegistered ){
			//
			throw new ObjectTypeNotRegisteredException("Object type not registered");
		}
		
		//fields validation
		EntityMapping entityMapping = configuration.getEntityMappings().get( objClazz );
		Map<Field, AttributeMapping> attributesMapped = entityMapping.getAttributesMapped();
		
		Set<Field> keySet = attributesMapped.keySet();
		for (Field attribute : keySet) {
			
			AttributeMapping attributeMapping = attributesMapped.get(attribute);
			
			try {
				
				this.validateAttribute(attribute, attributeMapping);
				
			} catch (ValidationException e) {
				logger.error("validateEntity is not ok :(", e);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see jldap.session.component.validator.EntityValidator#validateAttribute(java.lang.reflect.Field, jldap.cfg.component.mapper.model.metadata.AttributeMapping)
	 */
	public void validateAttribute(Field attribute, AttributeMapping attributeMapping) throws ValidationException{
		
	}

}
