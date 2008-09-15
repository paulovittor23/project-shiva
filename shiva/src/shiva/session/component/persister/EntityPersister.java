package shiva.session.component.persister;

import shiva.domain.metadata.EntityMapping;

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
	 * @param em
	 * @param ldapEntity
	 * @return
	 */
	public String generateInsertString(EntityMapping em, Object ldapEntity);
	
	/**
	 * 
	 * @param em
	 * @param ldapEntity
	 * @return
	 */
	public String generateDeleteString(EntityMapping em, Object ldapEntity);
	
	/**
	 * 
	 * @param em
	 * @param ldapEntity
	 * @return
	 */
	public String generateUpdateString(EntityMapping em, Object ldapEntity);

}
