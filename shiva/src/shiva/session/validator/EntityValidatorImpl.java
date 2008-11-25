package shiva.session.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import shiva.cfg.Configuration;
import shiva.domain.exception.validation.InvalidAttributeValueException;
import shiva.domain.exception.validation.ValidationException;
import shiva.domain.metadata.AttributeMapping;
import shiva.domain.metadata.EntityMapping;
import shiva.domain.validation.logic.ValidationClass;
import shiva.util.Utils;

/**
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
@SuppressWarnings("unchecked")
public class EntityValidatorImpl implements EntityValidator {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	/* (non-Javadoc)
	 * @see shiva.session.validator.EntityValidator#validateEntityInstance(java.lang.Object)
	 */
	@Override
	public void validateEntityInstance( Object objEntity ) throws ValidationException {
		if( objEntity == null ){
			throw new ValidationException("Entity instance is null");
		}		
	}
	
	/* (non-Javadoc)
	 * @see shiva.session.validator.EntityValidator#validateEntityType(shiva.cfg.Configuration, java.lang.Object)
	 */
	@Override
	public void validateEntityType( Configuration config, Object objEntity ) throws ValidationException {
		logger.info( "starting entity type validation" );

		Class objClazz = objEntity.getClass();
		logger.info( "entity class: " + objClazz.getCanonicalName() );

		Set<Class> regEntityClasses = config.getEntityRegistrator().getRegisteredObjects();
		boolean isRegistered = regEntityClasses.contains(objClazz);
		
		if( ! isRegistered ){
			//
			logger.info( "entity class not registered!" );
			logger.info( "finishing entity type validation" );
			throw new ValidationException("Object type not registered");
		}
		
		logger.info( "entity class is registered! :)" );
		logger.info( "finishing entity type validation" );
	}
	
	/* (non-Javadoc)
	 * @see shiva.session.validator.EntityValidator#validateEntityType(shiva.cfg.Configuration, java.lang.Class)
	 */
	@Override
	public void validateEntityType( Configuration config, Class clazz ) throws ValidationException {
		logger.info( "starting entity type validation" );

		if( clazz == null ){
			//
			logger.info( "entity type is null!" );
			throw new ValidationException("Entity type is null");
		}		
		
		logger.info( "entity class: " + clazz.getCanonicalName() );

		Set<Class> regEntityClasses = config.getEntityRegistrator().getRegisteredObjects();
		boolean isRegistered = regEntityClasses.contains(clazz);
		
		if( ! isRegistered ){
			//
			logger.info( "entity class not registered!" );
			logger.info( "finishing entity type validation" );
			throw new ValidationException("Object type not registered");
		}
		
		logger.info( "entity class is registered! :)" );
		logger.info( "finishing entity type validation" );
	}

	/* (non-Javadoc)
	 * @see shiva.session.validator.EntityValidator#validateEntityState(shiva.cfg.Configuration, java.lang.Object)
	 */
	public void validateEntityState(Configuration config, Object objEntity) throws ValidationException {
		logger.info( "starting entity state validation" );

		Class objClazz = objEntity.getClass();
		
		//fields validation
		logger.info( "starting attributes state validation" );
		EntityMapping entityMapping = config.getEntityMappings().get( objClazz );
		Map<Field, AttributeMapping> attributesMapped = entityMapping.getAttributesMapped();
		
		//
		ValidationException ve = null;
		
		Set<Field> keySet = attributesMapped.keySet();
		for (Field att : keySet) {
			
			AttributeMapping am = attributesMapped.get(att);
			
			try {
				
				this.validateAttributeState(config, objEntity, att, am);
				
			} catch (InvalidAttributeValueException e) {
				
				if(ve == null){
					ve = new ValidationException();
				}

				//
				ve.addAttributeException( e );
			}
		}
		
		//validation failed
		if ( ve != null ) {
			throw ve;
		}
		
		logger.info( "finishing attributes state validation" );
		logger.info( "finishing entity state validation" );
	}

	/* (non-Javadoc)
	 * @see shiva.session.validator.EntityValidator#validateAttributeState(shiva.cfg.Configuration, java.lang.Object, java.lang.reflect.Field, shiva.domain.metadata.AttributeMapping)
	 */
	public void validateAttributeState(Configuration config, Object objEntity, Field att, AttributeMapping am) throws InvalidAttributeValueException{
		logger.info( "attribute: " + att.getName() );
		
		try {
			boolean accessible = att.isAccessible();
			
			if( ! accessible ){
				att.setAccessible( true );
			}
			
			//get the validation map
			Map<Class, ValidationClass> vm = config.getValidationsMapping();

			//get the attribute value
			Object attributeValue = att.get( objEntity );
			
			//for each validation type
			Collection<Annotation> validations = am.getValidations();
			for( Annotation validation : validations ) {
				
				//parameter map
				Map<String, Object> parameters = new HashMap<String, Object>();
				
				Method[] declaredMethods = validation.annotationType().getDeclaredMethods();
				if( declaredMethods != null ){
					for( Method method : declaredMethods ) {
						
						String methodName = method.getName();
						//logger.info( "method.getName(): " + methodName );
						
						Object value = Utils.getInstance().getValueByMethod( validation, method );
						parameters.put( methodName, value );
					}
				}
				
				//retrieve the validation class
				ValidationClass vc = vm.get( validation.annotationType() );
				
				//
				String attributeName = att.getName();
				
				if( ! accessible ){
					att.setAccessible( false );
				}
				
				//validate the attribute value with the actual validation class
				try{
					
					vc.validate( parameters, attributeValue, attributeName );
					logger.info( " -- "  + vc.getClass().getName() + ": PASSED :)" );
					
				}catch (InvalidAttributeValueException e) {
					logger.info( " -- "  + vc.getClass().getName() + ": FAILED :(" );
					throw e;
				}
			}
		
		} catch ( IllegalArgumentException e ) {
			logger.error("validateAttribute is not ok :(", e);
		} catch ( IllegalAccessException e ) {
			logger.error("validateAttribute is not ok :(", e);
		}
	}

}
