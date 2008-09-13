package shiva.cfg.component.mapper.model.exception;

import shiva.cfg.component.mapper.model.LdapEntity;

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
