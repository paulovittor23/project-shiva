package shiva.session.persister.sql;

import shiva.domain.metadata.EntityMapping;

/**
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
@SuppressWarnings("unchecked")
public interface SqlGenerator {
	
	/**
	 * 
	 * @param em
	 * @param ldapEntity
	 * @return
	 */
	public String generateSelectByDnString(EntityMapping em, Object ldapEntity);
	
	/**
	 * 
	 * @param em
	 * @param ldapEntity
	 * @return
	 */
	public String generateSelectAllString(EntityMapping em, Class clazz);
	
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
