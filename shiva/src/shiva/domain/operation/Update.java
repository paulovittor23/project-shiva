package shiva.domain.operation;

import java.util.Iterator;

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
		buf.append("UPDATE ").append("uid=").append(uid);
		
		if (columns.size() == 0) {
			buf.append(' ');
		} else {
			buf.append(" SET ");
			
			Iterator iter = columns.keySet().iterator();
			while (iter.hasNext()) {
				String attributeName = (String) iter.next();				
				buf.append(attributeName).append( "=" ).append( columns.get( attributeName ) );
				if (iter.hasNext()) {
					buf.append(",");
				}
			}
			
		}
		
		return buf.toString();
	}

	
}
