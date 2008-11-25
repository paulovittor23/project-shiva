package validation;

import org.junit.Test;

import shiva.domain.exception.validation.InvalidAttributeValueException;
import shiva.domain.validation.logic.impl.CnpjValidation;
import junit.framework.TestCase;

/**
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
public class TestCnpjValidation extends TestCase {
	
	private static final String NOME_CAMPO = "CAMPO CNPJ";
	
	@Test
	public void testValidCnpjCondition(){
		try {
			new CnpjValidation().validate( null, "42887120000100", NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
			fail( "deveria passar sem falhas!" );
		}
	}
	
	@Test
	public void testValidCnpjWithSeparatorCondition(){
		try {
			new CnpjValidation().validate( null, "42.887.120/0001-00", NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
			fail( "deveria passar sem falhas!" );
		}
	}
	
	@Test(expected=InvalidAttributeValueException.class)
	public void testInvalidCnpjCondition(){
		try {
			new CnpjValidation().validate( null, "42887120000101", NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
		}
	}
	
	@Test(expected=InvalidAttributeValueException.class)
	public void testInvalidCnpjWithSeparatorCondition(){
		try {
			new CnpjValidation().validate( null, "42.887.120/0004-00", NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
		}
	}
	
	@Test(expected=InvalidAttributeValueException.class)
	public void testEmptyStringCondition(){
		try {
			new CnpjValidation().validate( null, "", NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
		}
	}
	
	@Test(expected=InvalidAttributeValueException.class)
	public void testNullCondition(){
		try {
			new CnpjValidation().validate( null, "", NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
		}
	}

}
