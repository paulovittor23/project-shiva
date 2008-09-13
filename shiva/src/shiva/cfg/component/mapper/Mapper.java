package shiva.cfg.component.mapper;

import java.util.Map;
import java.util.Set;

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
