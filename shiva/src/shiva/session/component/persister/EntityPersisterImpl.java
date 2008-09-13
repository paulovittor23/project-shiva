package shiva.session.component.persister;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.SequencedHashMap;
import org.apache.log4j.Logger;

import shiva.cfg.component.mapper.model.metadata.AttributeMapping;
import shiva.cfg.component.mapper.model.metadata.EntityMapping;
import shiva.session.component.persister.model.Delete;
import shiva.session.component.persister.model.Insert;

/**
 * 
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
public class EntityPersisterImpl implements EntityPersister {
	
	private Logger logger = Logger.getLogger(this.getClass());

	/*
	 * (non-Javadoc)
	 * @see jldap.session.component.persister.EntityPersister#generateInsertString(java.lang.Object)
	 */
	public String generateInsertString(EntityMapping em, Object ldapEntity) {
		
		Insert insert = new Insert();

		// directoryName
		String directoryNameBound = em.getDirectoryNameBound();
		insert.setDirectoryName( directoryNameBound );

		// objectClass
		insert.setObjectClasses( em.getObjectClass() );		
		
		// attributes
		Map<String, Object> columns = new SequencedHashMap();
		
		Map<Field, AttributeMapping> ams = em.getAttributesMapped();
		Set<Field> keySet = ams.keySet();
				
		for (Field field : keySet) {
			AttributeMapping am = ams.get( field );
			
			try {
				
				//
				am.getAttribute().setAccessible( true );
				
				String attributeNameBound = am.getAttributeNameBound();
				Object attributeValue = am.getAttribute().get( ldapEntity );
				
				if(attributeNameBound.equalsIgnoreCase( "uid" )){
					insert.setUid( ((String) attributeValue) + "," + directoryNameBound );
				}
				
				columns.put(attributeNameBound, attributeValue );
				
			} catch (IllegalArgumentException e) {
				logger.error("generateInsertString is not ok :(", e);
			} catch (IllegalAccessException e) {
				logger.error("generateInsertString is not ok :(", e);
			}
		}
		
		insert.setColumns(columns);
		return insert.toStatementString();
	}
	
	/*
	 * (non-Javadoc)
	 * @see jldap.session.component.persister.EntityPersister#generateDeleteString(java.lang.Object)
	 */
	public String generateDeleteString(EntityMapping em, Object ldapEntity) {
		
		Delete delete = new Delete();
		
		// directoryName
		String directoryNameBound = em.getDirectoryNameBound();
		delete.setDirectoryName( directoryNameBound );
		
		// objectClass
		delete.setObjectClasses( em.getObjectClass() );
		
		return delete.toStatementString();
	}

	/*
	 * (non-Javadoc)
	 * @see jldap.session.component.persister.EntityPersister#generateUpdateString(java.lang.Object)
	 */
	public String generateUpdateString(EntityMapping em, Object ldapEntity) {
		return null;
	}

}
