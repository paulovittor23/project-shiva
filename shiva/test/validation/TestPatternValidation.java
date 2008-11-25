package validation;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import shiva.domain.exception.validation.InvalidAttributeValueException;
import shiva.domain.validation.logic.impl.PatternValidation;

import junit.framework.TestCase;

/**
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 *
 */
public class TestPatternValidation extends TestCase{
	
	private static final String NOME_CAMPO = "CAMPO STRING";
	
	@Test
	public void testValidCondition(){
		Map<String, Object> p = new HashMap<String, Object>();
		p.put( "value", "^a.*f$" );
		
		try {
			new PatternValidation().validate( p, "abcdef", NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
			fail( "deveria passar sem falhas!" );
		}
	}
	
	@Test(expected=InvalidAttributeValueException.class)
	public void testInvalidCondition(){
		Map<String, Object> p = new HashMap<String, Object>();
		p.put( "value", "^a.*f$" );
		
		try {
			new PatternValidation().validate( p, "abcde", NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
		}
	}
	
	@Test(expected=InvalidAttributeValueException.class)
	public void testEmptyStringCondition(){
		Map<String, Object> p = new HashMap<String, Object>();
		p.put( "value", "^a.*f$" );
		
		try {
			new PatternValidation().validate( p, "", NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
		}
	}
	
	@Test(expected=InvalidAttributeValueException.class)
	public void testNullPatternCondition(){
		Map<String, Object> p = new HashMap<String, Object>();
		p.put( "value", null );
		
		try {
			new PatternValidation().validate( p, "abcdef", NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
		}
	}
	
	@Test(expected=InvalidAttributeValueException.class)
	public void testNullValueCondition(){
		Map<String, Object> p = new HashMap<String, Object>();
		p.put( "value", "^a.*f$" );
		
		try {
			new PatternValidation().validate( p, null, NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
		}
	}
	
}
