package shiva.domain.exception;

public class ValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4946213609638635327L;
	
	public ValidationException() {
		super();
	}

	public ValidationException(String mensagem) {
		super(mensagem);
	}

}
