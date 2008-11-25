package shiva.cfg.reflector;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;

/**
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
@SuppressWarnings("unchecked")
public interface EntityReflector {

	/**
	 * 
	 * 
	 * @param clazz
	 * @return
	 */
	public boolean isLdapEntity(Class clazz);

	/**
	 * 
	 * 
	 * @param object
	 * @return
	 */
	public boolean isLdapEntity(Object object);
	
	/**
	 * 
	 * 
	 * @param attribute
	 * @return
	 */
	public boolean isLdapAttribute(Field attribute);

	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	public boolean isLdapDn(Field attribute);
	
	/**
	 * 
	 * 
	 * @param attribute
	 * @return
	 */
	public Annotation getDnAnnotationFromAttribute(Field attribute);
	
	/**
	 * 
	 * 
	 * @param attribute
	 * @return
	 */
	public String getDnFromAnnotation(Annotation ldapDnAnnotation);
	
	/**
	 * 
	 * 
	 * @param entityAnnotation
	 * @return
	 */
	public String getDirectoryNameFromAnnotation(Annotation entityAnnotation);
	
	/**
	 * 
	 * 
	 * @param entityAnnotation
	 * @return
	 */
	public String[] getObjectClassFromAnnotation(Annotation entityAnnotation);
	
	/**
	 * 
	 * 
	 * @param entityAnnotation
	 * @return
	 */
	public String getAttributeNameFromAnnotation(Annotation attributeAnnotation);

	/**
	 * 
	 * 
	 * @return
	 */
	public Annotation getEntityAnnotation(Class clazz);
	
	/**
	 * 
	 * 
	 * @return
	 */
	public Annotation getAttributeAnnotation(Field field);
	
	/**
	 * 
	 * 
	 * @return
	 */
	public Collection<Annotation> getAnnotationsFromEntity(Class clazz);

	/**
	 * 
	 * @param object
	 * @return
	 */
	public Collection<Annotation> getAnnotationsFromEntity(Object object);

	/**
	 * 
	 * 
	 * @return
	 */
	public Collection<Annotation> getAnnotationsFromAttribute(Field attribute);
	
	/**
	 * 
	 * @param attribute
	 * @return
	 */
	public Collection<Annotation> getValidationAnnotationsFromAttribute(Field attribute);


}
