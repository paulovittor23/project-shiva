package validation;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite( "Test for validation" );

		//$JUnit-BEGIN$
		suite.addTestSuite( TestCnpjValidation.class );
		suite.addTestSuite( TestCpfValidation.class );
		suite.addTestSuite( TestEmailValidation.class );
		suite.addTestSuite( TestLengthValidation.class );
		suite.addTestSuite( TestMaxValidation.class );
		suite.addTestSuite( TestMinValidation.class );
		suite.addTestSuite( TestNotEmptyValidation.class );
		suite.addTestSuite( TestPatternValidation.class );
		suite.addTestSuite( TestRangeValidation.class );
		//$JUnit-END$
		
		return suite;
	}

}
