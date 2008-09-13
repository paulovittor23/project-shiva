package shiva.session.component.persister;

import shiva.cfg.component.mapper.model.metadata.EntityMapping;

@SuppressWarnings("unchecked")
public interface EntityPersister {
	
	/**
	 * 
	 * @param ldapEntity
	 * @return
	 */
	public String generateInsertString(EntityMapping em, Object ldapEntity);
	
	/**
	 * 
	 * @param ldapEntity
	 * @return
	 */
	public String generateDeleteString(EntityMapping em, Object ldapEntity);
	
	/**
	 * 
	 * @param ldapEntity
	 * @return
	 */
	public String generateUpdateString(EntityMapping em, Object ldapEntity);

}
