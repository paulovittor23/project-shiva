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
public abstract class AbstractOperation implements Operation {

	protected String uid;

	protected String directoryName;

	protected String[] objectClasses;

	protected Map columns = new SequencedHashMap();

	/**
	 * 
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
	 * @return the uid
	 */
	public String getUid() {
		return this.uid;
	}

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

	/* setters */

	/*
	 * (non-Javadoc)
	 * 
	 * @see shiva.domain.operation.CrudOperation#setColumns(java.util.Map)
	 */
	public void setColumns( Map columns ) {
		this.columns = columns;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * shiva.domain.operation.CrudOperation#setDirectoryName(java.lang.String)
	 */
	public void setDirectoryName( String directoryName ) {
		this.directoryName = directoryName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * shiva.domain.operation.CrudOperation#setObjectClasses(java.lang.String[])
	 */
	public void setObjectClasses( String[] objectClasses ) {
		this.objectClasses = objectClasses;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see shiva.domain.operation.CrudOperation#setUid(java.lang.String)
	 */
	public void setUid( String uid ) {
		this.uid = uid;
	}

}
