package shiva.cfg.mapper;

import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
@SuppressWarnings("unchecked")
public interface Mapper {
	
	/**
	 * 
	 */
	void initMapper();
	
	/**
	 * 
	 * @param registeredClasses
	 */
	Map generateMapping( Set<Class> registeredClasses );
	
}
