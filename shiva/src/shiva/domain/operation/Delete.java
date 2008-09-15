package shiva.domain.operation;

/**
 * 
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
		buf.append( "uid=" ).append( uid );
		
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
	
	/*public void addWhereFragment(String fragment) {
	if (where == null) {
		where = fragment;
	} else {
		where += (" and " + fragment);
	}
	}*/

}
