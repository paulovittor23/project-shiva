package shiva.cfg.component.mapper.model.exception;

public class ColumnAnnotationNotFoundException extends Exception {
	
	private static final long serialVersionUID = -553294181240625246L;

	/**
	 * 
	 * 
	 */
	public ColumnAnnotationNotFoundException(){   
        super();   
    }
	
	/**
	 * 
	 * @param mensagem
	 */
	public ColumnAnnotationNotFoundException(String mensagem){   
        super(mensagem);   
    }
	
}
