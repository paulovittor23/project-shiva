package validation;

import org.junit.Test;

import shiva.domain.exception.validation.InvalidAttributeValueException;
import shiva.domain.validation.logic.impl.CpfValidation;

import junit.framework.TestCase;

/**
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
public class TestCpfValidation extends TestCase {
	
	private static final String NOME_CAMPO = "CAMPO CPF";

	@Test
	public void testValidCpfCondition(){
		try {
			new CpfValidation().validate( null, "61444560905", NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
			fail( "deveria passar sem falhas!" );
		}
	}
	
	@Test
	public void testValidCpfWithSeparatorCondition(){
		try {
			new CpfValidation().validate( null, "614.445.609-05", NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
			fail( "deveria passar sem falhas!" );
		}
	}
	
	@Test(expected=InvalidAttributeValueException.class)
	public void testInvalidCpfCondition(){
		try {
			new CpfValidation().validate( null, "23478612374", NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
		}
	}
	
	@Test(expected=InvalidAttributeValueException.class)
	public void testInvalidCpfWithSeparatorCondition(){
		try {
			new CpfValidation().validate( null, "234.786.123-74", NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
		}
	}
	
	@Test(expected=InvalidAttributeValueException.class)
	public void testEmptyStringCondition(){
		try {
			new CpfValidation().validate( null, "", NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
		}
	}
	
	@Test(expected=InvalidAttributeValueException.class)
	public void testNullCondition(){
		try {
			new CpfValidation().validate( null, "", NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
		}
	}
	
}
