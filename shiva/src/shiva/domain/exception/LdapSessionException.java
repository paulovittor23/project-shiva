package shiva.domain.exception;

/**
 * 
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
public class LdapSessionException extends Exception {
	
	private static final long serialVersionUID = -613343445747683121L;

	/**
	 * 
	 * 
	 * 
	 */
	public LdapSessionException(){   
        super();   
    }
	
	/**
	 * 
	 * 
	 * @param mensagem
	 */
	public LdapSessionException(String mensagem){   
        super(mensagem);   
    }
	
}
