package shiva.cfg.reflector;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
@SuppressWarnings("unchecked")
public class ValidatorReflectorImpl implements ValidatorReflector {

	private Logger logger = Logger.getLogger(this.getClass());
	
	/*
	 * (non-Javadoc)
	 * @see jldap.core.config.mapping.reflection.ValidatorReflector#findClasses(java.io.File, java.lang.String)
	 */
	public List<Class> findClasses(File directory, String packageName){
		List<Class> classes = new ArrayList<Class>();
		if (!directory.exists()) {
			return classes;
		}
		java.io.File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file, packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
				try {
					classes.add(Class.forName(className));
				} catch (ClassNotFoundException e) {
					logger.error("findClasses is not ok :(", e);
				}
			}
		}
		return classes;
	}

	/*
	 * (non-Javadoc)
	 * @see jldap.core.config.mapping.reflection.ValidatorReflector#getClasses(java.lang.String)
	 */
	public Class[] getClasses(String packageName) {
		ArrayList<Class> classes = new ArrayList<Class>();
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		assert classLoader != null;
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = null;
		try {
			resources = classLoader.getResources(path);
			List<File> dirs = new ArrayList<File>();
			while (resources.hasMoreElements()) {
				URL resource = resources.nextElement();
				dirs.add(new File(resource.getFile()));
			}
			for (File directory : dirs) {
				classes.addAll(findClasses(directory, packageName));
			}
			classes.toArray(new Class[classes.size()]);
		} catch (IOException e) {
			logger.error("Ocorreu um ero durante o carregamento das classes do pacote " + packageName + ".");
		}
		return classes.toArray(new Class[classes.size()]);
	}

}
