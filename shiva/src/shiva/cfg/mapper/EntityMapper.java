package shiva.cfg.mapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.SequencedHashMap;
import org.apache.log4j.Logger;

import shiva.cfg.reflector.EntityReflectorImpl;
import shiva.domain.exception.ColumnAnnotationNotFoundException;
import shiva.domain.exception.EntityAnnotationNotFoundException;
import shiva.domain.exception.DnAttributeNotFoundException;
import shiva.domain.metadata.AttributeMapping;
import shiva.domain.metadata.EntityMapping;

/**
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 * 
 */
@SuppressWarnings( "unchecked" )
public class EntityMapper implements Mapper {

	private Logger logger = Logger.getLogger( this.getClass() );

	//
	private Map<Class, EntityMapping> mappings = null;
	private EntityReflectorImpl entityReflector = null;
	private StringBuffer mappingErrors = null;
	
	/**
	 * 
	 * 
	 */
	public EntityMapper() {
		this.entityReflector = new EntityReflectorImpl();

		this.mappings = new SequencedHashMap();
		this.mappingErrors = new StringBuffer();
	}

	/*
	 * (non-Javadoc)
	 * @see shiva.cfg.component.mapper.Mapper#initMapper()
	 */
	public void initMapper() {
		this.mappings.clear();
		this.mappingErrors.setLength( 0 );
	}

	/*
	 * (non-Javadoc)
	 * @see shiva.cfg.component.mapper.Mapper#generateMapping(java.util.Set)
	 */
	public Map<Class, EntityMapping> generateMapping( Set<Class> registeredClasses ) {

		this.initMapper();

		for( Class clazz : registeredClasses ) {
			try {

				EntityMapping mappedClass = this.mapClass( clazz );
				this.mappings.put( clazz, mappedClass );

			} catch ( EntityAnnotationNotFoundException e ) {
				this.mappingErrors.append( e.getMessage() );
				logger.error( "generateMapping is not ok :(", e );
			} catch ( DnAttributeNotFoundException e ) {
				this.mappingErrors.append( e.getMessage() );
				logger.error( "generateMapping is not ok :(", e );
			}
		}

		return this.mappings;
	}

	/**
	 * 
	 * @param clazz
	 * @return
	 * @throws EntityAnnotationNotFoundException
	 * @throws DnAttributeNotFoundException
	 */
	private EntityMapping mapClass( Class clazz ) throws EntityAnnotationNotFoundException,
														 DnAttributeNotFoundException {
		logger.info( "mapping class: " + clazz.getCanonicalName() );
		
		boolean ldapEntity = this.entityReflector.isLdapEntity( clazz );
		if ( !ldapEntity ) {
			throw new EntityAnnotationNotFoundException( clazz );
		}

		Annotation entityAnnotation = this.entityReflector.getEntityAnnotation( clazz );
		String directoryName = this.entityReflector.getDirectoryNameFromAnnotation( entityAnnotation );
		String[] objectClass = this.entityReflector.getObjectClassFromAnnotation( entityAnnotation );

		EntityMapping em = new EntityMapping();
		em.setClazz( clazz );
		em.setDirectoryNameBound( directoryName );
		em.setObjectClass( objectClass );

		Field[] attributes = clazz.getDeclaredFields();
		List<Field> attributesList = Arrays.asList( attributes );

		boolean hasDn = false;
		for( Field attribute : attributesList ) {
			try {

				AttributeMapping am = this.mapAttribute( attribute );
				em.addAttributeMapped( attribute, am );

				if ( am.isDn() ) {
					hasDn = true;
				}

			} catch ( ColumnAnnotationNotFoundException e ) {
				logger.error( "mapClass is not ok :(", e );
			}
		}

		if ( ! hasDn ) {
			throw new DnAttributeNotFoundException( clazz );
		}

		return em;
	}

	/**
	 * 
	 * @param attribute
	 * @return
	 * @throws ColumnAnnotationNotFoundException
	 */
	private AttributeMapping mapAttribute( Field attribute ) throws ColumnAnnotationNotFoundException {

		AttributeMapping am = null;

		boolean ldapAttribute = this.entityReflector.isLdapAttribute( attribute );
		boolean ldapDn = this.entityReflector.isLdapDn( attribute );
		
		//
		if ( ! ldapAttribute && ! ldapDn ) {
			throw new ColumnAnnotationNotFoundException();
		}
		
		//create a new Attribute Mapping
		am = new AttributeMapping();
		
		//
		Annotation annotation = null;
		
		//
		if( ldapDn ){
			
			annotation = this.entityReflector.getDnAnnotationFromAttribute( attribute );
			String dnType = this.entityReflector.getDnFromAnnotation( annotation );

			am.setDn( true );
			am.setDnType( dnType );
			
		}else if( ldapAttribute ){
			
			annotation = this.entityReflector.getAttributeAnnotation( attribute );
			
		}
		
		//
		String attributeNameBound = this.entityReflector.getAttributeNameFromAnnotation( annotation );
		Collection<Annotation> validations = this.entityReflector.getValidationAnnotationsFromAttribute( attribute );
		
		//
		am.setAttribute( attribute );
		am.setAttributeNameBound( attributeNameBound );
		am.setValidations( validations );
		
		return am;
	}

}
