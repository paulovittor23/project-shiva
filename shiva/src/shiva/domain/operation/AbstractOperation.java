package shiva.domain.operation;

import java.util.Map;

import org.apache.commons.collections.SequencedHashMap;

/**
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 * 
 * 
 */
@SuppressWarnings( "unchecked" )
public abstract class AbstractOperation implements Operation {

	// o, ou, dc...
	protected String distinguishedName;
	protected String directoryName;
	protected String[] objectClasses;
	protected Map columns = new SequencedHashMap();

	/**
	 * 
	 */
	public AbstractOperation() {
	}

	/**
	 * 
	 * @param directoryName
	 */
	public AbstractOperation( String directoryName ) {
		this.directoryName = directoryName;
	}

	/* getters */

	/**
	 * @return the directoryName
	 */
	public String getDirectoryName() {
		return this.directoryName;
	}

	/**
	 * @return the objectClasses
	 */
	public String[] getObjectClasses() {
		return this.objectClasses;
	}

	/**
	 * @return the columns
	 */
	public Map getColumns() {
		return this.columns;
	}
	
	/* (non-Javadoc)
	 * @see shiva.domain.operation.Operation#getDistinguishedName()
	 */
	public String getDistinguishedName() {
		return this.distinguishedName;
	}

	/* (non-Javadoc)
	 * @see shiva.domain.operation.Operation#setDistinguishedName(java.lang.String)
	 */
	public void setDistinguishedName( String distinguishedName ) {
		this.distinguishedName = distinguishedName;
	}

	/* (non-Javadoc)
	 *  @see shiva.domain.operation.CrudOperation#setColumns(java.util.Map)
	 */
	public void setColumns( Map columns ) {
		this.columns = columns;
	}

	/* (non-Javadoc)
	 * @see shiva.domain.operation.CrudOperation#setDirectoryName(java.lang.String)
	 */
	public void setDirectoryName( String directoryName ) {
		this.directoryName = directoryName;
	}

	/* (non-Javadoc)
	 * @see shiva.domain.operation.CrudOperation#setObjectClasses(java.lang.String[])
	 */
	public void setObjectClasses( String[] objectClasses ) {
		this.objectClasses = objectClasses;
	}

}
