package shiva.session.component.persister.model;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.collections.SequencedHashMap;

@SuppressWarnings("unchecked")
public class Insert {

	private String uid;
	private String directoryName;
	private String[] objectClasses;
	private Map columns = new SequencedHashMap();

	public Insert() {}
	
	public Insert(String directoryName) {
		this.directoryName = directoryName;
	}
	
	/**
	 * @return the uid
	 */
	public String getUid() {
		return this.uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid( String uid ) {
		this.uid = uid;
	}

	/**
	 * @return the directoryName
	 */
	public String getDirectoryName() {
		return directoryName;
	}

	/**
	 * @param directoryName
	 *            the directoryName to set
	 */
	public void setDirectoryName(String directoryName) {
		this.directoryName = directoryName;
	}
	
	/**
	 * @return the objectClasses
	 */
	public String[] getObjectClasses() {
		return objectClasses;
	}

	/**
	 * @param objectClasses the objectClasses to set
	 */
	public void setObjectClasses(String[] objectClasses) {
		this.objectClasses = objectClasses;
	}

	/**
	 * @return the columns
	 */
	public Map getColumns() {
		return columns;
	}

	/**
	 * @param columns
	 *            the columns to set
	 */
	public void setColumns(Map columns) {
		this.columns = columns;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public String toStatementString() {
		
		StringBuffer buf = new StringBuffer();
		buf.append("INSERT INTO ").append("uid=").append(uid);
		
		if (columns.size() == 0) {
			buf.append(' ');
		} else {
			buf.append("(");
			
			if( objectClasses != null && objectClasses.length > 0 ){
				for (int i = 0; i < objectClasses.length; i++) {
					buf.append("objectClass,");
				}
			}
			
			Iterator iter = columns.keySet().iterator();
			while (iter.hasNext()) {
				buf.append(iter.next());
				if (iter.hasNext()) {
					buf.append(",");
				}
			}
			
			buf.append(") VALUES (");
			
			if( objectClasses != null && objectClasses.length > 0 ){
				for (String objectClass : objectClasses) {
					buf.append(objectClass);
					buf.append(",");
				}
			}
			
			iter = columns.values().iterator();
			while (iter.hasNext()) {
				//buf.append("\"" + iter.next() + "\"");
				
				Object value = iter.next();
				buf.append(value);
				
				if (iter.hasNext()) {
					buf.append(",");
				}
			}
			buf.append(')');
		}
		
		return buf.toString();
	}

}
