package shiva.domain.operation.impl;

import java.util.Iterator;

import shiva.domain.operation.AbstractOperation;

/**
 * 
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
public class Update extends AbstractOperation {

	/* 
	 * (non-Javadoc)
	 * @see shiva.domain.operation.CrudOperation#toStatementString()
	 */
	@SuppressWarnings("unchecked")
	public String toStatementString() {
		
		StringBuffer buf = new StringBuffer();
		buf.append("UPDATE ").append(distinguishedName);
		
		if (columns.size() == 0) {
			buf.append(' ');
		} else {
			buf.append(" SET ");
			
			Iterator iter = columns.keySet().iterator();
			while (iter.hasNext()) {
				String attributeName = (String) iter.next();	
				
				Object value = columns.get( attributeName );
				
				if( value instanceof String ){
					buf.append(attributeName).append( "=" ).append( "'" ).append( value ).append( "'" );
				}else{
					buf.append(attributeName).append( "=" ).append( value );
				}
				
				
				
				if (iter.hasNext()) {
					buf.append(",");
				}
			}
			
		}
		
		return buf.toString();
	}

	
}
