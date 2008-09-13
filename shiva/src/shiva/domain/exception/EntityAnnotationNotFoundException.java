package shiva.domain.exception;

import shiva.domain.annotation.mapping.LdapEntity;

/**
 * 
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
@SuppressWarnings("unchecked")
public class EntityAnnotationNotFoundException extends Exception {

    private static final long serialVersionUID = -553294181240625246L;

    /**
	 * 
	 * 
	 */
    public EntityAnnotationNotFoundException() {
	super();
    }

    /**
     * 
     * @param mensagem
     */
    public EntityAnnotationNotFoundException(String mensagem) {
	super(mensagem);
    }

    /**
     * 
     * @param clazz
     */
    public EntityAnnotationNotFoundException(Class clazz) {
	super("The registered class \"" + clazz.getCanonicalName()
		+ "\" must be annotated with "
		+ LdapEntity.class.getCanonicalName() + " annotation.");
    }

}
