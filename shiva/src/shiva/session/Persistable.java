package shiva.session;

public interface Persistable {
	
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
