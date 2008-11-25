package shiva.domain.operation.impl;

import shiva.domain.operation.AbstractOperation;
import shiva.domain.operation.filter.Filter;
import shiva.domain.operation.filter.SelectFilter;

/**
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
public class Select extends AbstractOperation {

	Filter filter = new SelectFilter();
	
	/**
	 * 
	 * @return
	 */
	public Filter getFilter() {
		return this.filter;
	}

	/* (non-Javadoc)
	 * @see shiva.domain.operation.Operation#toStatementString()
	 */
	@Override
	public String toStatementString() {
		StringBuffer buf = new StringBuffer();		
		buf.append("SELECT * FROM ");
		buf.append(distinguishedName);
		
		if( filter.hasRestrictions() ){
			buf.append( " WHERE " );
			buf.append( filter.getRestrictStatement( true ) );
		}
		
		return buf.toString();
	}

}
