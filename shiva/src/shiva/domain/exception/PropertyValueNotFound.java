package shiva.domain.exception;

/**
 * 
 * 
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
public class PropertyValueNotFound extends Exception {

	private static final long serialVersionUID = -7842595560585138491L;
	
	/**
	 * 
	 * 
	 */
	public PropertyValueNotFound(){   
        super();   
    }
	
	/**
	 * 
	 * @param mensagem
	 */
	public PropertyValueNotFound(String mensagem){   
        super(mensagem);   
    }
	
}
