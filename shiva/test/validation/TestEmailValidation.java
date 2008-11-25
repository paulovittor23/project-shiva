package validation;

import org.junit.Test;

import shiva.domain.exception.validation.InvalidAttributeValueException;
import shiva.domain.validation.logic.impl.EmailValidation;

import junit.framework.TestCase;

/**
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
public class TestEmailValidation extends TestCase{
	
	private static final String NOME_CAMPO = "CAMPO E-MAIL";
	
	@Test
	public void testValidCondition1(){
		try {
			new EmailValidation().validate( null, "paulovittor23@gmail.com", NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
			fail( "deveria passar sem falhas!" );
		}
	}
	
	@Test
	public void testValidCondition2(){
		try {
			new EmailValidation().validate( null, "test-email@test.com", NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
			fail( "deveria passar sem falhas!" );
		}
	}
	
	@Test
	public void testValidCondition3(){
		try {
			new EmailValidation().validate( null, "test_email@test.com", NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
			fail( "deveria passar sem falhas!" );
		}
	}
	
	@Test(expected=InvalidAttributeValueException.class)
	public void testInvalidCondition1(){
		try {
			new EmailValidation().validate( null, "test_email@testcom", NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
		}
	}
	
	@Test(expected=InvalidAttributeValueException.class)
	public void testInvalidCondition2(){
		try {
			new EmailValidation().validate( null, "test_emailtestcom", NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
		}
	}
	
	@Test(expected=InvalidAttributeValueException.class)
	public void testEmptyStringCondition(){
		try {
			new EmailValidation().validate( null, "", NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
		}
	}
	
	@Test(expected=InvalidAttributeValueException.class)
	public void testNullCondition(){
		try {
			new EmailValidation().validate( null, null, NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
		}
	}

}
