package shiva.domain.operation.impl;

import shiva.domain.operation.AbstractOperation;

/**
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
public class Delete extends AbstractOperation {

	/*
	 * (non-Javadoc)
	 * @see shiva.domain.operation.CrudOperation#toStatementString()
	 */
	public String toStatementString() {
		StringBuffer buf = new StringBuffer();		
		buf.append("DELETE FROM ").append(directoryName);
		buf.append(" WHERE ");
		buf.append(distinguishedName);
		return buf.toString();
	}

}
