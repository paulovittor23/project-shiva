package shiva.cfg.component.mapper.model.exception;

@SuppressWarnings("unchecked")
public class UidAttributeNotFoundException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 4748709829356716872L;

    /**
	 * 
	 * 
	 */
    public UidAttributeNotFoundException() {
	super();
    }

    /**
     * 
     * @param mensagem
     */
    public UidAttributeNotFoundException(String mensagem) {
	super(mensagem);
    }
    
    /**
     * 
     * @param clazz
     */
    public UidAttributeNotFoundException(Class clazz) {
	super( "The registered class \"" + clazz.getCanonicalName() + "\" must have an uid attribute." );
    }
    
}
