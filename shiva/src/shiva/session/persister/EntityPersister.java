package shiva.session.persister;

/**
 * 
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
public interface EntityPersister {
	
	/**
	 * 
	 * 
	 * @param ldapEntity
	 */
	public void persist( Object ldapEntity );
	
	/**
	 * 
	 * 
	 * @param ldapEntity
	 */
	public void update( Object ldapEntity );
	
	/**
	 * 
	 * 
	 * @param ldapEntity
	 */
	public void delete( Object ldapEntity );
	
}
