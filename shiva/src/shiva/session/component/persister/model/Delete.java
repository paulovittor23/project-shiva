package shiva.session.component.persister.model;

public class Delete {

	private String uid;
	private String directoryName;
	private String[] objectClasses; 
	private String where;

	public Delete() {
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
	 * @return the objectClasses
	 */
	public String[] getObjectClasses() {
		return this.objectClasses;
	}

	/**
	 * @param objectClasses the objectClasses to set
	 */
	public void setObjectClasses( String[] objectClasses ) {
		this.objectClasses = objectClasses;
	}

	/**
	 * @return the directoryName
	 */
	public String getDirectoryName() {
		return directoryName;
	}

	/**
	 * @param directoryName the directoryName to set
	 */
	public void setDirectoryName(String directoryName) {
		this.directoryName = directoryName;
	}

	/**
	 * @return the where
	 */
	public String getWhere() {
		return where;
	}

	/**
	 * @param where the where to set
	 */
	public void setWhere(String where) {
		this.where = where;
	}

	/**
	 * 
	 * @param fragment
	 * @return
	 */
	public void addWhereFragment(String fragment) {
		if (where == null) {
			where = fragment;
		} else {
			where += (" and " + fragment);
		}
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public String toStatementString() {
		
		StringBuffer buf = new StringBuffer();		
		buf.append("DELETE FROM ").append("uid=").append(uid);
		/*if(where != null || pkAttributeName != null) {
			buf.append(" where ");
		}
		boolean conditionsAppended = false;
		if (pkAttributeName != null) {
			buf.append(pkAttributeName).append("=?");
			conditionsAppended = true;
		}
		if (where != null) {
			if (conditionsAppended) {
				buf.append(" and ");
			}
			buf.append(where);
			conditionsAppended = true;
		}*/
		return buf.toString();
	}

}
