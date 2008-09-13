package shiva.cfg.component.mapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.SequencedHashMap;
import org.apache.log4j.Logger;

import shiva.cfg.component.mapper.model.exception.ColumnAnnotationNotFoundException;
import shiva.cfg.component.mapper.model.exception.EntityAnnotationNotFoundException;
import shiva.cfg.component.mapper.model.exception.UidAttributeNotFoundException;
import shiva.cfg.component.mapper.model.metadata.AttributeMapping;
import shiva.cfg.component.mapper.model.metadata.EntityMapping;
import shiva.cfg.component.reflector.EntityReflectorImpl;

/**
 * 
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
			} catch ( UidAttributeNotFoundException e ) {
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
	 * @throws UidAttributeNotFoundException
	 */
	private EntityMapping mapClass( Class clazz ) throws EntityAnnotationNotFoundException,
			UidAttributeNotFoundException {

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

		boolean hasUid = false;
		for( Field attribute : attributesList ) {
			try {

				AttributeMapping am = this.mapAttribute( attribute );
				em.addAttributeMapped( attribute, am );

				if ( am.isUidAttribute() ) {
					hasUid = true;
				}

			} catch ( ColumnAnnotationNotFoundException e ) {
				logger.error( "mapClass is not ok :(", e );
			}
		}

		if ( !hasUid ) {
			throw new UidAttributeNotFoundException( clazz );
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

		boolean ldapColumn = this.entityReflector.isLdapAttribute( attribute );
		if ( !ldapColumn ) {

			throw new ColumnAnnotationNotFoundException();
		} else {

			Annotation attributeAnnotation = this.entityReflector.getAttributeAnnotation( attribute );
			String attributeNameBound = this.entityReflector.getAttributeNameFromAnnotation( attributeAnnotation );

			am = new AttributeMapping();
			am.setAttribute( attribute );
			am.setAttributeNameBound( attributeNameBound );
		}

		return am;
	}

}
