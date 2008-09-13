package shiva.cfg.component.mapper.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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