package validation;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import shiva.domain.exception.validation.InvalidAttributeValueException;
import shiva.domain.validation.logic.impl.RangeValidation;

import junit.framework.TestCase;

/**
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
public class TestRangeValidation extends TestCase {
	
	private static final String NOME_CAMPO = "CAMPO INTEIRO";
	
	@Test
	public void testValidCondition1(){
		Map<String, Object> p = new HashMap<String, Object>();
		p.put( "min", new Integer(1) );
		p.put( "max", new Integer(3) );
		
		try {
			new RangeValidation().validate( p, 2, NOME_CAMPO );
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
			new RangeValidation().validate( p, 18, NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
			fail( "deveria passar sem falhas!" );
		}
	}
	
	@Test(expected=InvalidAttributeValueException.class)
	public void testNullParameters(){
		try {
			new RangeValidation().validate( null, 5, NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
		}
	}

	@Test(expected=InvalidAttributeValueException.class)
	public void testNullValue(){
		Map<String, Object> p = new HashMap<String, Object>();
		p.put( "min", new Integer(13) );
		p.put( "max", new Integer(20) );
		
		try {
			new RangeValidation().validate( p, null, NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
		}
	}
	
	@Test
	public void testInsideMinBondary(){
		Map<String, Object> p = new HashMap<String, Object>();
		p.put( "min", new Integer(3) );
		p.put( "max", new Integer(5) );
		
		try {
			new RangeValidation().validate( p, 3, NOME_CAMPO );
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
			new RangeValidation().validate( p, 5, NOME_CAMPO );
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
			new RangeValidation().validate( p, 2, NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
		}
	}
	
	@Test(expected=InvalidAttributeValueException.class)
	public void testOutsideMaxBondary(){
		Map<String, Object> p = new HashMap<String, Object>();
		p.put( "min", new Integer(3) );
		p.put( "max", new Integer(5) );
		
		try {
			new RangeValidation().validate( p, 6, NOME_CAMPO );
		} catch ( InvalidAttributeValueException e ) {
		}
	}
		
}
