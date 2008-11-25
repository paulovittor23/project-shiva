package shiva.domain.operation;

import java.util.Map;

/**
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 *
 */
@SuppressWarnings("unchecked")
public interface Operation {

	/**
	 * 
	 * @return
	 */
	String toStatementString();
	
	/**
	 * 
	 * @param directoryName the directoryName to set
	 */
	void setDirectoryName( String directoryName );

	/**
	 * 
	 * @param objectClasses the objectClasses to set
	 */
	void setObjectClasses( String[] objectClasses );

	/**
	 * 
	 * @param columns the columns to set
	 */
	void setColumns( Map columns );
	
	/**
	 * 
	 * @return
	 */
	public String getDistinguishedName();

	/**
	 * 
	 * @param distinguishedName
	 */
	public void setDistinguishedName( String distinguishedName );
	
}
