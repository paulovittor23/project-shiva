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
public class Insert extends AbstractOperation {

	/*
	 * (non-Javadoc)
	 * @see shiva.domain.operation.CrudOperation#toStatementString()
	 */
	@SuppressWarnings("unchecked")
	public String toStatementString() {
		
		StringBuffer buf = new StringBuffer();
		buf.append("INSERT INTO ").append(distinguishedName);
		
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
				buf.append( iter.next() );
				
				if (iter.hasNext()) {
					buf.append(",");
				}
			}
			
			buf.append(") VALUES (");
			
			if( objectClasses != null && objectClasses.length > 0 ){
				for (String objectClass : objectClasses) {
					buf.append( "'" ).append( objectClass ).append( "'" ).append( "," );
				}
			}
			
			iter = columns.values().iterator();
			while (iter.hasNext()) {
				//buf.append("\"" + iter.next() + "\"");
				
				Object value = iter.next();
				
				if( value instanceof String ){
					buf.append( "'" ).append( value ).append( "'" );
				}else{
					buf.append(value);
				}
				
				if (iter.hasNext()) {
					buf.append(",");
				}
			}
			buf.append(')');
		}
		
		return buf.toString();
	}

}
