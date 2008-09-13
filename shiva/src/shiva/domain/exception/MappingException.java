package shiva.domain.exception;

/**
 * 
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
public class MappingException extends Exception {
	
	private static final long serialVersionUID = 3707984276307613368L;

	/**
	 * 
	 * 
	 */
	public MappingException(){   
        super();   
    }
	
	/**
	 * 
	 * @param mensagem
	 */
	public MappingException(String mensagem){   
        super(mensagem);   
    }
	
}
