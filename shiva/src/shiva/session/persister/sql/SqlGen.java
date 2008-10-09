package shiva.session.persister.sql;

import shiva.domain.metadata.EntityMapping;

public interface SqlGen {
	
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
