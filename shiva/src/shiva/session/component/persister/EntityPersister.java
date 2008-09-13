package shiva.session.component.persister;

import shiva.cfg.component.mapper.model.metadata.EntityMapping;

/**
 * 
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
@SuppressWarnings({"unchecked", "unused"})
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
