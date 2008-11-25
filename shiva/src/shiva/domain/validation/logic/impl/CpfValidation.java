package shiva.domain.validation.logic.impl;

import java.util.Map;

import shiva.domain.exception.validation.InvalidAttributeValueException;
import shiva.domain.validation.logic.ValidationClass;
import shiva.util.Utils;

/**
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
public class CpfValidation implements ValidationClass {

	/* (non-Javadoc)
	 * @see shiva.domain.validation.logic.ValidationClass#validate(java.util.Map, java.lang.Object, java.lang.String)
	 */
	@Override
	public void validate( Map<String, Object> parameters, Object attributeValue, String attributeName ) throws InvalidAttributeValueException {
		if( attributeValue == null ){
			throw new InvalidAttributeValueException(
				Utils.retrieveMessage( "validator.invalid.notnull", new Object[]{ attributeName } )
			);
		}
		
		String strValue = (String) attributeValue;
		strValue = strValue.replaceAll( "[^0-9]", "" );
		
		if( strValue.length() == 0 ){
			throw new InvalidAttributeValueException(
				Utils.retrieveMessage( "validator.invalid.notempty", new Object[]{ attributeName } )
			);
		}
		
		if( ! this.isCPF( strValue ) ){
			throw new InvalidAttributeValueException(
				Utils.retrieveMessage( "validator.invalid.cpf", new Object[]{ attributeName } )
			);
		}
	}
	
	/**
	 * @param cpf
	 * @return
	 */
	private boolean isCPF( String cpf ) {
		if ( cpf.length() != 11 )
			return false;

		String numDig = cpf.substring( 0, 9 );
		return calcDigVerif( numDig ).equals( cpf.substring( 9, 11 ) );
	}  
	
	 /**
	  * 
	  * @param num
	  * @return
	  */
	 private String calcDigVerif( String num ) {
		Integer primDig, segDig;
		int soma = 0, peso = 10;
		for( int i = 0; i < num.length(); i++ )
			soma += Integer.parseInt( num.substring( i, i + 1 ) ) * peso--;

		if ( soma % 11 == 0 | soma % 11 == 1 )
			primDig = new Integer( 0 );
		else
			primDig = new Integer( 11 - ( soma % 11 ) );

		soma = 0;
		peso = 11;
		for( int i = 0; i < num.length(); i++ )
			soma += Integer.parseInt( num.substring( i, i + 1 ) ) * peso--;

		soma += primDig.intValue() * 2;
		if ( soma % 11 == 0 | soma % 11 == 1 )
			segDig = new Integer( 0 );
		else
			segDig = new Integer( 11 - ( soma % 11 ) );

		return primDig.toString() + segDig.toString();
	}  

}
