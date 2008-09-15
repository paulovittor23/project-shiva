package shiva.domain.operation;

import java.util.Map;

import org.apache.commons.collections.SequencedHashMap;

/**
 * 
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 * 
 * 
 */
@SuppressWarnings( "unchecked" )
public abstract class AbstractOperation implements CrudOperation {

	protected String uid;

	protected String directoryName;

	protected String[] objectClasses;

	protected Map columns = new SequencedHashMap();

	/**
	 * 
	 * 
	 */
	public AbstractOperation() {}
	
	/**
	 * 
	 * @param directoryName
	 */
	public AbstractOperation( String directoryName ) {
		this.directoryName = directoryName;
	}

	/**
	 * @return the uid
	 */
	public String getUid() {
		return this.uid;
	}

	/**
	 * @param uid
	 *            the uid to set
	 */
	public void setUid( String uid ) {
		this.uid = uid;
	}

	/**
	 * @return the directoryName
	 */
	public String getDirectoryName() {
		return this.directoryName;
	}

	/**
	 * @param directoryName
	 *            the directoryName to set
	 */
	public void setDirectoryName( String directoryName ) {
		this.directoryName = directoryName;
	}

	/**
	 * @return the objectClasses
	 */
	public String[] getObjectClasses() {
		return this.objectClasses;
	}

	/**
	 * @param objectClasses
	 *            the objectClasses to set
	 */
	public void setObjectClasses( String[] objectClasses ) {
		this.objectClasses = objectClasses;
	}

	/**
	 * @return the columns
	 */
	public Map getColumns() {
		return this.columns;
	}

	/**
	 * @param columns
	 *            the columns to set
	 */
	public void setColumns( Map columns ) {
		this.columns = columns;
	}

}
