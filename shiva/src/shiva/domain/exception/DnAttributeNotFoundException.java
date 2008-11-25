package shiva.domain.exception;

/**
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 * 
 */
@SuppressWarnings( "unchecked" )
public class DnAttributeNotFoundException extends Exception {

	/**
     * 
     */
	private static final long serialVersionUID = 4748709829356716872L;

	/**
	 * 
	 * 
	 */
	public DnAttributeNotFoundException() {
		super();
	}

	/**
	 * 
	 * @param mensagem
	 */
	public DnAttributeNotFoundException( String mensagem ) {
		super( mensagem );
	}

	/**
	 * 
	 * @param clazz
	 */
	public DnAttributeNotFoundException( Class clazz ) {
		super( "The registered class \"" + clazz.getCanonicalName() + "\" must have a Distinguised Name!" );
	}

}
