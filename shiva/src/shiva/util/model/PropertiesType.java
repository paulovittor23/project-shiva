package shiva.util.model;

/**
 * 
 * 
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
public enum PropertiesType {
	
	UTIL_PROPERTIES_FILE("utils.properties"),
	
	CONFIGURATION_PROPERTIES_FILE("/shiva.properties");
	
	private String filePath;
	
	/**
	 * 
	 * 
	 * @param filePath
	 */
	private PropertiesType(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public String getFilePath() {
		return filePath;
	}

}
