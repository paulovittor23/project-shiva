package validation;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import shiva.domain.exception.validation.InvalidAttributeValueException;
import shiva.domain.validation.logic.impl.LengthValidation;

import junit.framework.TestCase;

/**
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
public class TestLengthValidation extends TestCase {
	
	private static final String NOME_CAMPO = "CAMPO STRING";
	
	@Test
	public void testValidCondition1(){
		Map<String, Object> p = new HashMap<String, Object>();
		p.put( "min", new Integer(1) );
		p.put( "max", new Integer(3) );
		
		try {
			new LengthValidation().validate( p, "12", NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
			fail( "deveria passar sem falhas!" );
		}
	}
	
	@Test
	public void testValidCondition(){
		Map<String, Object> p = new HashMap<String, Object>();
		p.put( "min", new Integer(13) );
		p.put( "max", new Integer(20) );
		
		try {
			new LengthValidation().validate( p, "123456789012345", NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
			fail( "deveria passar sem falhas!" );
		}
	}
	
	@Test(expected=InvalidAttributeValueException.class)
	public void testNullParameters(){
		try {
			new LengthValidation().validate( null, "1234567890", NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
		}
	}

	@Test(expected=InvalidAttributeValueException.class)
	public void testNullValue(){
		Map<String, Object> p = new HashMap<String, Object>();
		p.put( "min", new Integer(13) );
		p.put( "max", new Integer(20) );
		
		try {
			new LengthValidation().validate( p, null, NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
		}
	}
	
	@Test
	public void testInsideMinBondary(){
		Map<String, Object> p = new HashMap<String, Object>();
		p.put( "min", new Integer(3) );
		p.put( "max", new Integer(5) );
		
		try {
			new LengthValidation().validate( p, "123", NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
			fail( "deveria passar sem falhas!" );
		}
	}
	
	@Test
	public void testInsideMaxBondary(){
		Map<String, Object> p = new HashMap<String, Object>();
		p.put( "min", new Integer(3) );
		p.put( "max", new Integer(5) );
		
		try {
			new LengthValidation().validate( p, "12345", NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
			fail( "deveria passar sem falhas!" );
		}
	}
	
	@Test(expected=InvalidAttributeValueException.class)
	public void testOutsideMinBondary(){
		Map<String, Object> p = new HashMap<String, Object>();
		p.put( "min", new Integer(3) );
		p.put( "max", new Integer(5) );
		
		try {
			new LengthValidation().validate( p, "12", NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
		}
	}
	
	@Test(expected=InvalidAttributeValueException.class)
	public void testOutsideMaxBondary(){
		Map<String, Object> p = new HashMap<String, Object>();
		p.put( "min", new Integer(3) );
		p.put( "max", new Integer(5) );
		
		try {
			new LengthValidation().validate( p, "123456", NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
		}
	}
		
}
