package shiva.cfg.component.reflector;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.apache.log4j.Logger;

import shiva.domain.mapping.LdapAttribute;
import shiva.domain.mapping.LdapEntity;
import shiva.util.Utils;

/**
 * 
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
@SuppressWarnings({"unchecked","unused"})
public class EntityReflectorImpl implements EntityReflector {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	public EntityReflectorImpl() {}
	
	/* 
	 * (non-Javadoc)
	 * @see jldap.core.reflection.EntityReflector#getAnnotationsFromEntity(java.lang.Class)
	 */
	public Collection<Annotation> getAnnotationsFromEntity( Class clazz ) {

		Collection<Annotation> annotations = null;
		
		if( clazz != null ){
			
			Annotation[] vectorAnnotations = clazz.getAnnotations();
			if( vectorAnnotations != null && vectorAnnotations.length > 0 ){
				
				annotations = new ArrayList<Annotation>();
				annotations = Arrays.asList( vectorAnnotations );
			}
		}
		
		return annotations;
	}
	
	/*
	 * (non-Javadoc)
	 * @see jldap.core.reflection.EntityReflector#getAnnotationsFromEntity(java.lang.Object)
	 */
	public Collection<Annotation> getAnnotationsFromEntity( Object object ){
		
		Collection<Annotation> annotations = null;
		
		if( object != null ){
			
			Class clazz = object.getClass();
			annotations = this.getAnnotationsFromEntity( clazz );
		}
		
		return annotations;
	}
	
	/* 
	 * (non-Javadoc)
	 * @see jldap.core.reflection.EntityReflector#getAnnotationsFromAttribute(java.lang.reflect.Field)
	 */
	public Collection<Annotation> getAnnotationsFromAttribute( Field field ) {
		
		Collection<Annotation> annotations = null;
		
		if( field != null ){
			
			Annotation[] vectorAnnotations = field.getAnnotations();
			if( vectorAnnotations != null && vectorAnnotations.length > 0 ){
				
				annotations = new ArrayList<Annotation>();
				annotations = Arrays.asList( vectorAnnotations );
			}
		}
		
		return annotations;
	}

	/*
	 * (non-Javadoc)
	 * @see jldap.core.config.mapping.reflection.EntityReflector#isLdapEntity(java.lang.Class)
	 */
	public boolean isLdapEntity(Class clazz) {
		return clazz.isAnnotationPresent( LdapEntity.class );
	}

	/*
	 * (non-Javadoc)
	 * @see jldap.core.config.mapping.reflection.EntityReflector#isLdapEntity(java.lang.Object)
	 */
	public boolean isLdapEntity(Object object) {
		return this.isLdapEntity( object.getClass() );
	}

	/*
	 * (non-Javadoc)
	 * @see jldap.core.config.mapping.reflection.EntityReflector#getDirectoryName(java.lang.annotation.Annotation)
	 */
	public String getDirectoryNameFromAnnotation(Annotation entityAnnotation) {
		Object directoryName = Utils.getInstance().getValueFromAnnotationAttribute( entityAnnotation, "directory" );
		return (String) directoryName;
	}
	
	/*
	 * (non-Javadoc)
	 * @see jldap.core.config.component.reflector.EntityReflector#getObjectClassFromAnnotation(java.lang.annotation.Annotation)
	 */
	public String[] getObjectClassFromAnnotation(Annotation entityAnnotation){
		Object objectClass = Utils.getInstance().getValueFromAnnotationAttribute( entityAnnotation, "objectClass" );
		return (String[]) objectClass;
	}
	
	/*
	 * (non-Javadoc)
	 * @see jldap.core.config.components.reflector.EntityReflector#getAttributeNameFromAnnotation(java.lang.annotation.Annotation)
	 */
	public String getAttributeNameFromAnnotation(Annotation attributeAnnotation){
		Object attributeName = Utils.getInstance().getValueFromAnnotationAttribute( attributeAnnotation, "value" );
		return (String) attributeName;
	}

	/*
	 * (non-Javadoc)
	 * @see jldap.core.config.mapping.reflection.EntityReflector#getEntityAnnotation(java.lang.Class)
	 */
	public Annotation getEntityAnnotation(Class clazz) {
		Annotation annotation = clazz.getAnnotation(LdapEntity.class);
		return annotation;
	}
	
	/*
	 * (non-Javadoc)
	 * @see jldap.core.config.components.reflector.EntityReflector#isLdapAttribute(java.lang.reflect.Field)
	 */
	public boolean isLdapAttribute(Field attribute){
		LdapAttribute annotationClazz = attribute.getAnnotation( LdapAttribute.class );
		return annotationClazz != null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see jldap.core.config.components.reflector.EntityReflector#getAttributeAnnotation(java.lang.reflect.Field)
	 */
	public Annotation getAttributeAnnotation(Field field){
		LdapAttribute annotationClazz = field.getAnnotation(LdapAttribute.class);
		return annotationClazz;
	}

}
