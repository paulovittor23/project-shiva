package shiva.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import shiva.domain.exception.PropertyValueNotFound;

/**
 * 
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
public class Utils {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	private static Utils instance;
	
	/**
	 * 
	 * 
	 */
	private Utils(){}
	
	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	public static synchronized Utils getInstance(){
		if( instance == null ){
			instance = new Utils();
		}
		return instance;
	}

	/**
	 * 
	 * 
	 * @param file_type
	 * @return
	 * @throws FileNotFoundException
	 */
	public Properties getPropertyFileByType(PropertiesType file_type) throws FileNotFoundException{
		Properties properties = new Properties();
    	
		InputStream is = this.getClass().getResourceAsStream(file_type.getFilePath());
    	
		if( is == null ){
			
			String errorMsg = "The file \"" + file_type.getFilePath() + "\" was not found.";
    		throw new FileNotFoundException(errorMsg);
    	}
		
		try {
			properties.load(is);
		} catch (IOException e) {
			logger.error("getPropertyFileByType is not ok :(", e);
		}
		
		return properties;
	}

	/**
	 * 
	 * 
	 * @param file_type
	 * @param key
	 * @return
	 * @throws PropertyValueNotFound
	 * @throws FileNotFoundException
	 */
	public String getPropertyValueByKey(PropertiesType file_type, String key) throws PropertyValueNotFound, FileNotFoundException {
		String value = null;
		
		Properties properties = this.getPropertyFileByType(file_type);
		value = properties.getProperty(key);
		
		if( value == null ){
			throw new PropertyValueNotFound("");
		}
			
		return value;
	}
	
	/**
	 * 
	 * 
	 * @param properties
	 * @param key
	 * @return
	 * @throws PropertyValueNotFound
	 */
	public String getPropertyValueByKey(Properties properties, String key) throws PropertyValueNotFound {
		String propertyValue = properties.getProperty(key);
		if( propertyValue == null ){
			String errorMsg = "Property \"" + key + "\" not found.";
			throw new PropertyValueNotFound(errorMsg);
		}
		return propertyValue;
	}

	/**
	 * 
	 * 
	 * @param annotation
	 * @param attributeName
	 * @return
	 */
	public Object getValueByAttributeName(Annotation annotation, String attributeName) {
		Object o = null;
		try {
			Method m = annotation.annotationType().getMethod(attributeName, new Class[]{});
			o = m.invoke(annotation);
		} catch (SecurityException e) {
			logger.error("getValueFromAnnotationAttribute is not ok :(", e);
		} catch (NoSuchMethodException e) {
			logger.error("getValueFromAnnotationAttribute is not ok :(", e);
		} catch (IllegalArgumentException e) {
			logger.error("getValueFromAnnotationAttribute is not ok :(", e);
		} catch (IllegalAccessException e) {
			logger.error("getValueFromAnnotationAttribute is not ok :(", e);
		} catch (InvocationTargetException e) {
			logger.error("getValueFromAnnotationAttribute is not ok :(", e);
		}
		//return o.toString();
		return o;
	}
	
	/**
	 * 
	 * @param annotation
	 * @param method
	 * @return
	 */
	public Object getValueByMethod(Annotation annotation, Method method) {
		Object o = null;
		try {
			o = method.invoke(annotation);
		} catch (SecurityException e) {
			logger.error("getValueFromAnnotationAttribute is not ok :(", e);
		} catch (IllegalArgumentException e) {
			logger.error("getValueFromAnnotationAttribute is not ok :(", e);
		} catch (IllegalAccessException e) {
			logger.error("getValueFromAnnotationAttribute is not ok :(", e);
		} catch (InvocationTargetException e) {
			logger.error("getValueFromAnnotationAttribute is not ok :(", e);
		}
		//return o.toString();
		return o;
	}
	
	/**
	 * 
	 * @param key
	 * @param parameters
	 * @return
	 */
	public static String retrieveMessage(String key, Object[] parameters){
		Locale locale = Locale.getDefault();
		
		ResourceBundle rb = ResourceBundle.getBundle("messages", locale);
		String string = rb.getString( key );
		
		MessageFormat messageForm = new MessageFormat("");
		messageForm.setLocale(locale);

		messageForm.applyPattern(string);
		String result = messageForm.format(parameters);	
		
		return result;
	}

}
