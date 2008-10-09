package shiva.session.persister.sql;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.SequencedHashMap;
import org.apache.log4j.Logger;

import shiva.domain.metadata.AttributeMapping;
import shiva.domain.metadata.EntityMapping;
import shiva.domain.operation.Delete;
import shiva.domain.operation.Insert;
import shiva.domain.operation.Operation;
import shiva.domain.operation.Update;

@SuppressWarnings("unchecked")
public class CrudSqlGen implements SqlGen {
	
	private Logger logger = Logger.getLogger(this.getClass());

	/*
	 * (non-Javadoc)
	 * @see shiva.session.persister.sql.SqlGen#generateInsertString(shiva.domain.metadata.EntityMapping, java.lang.Object)
	 */
	@Override
	public String generateInsertString(EntityMapping em, Object ldapEntity) {
		
		Operation operation = new Insert();

		// directoryName
		String directoryNameBound = em.getDirectoryNameBound();
		operation.setDirectoryName( directoryNameBound );

		// objectClass
		operation.setObjectClasses( em.getObjectClass() );		
		
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
				
				if(am.isUidAttribute()){
					operation.setUid( ((String) attributeValue) + "," + directoryNameBound );
				}
				
				columns.put(attributeNameBound, attributeValue );
				
			} catch (IllegalArgumentException e) {
				logger.error("generateInsertString is not ok :(", e);
			} catch (IllegalAccessException e) {
				logger.error("generateInsertString is not ok :(", e);
			}
		}
		
		operation.setColumns(columns);
		return operation.toStatementString();
	}
	
	/*
	 * (non-Javadoc)
	 * @see shiva.session.persister.sql.SqlGen#generateDeleteString(shiva.domain.metadata.EntityMapping, java.lang.Object)
	 */
	@Override
	public String generateDeleteString(EntityMapping em, Object ldapEntity) {

		Operation operation = new Delete();
		
		try {
			
			// directoryName
			String directoryNameBound = em.getDirectoryNameBound();
			operation.setDirectoryName( directoryNameBound );
			
			// uid
			AttributeMapping uidAttribute = em.getUidAttribute();
			Field attribute = uidAttribute.getAttribute();
			
			if( ! attribute.isAccessible() ){
				attribute.setAccessible( true );
			}
			
			Object attributeValue = attribute.get( ldapEntity );
			operation.setUid( (String) attributeValue );
			
		} catch ( IllegalArgumentException e ) {
			logger.error("generateDeleteString is not ok :(", e);
		} catch ( IllegalAccessException e ) {
			logger.error("generateDeleteString is not ok :(", e);
		}
		
		return operation.toStatementString();
	}

	/*
	 * (non-Javadoc)
	 * @see shiva.session.persister.sql.SqlGen#generateUpdateString(shiva.domain.metadata.EntityMapping, java.lang.Object)
	 */
	@Override
	public String generateUpdateString(EntityMapping em, Object ldapEntity) {

		Operation operation = new Update();

		// directoryName
		String directoryNameBound = em.getDirectoryNameBound();
		operation.setDirectoryName( directoryNameBound );

		// objectClass
		operation.setObjectClasses( em.getObjectClass() );		
		
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
				
				if(am.isUidAttribute()){
					operation.setUid( ((String) attributeValue) + "," + directoryNameBound );
				}else{
					columns.put(attributeNameBound, attributeValue );
				}
				
			} catch (IllegalArgumentException e) {
				logger.error("generateInsertString is not ok :(", e);
			} catch (IllegalAccessException e) {
				logger.error("generateInsertString is not ok :(", e);
			}
		}
		
		operation.setColumns(columns);
		return operation.toStatementString();
	}

}
