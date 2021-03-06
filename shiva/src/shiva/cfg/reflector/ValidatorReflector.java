package shiva.cfg.reflector;

import java.io.File;
import java.util.List;

/**
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
@SuppressWarnings("unchecked")
public interface ValidatorReflector {
	
	/**
	 * 
	 * 
	 * @param packageName
	 * @return
	 */
	public Class[] getClasses(String packageName);
	
	/**
	 * 
	 * @param directory
	 * @param packageName
	 * @return
	 */
	public List<Class> findClasses(File directory, String packageName);

}
