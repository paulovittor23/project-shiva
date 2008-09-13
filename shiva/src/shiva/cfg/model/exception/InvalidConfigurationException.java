package shiva.cfg.model.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
public class InvalidConfigurationException extends Exception {

	private static final long serialVersionUID = -7842595560585138491L;
	
	//
	private static final String defaultMessage= "Call getImplicitExceptions to see the exception causes.";
	private List<Exception> implicitExceptions;

	/**
	 * 
	 * 
	 */
	public InvalidConfigurationException(){   
        super(defaultMessage);   
    }
	
	/**
	 * 
	 * @param mensagem
	 */
	public InvalidConfigurationException(String mensagem){   
        super(mensagem);   
    }

	/**
	 * 
	 * @return the implicitExceptions
	 */
	public List<Exception> getImplicitExceptions() {
		return implicitExceptions;
	}

	/**
	 * 
	 * @param implicitExceptions the implicitExceptions to set
	 */
	public void setImplicitExceptions(List<Exception> implicitExceptions) {
		this.implicitExceptions = implicitExceptions;
	}

	/**
	 * 
	 * @param implicitException
	 */
	public void addImplicitExceptions(Exception implicitException) {
		if( this.implicitExceptions == null ){
			this.implicitExceptions = new ArrayList<Exception>();
		}
		this.implicitExceptions.add( implicitException );
	}
	
	/**
	 * 
	 * @return
	 */
	public void getImplicitExceptionsDetail(){
		System.err.println();
		if(this.implicitExceptions != null){
			for(Exception e : this.implicitExceptions){
				System.err.println( "\t***** " + e.getMessage() + "\n");	
			}
		}
	}
	
}
