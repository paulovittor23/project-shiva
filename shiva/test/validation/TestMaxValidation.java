package validation;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import shiva.domain.exception.validation.InvalidAttributeValueException;
import shiva.domain.validation.logic.impl.MaxValidation;

import junit.framework.TestCase;

/**
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
public class TestMaxValidation extends TestCase {
	
	private static final String NOME_CAMPO = "CAMPO INTEIRO";
	
	@Test
	public void testValidCondition1(){
		Map<String, Object> p = new HashMap<String, Object>();
		p.put( "max", new Integer(3) );
		
		try {
			new MaxValidation().validate( p, 3, NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
			fail( "deveria passar sem falhas!" );
		}
	}
	
	@Test
	public void testValidCondition2(){
		Map<String, Object> p = new HashMap<String, Object>();
		p.put( "max", new Integer(3) );
		
		try {
			new MaxValidation().validate( p, 2, NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
			fail( "deveria passar sem falhas!" );
		}
	}
	
	@Test(expected=InvalidAttributeValueException.class)
	public void testInvalidCondition(){
		Map<String, Object> p = new HashMap<String, Object>();
		p.put( "max", new Integer(3) );
		
		try {
			new MaxValidation().validate( p, 4, NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
		}
	}

	@Test(expected=InvalidAttributeValueException.class)
	public void testNullCondition(){
		Map<String, Object> p = new HashMap<String, Object>();
		p.put( "max", new Integer(3) );
		
		try {
			new MaxValidation().validate( p, null, NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
		}
	}
	
}
