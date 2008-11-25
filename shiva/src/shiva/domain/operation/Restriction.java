package shiva.domain.operation;

/**
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
public class Restriction {
	
	private String field;
	private String type;
	private Object value;
	
	private boolean initSubRestriction;
	private boolean endSubRestriction;
	private String subRestrictionOperator;
	
	/**
	 * 
	 */
	public Restriction() {}
	
	/**
	 * 
	 * @param field
	 * @param type
	 * @param value
	 */
	public Restriction( String field, String type, Object value ) {
		this.field = field;
		this.type = type;
		this.value = value;
	}
	
	/**
	 * 
	 * @param field
	 * @param type
	 * @param value
	 * @param initSubRestriction
	 * @param endSubRestriction
	 * @param subRestrictionOperator
	 */
	public Restriction( String field, String type, Object value,
							  boolean initSubRestriction, boolean endSubRestriction, 
							  String subRestrictionOperator) {
		this.field = field;
		this.type = type;
		this.value = value;
		
		this.initSubRestriction = initSubRestriction;
		this.endSubRestriction = endSubRestriction;
		this.subRestrictionOperator = subRestrictionOperator;
	}

	
	public String getField() {
		return this.field;
	}

	public void setField( String field ) {
		this.field = field;
	}

	public String getType() {
		return this.type;
	}

	public void setType( String type ) {
		this.type = type;
	}

	public Object getValue() {
		return this.value;
	}

	public void setValue( Object value ) {
		this.value = value;
	}

	public boolean isInitSubRestriction() {
		return this.initSubRestriction;
	}

	public void setInitSubRestriction( boolean initSubRestriction ) {
		this.initSubRestriction = initSubRestriction;
	}

	public boolean isEndSubRestriction() {
		return this.endSubRestriction;
	}

	public void setEndSubRestriction( boolean endSubRestriction ) {
		this.endSubRestriction = endSubRestriction;
	}

	public String getSubRestrictionOperator() {
		return this.subRestrictionOperator;
	}

	public void setSubRestrictionOperator( String subRestrictionOperator ) {
		this.subRestrictionOperator = subRestrictionOperator;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append( field );
		buffer.append( " " );
		buffer.append( type );
		buffer.append( " " );
		buffer.append( value );
		buffer.append( " (" );
		buffer.append( "iniciaSubRestricao: " );
		buffer.append( initSubRestriction );
		buffer.append( ", " );
		buffer.append( "finalizaSubRestricao: " );
		buffer.append( endSubRestriction );
		buffer.append( ", " );
		buffer.append( "operadorSubRestricao: " );
		buffer.append( subRestrictionOperator );
		buffer.append( ")" );
		return buffer.toString();
	}
	
}
