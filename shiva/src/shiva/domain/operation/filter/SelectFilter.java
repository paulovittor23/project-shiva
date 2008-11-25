package shiva.domain.operation.filter;

import java.util.ArrayList;
import java.util.Iterator;

import shiva.domain.operation.Restriction;

/**
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
@SuppressWarnings("unchecked")
public class SelectFilter extends AbstractFilter {
	
	private Boolean restrictAll;
	
	private static final String AND_OPERATOR = "AND ";
	private static final String OR_OPERATOR = "OR ";

	/**
	 * 
	 * @return
	 */
	public Boolean getRestrictAll() {
		return this.restrictAll;
	}

	/**
	 * 
	 * @param restrictAll
	 */
	public void setRestrictAll( Boolean restrictAll ) {
		this.restrictAll = restrictAll;
	}

	/**
	 * 
	 * 
	 * 
	 */
	@Override
	public String toString() {
		if( this.restrictions == null || this.restrictions.size() == 0 ){
			return "";
		}else{
			StringBuffer buffRestricoes = new StringBuffer();
			Iterator iRes = this.restrictions.iterator();
			while (iRes.hasNext()) {
				Restriction r = (Restriction) iRes.next();
				buffRestricoes.append( r.toString() + "\n" );
			}
			return buffRestricoes.toString();
		}
	}

	/* (non-Javadoc)
	 * @see shiva.domain.operation.filter.Filter#addRestriction(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void addRestriction( String field, String type, String value ) {
		Restriction restriction = new Restriction();
		restriction.setField(field);
		restriction.setType(type);
		restriction.setValue(value);

		this.addRestriction(restriction);
	}

	/* (non-Javadoc)
	 * @see shiva.domain.operation.filter.Filter#addRestriction(shiva.domain.operation.Restriction)
	 */
	public void addRestriction( Restriction restriction ) {
		if (this.restrictions == null) {
			this.restrictions = new ArrayList<Restriction>();
		}

		restrictions.add(restriction);
	}

	/* (non-Javadoc)
	 * @see shiva.domain.operation.filter.Filter#clearRestrictions()
	 */
	public void clearRestrictions() {
		if( this.restrictions != null ){
			this.restrictions.clear();
		}
	}

	/* (non-Javadoc)
	 * @see shiva.domain.operation.filter.Filter#getRestrictStatement(boolean)
	 */
	public String getRestrictStatement( boolean restrictAll ) {
		if (this.restrictions == null || this.restrictions.size() == 0) {
			return "";
		}

		String conditionalOperator = null;
		if( this.restrictAll == null ){
			conditionalOperator = restrictAll ? AND_OPERATOR : OR_OPERATOR;
		}else{
			conditionalOperator = this.restrictAll ? AND_OPERATOR : OR_OPERATOR;
		}
		
		StringBuffer restrictionStatement = new StringBuffer();

		int numeroRestricoes = this.restrictions.size();
		for (int i = 0; i < numeroRestricoes; i++) {
			Restriction r = (Restriction) this.restrictions.get(i);

			if (i > 0 && ! r.isEndSubRestriction() ) {
				restrictionStatement.append(conditionalOperator);
			}
			
			/*  */
			String preSubRestricao = "";
			String operadorSubRestricao = "";
			if( r.isInitSubRestriction() ){
				preSubRestricao = "("; 
				operadorSubRestricao = r.getSubRestrictionOperator();
			}
			
			/*  */
			String posSubRestricao = "";
			if( r.isEndSubRestriction() ){
				posSubRestricao = ")"; 
			}
			
			/* monta algo como "RNOME like ? AND IDADE > ?" */
			restrictionStatement.append( preSubRestricao ).append( r.getField() ).append( "" );
			restrictionStatement.append( r.getType() ).append( r.getValue() );
			restrictionStatement.append( operadorSubRestricao ).append( " " ).append( posSubRestricao );
		}

		return restrictionStatement.toString();
	}

	/* (non-Javadoc)
	 * @see shiva.domain.operation.filter.Filter#hasRestrictions()
	 */
	public boolean hasRestrictions() {
		return this.restrictions != null && this.restrictions.size() > 0 ? true : false;
	}

}
