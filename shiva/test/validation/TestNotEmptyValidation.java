package validation;

import org.junit.Test;

import shiva.domain.exception.validation.InvalidAttributeValueException;
import shiva.domain.validation.logic.impl.NotEmptyValidation;

import junit.framework.TestCase;

/**
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
public class TestNotEmptyValidation extends TestCase {
	
	private static final String NOME_CAMPO = "CAMPO STRING";
	
	@Test
	public void testValidCondition(){
		try {
			new NotEmptyValidation().validate( null, "text", NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
			fail( "deveria passar sem falhas!" );
		}
	}
	
	@Test(expected=InvalidAttributeValueException.class)
	public void testInvalidCondition1(){
		try {
			new NotEmptyValidation().validate( null, "", NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
		}
	}
	
	@Test(expected=InvalidAttributeValueException.class)
	public void testInvalidCondition2(){
		try {
			new NotEmptyValidation().validate( null, "  ", NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
		}
	}
	
	@Test(expected=InvalidAttributeValueException.class)
	public void testNullValue(){
		try {
			new NotEmptyValidation().validate( null, null, NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
		}
	}

}
