package validation;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import shiva.domain.exception.validation.InvalidAttributeValueException;
import shiva.domain.validation.logic.impl.MinValidation;

import junit.framework.TestCase;

/**
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
public class TestMinValidation extends TestCase {
	
	private static final String NOME_CAMPO = "CAMPO INTEIRO";
	
	@Test
	public void testValidCondition1(){
		Map<String, Object> p = new HashMap<String, Object>();
		p.put( "min", new Integer(3) );
		
		try {
			new MinValidation().validate( p, 3, NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
			fail( "deveria passar sem falhas!" );
		}
	}
	
	@Test
	public void testValidCondition2(){
		Map<String, Object> p = new HashMap<String, Object>();
		p.put( "min", new Integer(3) );
		
		try {
			new MinValidation().validate( p, 5, NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
			fail( "deveria passar sem falhas!" );
		}
	}
	
	@Test(expected=InvalidAttributeValueException.class)
	public void testInvalidCondition(){
		Map<String, Object> p = new HashMap<String, Object>();
		p.put( "min", new Integer(5) );
		
		try {
			new MinValidation().validate( p, 4, NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
		}
	}

	@Test(expected=InvalidAttributeValueException.class)
	public void testNullCondition(){
		Map<String, Object> p = new HashMap<String, Object>();
		p.put( "min", new Integer(3) );
		
		try {
			new MinValidation().validate( p, null, NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
		}
	}
	
}
