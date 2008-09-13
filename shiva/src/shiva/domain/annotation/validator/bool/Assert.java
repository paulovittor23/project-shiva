package shiva.domain.annotation.validator.bool;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 
 * @author paulo
 * 
 * @description
 *
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Assert {
	
	/**
	 * 
	 * @return
	 */
	boolean value();
	
}
