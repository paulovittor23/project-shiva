package shiva.util.model;

public enum PropertiesType {
	
	UTIL_PROPERTIES_FILE("utils.properties"),
	CONFIGURATION_PROPERTIES_FILE("/jldap-annotations.properties");
	
	private PropertiesType(String filePath) {
		this.filePath = filePath;
	}
	
	private String filePath;

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

}
