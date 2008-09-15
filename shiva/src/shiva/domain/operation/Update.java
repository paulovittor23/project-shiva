package shiva.domain.operation;

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
	public String toStatementString() {
		
		StringBuffer buf = new StringBuffer();
		buf.append("UPDATE INTO ").append("uid=").append(uid);
		
		return buf.toString();
	}

	
}
