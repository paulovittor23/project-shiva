package shiva.domain.annotation.mapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface LdapEntity {
	
	/**
	 * 
	 * @return
	 */
	String directory();
	
	/**
	 * 
	 * @return
	 */
	String[] objectClass() default {"top"};

}